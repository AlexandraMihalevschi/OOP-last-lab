fun main() {
    // CarStations and Semaphore setup
    val gasStation = CarStation(null, object : Refuelable {
        override fun refuel(carId: String) {
            println("Refueling Gas car $carId")
        }
    }, ArrayQueue())

    val electricStation = CarStation(null, object : Refuelable {
        override fun refuel(carId: String) {
            println("Refueling Electric car $carId")
        }
    }, ArrayQueue())

    val semaphore = Semaphore(gasStation, electricStation)

    // Starting the CarScheduler
    val carScheduler = CarScheduler(semaphore, "C:\\Users\\Lenovo\\Laboratory_POO\\oop_coure_repo\\Lab3\\queue", 5000L) // Every 5 seconds
    carScheduler.start()

    Thread.sleep(60000L)
}
