import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CarServiceTests {

    @Test
    fun `test serving people dinner`() {
        val peopleDinner = PeopleDinner()
        peopleDinner.serveDinner("1")
        peopleDinner.serveDinner("2")
        assertEquals(2, peopleDinner.getPeopleServed())
    }

    @Test
    fun `test serving robot dinner`() {
        val robotDinner = RobotDinner()
        robotDinner.serveDinner("3")
        assertEquals(1, robotDinner.getRobotsServed())
    }

    @Test
    fun `test refueling electric cars`() {
        val electricStation = ElectricStation()
        electricStation.refuel("1")
        electricStation.refuel("2")
        assertEquals(2, electricStation.getElectricCarsRefueled())
    }

    @Test
    fun `test refueling gas cars`() {
        val gasStation = GasStation()
        gasStation.refuel("3")
        assertEquals(1, gasStation.getGasCarsRefueled())
    }

    @Test
    fun `test consolidated statistics`() {
        val electricStation = ElectricStation()
        val gasStation = GasStation()
        val peopleDinner = PeopleDinner()
        val robotDinner = RobotDinner()

        electricStation.refuel("1")
        gasStation.refuel("2")
        peopleDinner.serveDinner("3")
        robotDinner.serveDinner("4")

        CarServiceStats.recordElectricCar()
        CarServiceStats.recordGasCar()
        CarServiceStats.recordPeopleServed()
        CarServiceStats.recordRobotsServed()

        assertEquals(1, CarServiceStats.totalElectricCars)
        assertEquals(1, CarServiceStats.totalGasCars)
        assertEquals(1, CarServiceStats.totalPeopleServed)
        assertEquals(1, CarServiceStats.totalRobotsServed)
    }
}
