class GasStation : Refuelable {
    private var gasCarsRefueled = 0

    override fun refuel(carId: String) {
        println("Refueling gas car $carId.")
        gasCarsRefueled++
    }

    fun getGasCarsRefueled(): Int = gasCarsRefueled
}
