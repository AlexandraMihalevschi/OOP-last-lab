class Semaphore(
    private val gasStation: CarStation,
    private val electricStation: CarStation
) {
    fun guideCars(jsonCars: List<Car>) {
        for (car in jsonCars) {
            when (car.type) {
                "GAS" -> gasStation.addCar(car)
                "ELECTRIC" -> electricStation.addCar(car)
                else -> println("Unknown car type: ${car.type}")
            }
        }
    }

    fun serveAllStations() {
        println("   ⛽ Gas Station")
        gasStation.serveCars()

        println("   🪫⚡ Electric Station")
        electricStation.serveCars()
    }
}
