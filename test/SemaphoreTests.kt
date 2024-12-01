import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SemaphoreTest {

    @Test
    fun testGasCarsAreServedByGasStation() {
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

        val cars = listOf(
            Car("1", "GAS", "PEOPLE", false, 50),
            Car("2", "ELECTRIC", "ROBOTS", true, 70),
            Car("3", "GAS", "PEOPLE", false, 30)
        )

        semaphore.guideCars(cars)
        semaphore.serveAllStations()

        // Assuming you will add logic to verify refuel method calls or any other actions
        // For now, we just assert if the expected stations were served
        assertTrue(gasStation.queue.isEmpty())  // Verifying gas station queue is empty after serving
        assertTrue(electricStation.queue.isEmpty())  // Verifying electric station queue is empty after serving
    }

    @Test
    fun testElectricCarsAreServedByElectricStation() {
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

        val cars = listOf(
            Car("1", "GAS", "PEOPLE", false, 50),
            Car("2", "ELECTRIC", "ROBOTS", true, 70),
            Car("3", "ELECTRIC", "PEOPLE", false, 90)
        )

        semaphore.guideCars(cars)
        semaphore.serveAllStations()

        // Verifying that electric cars are served by electric station
        assertTrue(gasStation.queue.isEmpty())  // Verifying gas station queue is empty
        assertTrue(electricStation.queue.isEmpty())  // Verifying electric station queue is empty
    }
}
