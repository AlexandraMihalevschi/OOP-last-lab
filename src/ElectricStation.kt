class ElectricStation : Refuelable {
    private var electricCarsRefueled = 0

    override fun refuel(carId: String) {
        println("Refueling electric car $carId.")
        electricCarsRefueled++
    }

    fun getElectricCarsRefueled(): Int = electricCarsRefueled
}
