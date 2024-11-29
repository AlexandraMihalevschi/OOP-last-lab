class PeopleDinner : Dineable {
    private var peopleServed = 0

    override fun serveDinner(carId: String) {
        println("Serving dinner to people in car $carId.")
        peopleServed++
    }

    fun getPeopleServed(): Int = peopleServed
}
