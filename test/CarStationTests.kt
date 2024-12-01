import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CarStationTests {

    @Test
    fun `serve a gas car with no dining`() {
        val diningService = object : Dineable {
            override fun serveDinner(carId: String) {
                throw IllegalStateException("This should not be called")
            }
        }
        val refuelingService = object : Refuelable {
            var refueledCars = mutableListOf<String>()

            override fun refuel(carId: String) {
                refueledCars.add(carId)
            }
        }
        val queue = ArrayQueue<Car>()
        val carStation = CarStation(diningService, refuelingService, queue)

        val car = Car("1", "GAS", "PEOPLE", false, 30)
        carStation.addCar(car)
        carStation.serveCars()

        assertEquals(1, refuelingService.refueledCars.size)
        assertEquals("1", refuelingService.refueledCars[0])
        assertEquals(true, queue.isEmpty())
    }

    @Test
    fun `serve an electric car with dining`() {
        val diningService = object : Dineable {
            var servedCars = mutableListOf<String>()

            override fun serveDinner(carId: String) {
                servedCars.add(carId)
            }
        }
        val refuelingService = object : Refuelable {
            var refueledCars = mutableListOf<String>()

            override fun refuel(carId: String) {
                refueledCars.add(carId)
            }
        }
        val queue = ArrayQueue<Car>()
        val carStation = CarStation(diningService, refuelingService, queue)

        val car = Car("2", "ELECTRIC", "ROBOTS", true, 40)
        carStation.addCar(car)
        carStation.serveCars()

        assertEquals(1, diningService.servedCars.size)
        assertEquals("2", diningService.servedCars[0])
        assertEquals(1, refuelingService.refueledCars.size)
        assertEquals("2", refuelingService.refueledCars[0])
        assertEquals(true, queue.isEmpty())
    }

    @Test
    fun `process multiple cars`() {
        val diningService = object : Dineable {
            val servedCars = mutableListOf<String>()
            override fun serveDinner(carId: String) {
                servedCars.add(carId)
            }
        }
        val refuelingService = object : Refuelable {
            val refueledCars = mutableListOf<String>()
            override fun refuel(carId: String) {
                refueledCars.add(carId)
            }
        }
        val queue = ArrayQueue<Car>()
        val carStation = CarStation(diningService, refuelingService, queue)

        val car1 = Car("1", "GAS", "PEOPLE", false, 20)
        val car2 = Car("2", "ELECTRIC", "ROBOTS", true, 50)

        carStation.addCar(car1)
        carStation.addCar(car2)
        carStation.serveCars()

        // Assert cars were served in the correct order
        assertEquals(listOf("2"), diningService.servedCars) // Only car2 requires dining
        assertEquals(listOf("1", "2"), refuelingService.refueledCars) // Both cars are refueled
        assertTrue(queue.isEmpty())
    }
}
