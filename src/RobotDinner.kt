class RobotDinner : Dineable {
    private var robotsServed = 0

    override fun serveDinner(carId: String) {
        println("Serving dinner to robots in car $carId.")
        robotsServed++
    }

    fun getRobotsServed(): Int = robotsServed
}
