import java.io.File

// Simple data class for Car
data class Car(
    val id: Int,
    val type: String,
    val passengers: String,
    val isDining: Boolean,
    val consumption: Int
)

object JsonParser {

    // Read and parse JSON file into a list of Car objects
    fun fromJsonFile(filePath: String): List<Car> {
        val cars = mutableListOf<Car>()

        // Read the file line by line
        File(filePath).forEachLine { line ->
            if (line.trim().startsWith("{") && line.trim().endsWith("}")) {
                val id = extractValue("id", line).toInt()
                val type = extractValue("type", line)
                val passengers = extractValue("passengers", line)
                val isDining = extractValue("isDining", line).toBoolean()
                val consumption = extractValue("consumption", line).toInt()

                cars.add(Car(id, type, passengers, isDining, consumption))
            }
        }

        return cars
    }

    private fun extractValue(key: String, line: String): String {
        val regex = """"$key"\s*:\s*([^,}]+)""".toRegex()
        val matchResult = regex.find(line)
        return matchResult?.groups?.get(1)?.value?.trim()?.removeSurrounding("\"") ?: ""
    }
}
