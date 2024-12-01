import java.io.File
import java.util.*
import kotlin.concurrent.thread

class CarScheduler(
    private val semaphore: Semaphore,
    private val queueFolderPath: String,  // Folder for car jsons
    private val interval: Long = 5000L,   // Interval in milliseconds
    private val maxFilesToProcess: Int = 31 // Maximum number of JSON files
) {
    private var processedFilesCount = 0  // Counter for processed files
    private var lastFileAddedTime: Long = System.currentTimeMillis()  // Timestamp of the last file added
    private lateinit var timer: Timer

    private fun readAndAddCarsToStation() {
        val queueFolder = File(queueFolderPath)
        val carFiles = queueFolder.listFiles { _, name -> name.endsWith(".json") } // Search for .json files

        carFiles?.forEach { file ->
            if (processedFilesCount >= maxFilesToProcess) {
                println("Maximum number of files processed. Stopping scheduler.")
                return@forEach // Stop processing more files
            }

            try {
                val cars = JsonParser.fromJsonFile(file.absolutePath) // Deserialize JSON
                semaphore.guideCars(cars)  // Add cars to coresponding stations
                println("🍵 Car from file ${file.name} added to the queue!")
                file.delete() // delete the file after processing

                processedFilesCount++
                lastFileAddedTime = System.currentTimeMillis()  // Update the timestamp of the last file added
            } catch (e: Exception) {
                println("Failed to process file ${file.name}: ${e.message}")
            }
        }

        if (processedFilesCount >= maxFilesToProcess) {
            println("Maximum number of files processed.")
        }
    }

    // Serve cars
    private fun serveCarsFromStations() {
        semaphore.serveAllStations()  // Serve cars in both

        if (processedFilesCount >= maxFilesToProcess) {
            println("Stopping scheduler.")
            timer.cancel()  // Stop the scheduler
        }
    }

    // Check if queue empty for more than 30 sec
    private fun checkQueueEmptyForTwoMinutes() {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastFileAddedTime > 30_000) {
            println("Queue has been empty for more than 30 sec. Stopping scheduler.")
            timer.cancel()  // Stop the scheduler
        }
    }

    // Start the scheduled tasks
    fun start() {
        timer = Timer()

        // Read and add cars every interval
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                thread {
                    readAndAddCarsToStation()
                    if (processedFilesCount >= maxFilesToProcess) {
                        timer.cancel()
                    }
                }
            }
        }, 0, interval)

        // Serve cars every interval
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                thread {
                    serveCarsFromStations()
                    if (processedFilesCount >= maxFilesToProcess) {
                        timer.cancel()
                    }
                }
            }
        }, 0, interval)

        // Check if the queue empty for more than 30 ssec
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                checkQueueEmptyForTwoMinutes()
            }
        }, 0, 20_000)  // Check every 20 sec
    }
}
