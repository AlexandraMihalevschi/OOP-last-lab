object CarServiceStats {
    var totalElectricCars = 0
    var totalGasCars = 0
    var totalPeopleServed = 0
    var totalRobotsServed = 0

    fun recordElectricCar() {
        totalElectricCars++
    }

    fun recordGasCar() {
        totalGasCars++
    }

    fun recordPeopleServed() {
        totalPeopleServed++
    }

    fun recordRobotsServed() {
        totalRobotsServed++
    }

    fun printStats() {
        println("Electric cars refueled: $totalElectricCars")
        println("Gas cars refueled: $totalGasCars")
        println("People served dinner: $totalPeopleServed")
        println("Robots served dinner: $totalRobotsServed")
    }
}
