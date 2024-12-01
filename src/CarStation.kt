class CarStation(
    private val diningService: Dineable?,
    private val refuelingService: Refuelable,
    val queue: Queue<Car>
) {
    // Add a car to the queue
    fun addCar(car: Car) {
        queue.enqueue(car)
    }

    // Serve all cars in the queue
    fun serveCars() {
        while (!queue.isEmpty()) {
            val car = queue.dequeue()

            if (car != null) {
                println("Processing car: ${car.id}")

                // Serve dinner
                if (car.isDining) {
                    diningService?.serveDinner(car.id.toString())
                }

                // Refuel the car
                refuelingService.refuel(car.id.toString())
            } else {
                println("Encountered a null car in the queue.")
            }
        }
        println("No cars in the queue.")
    }
}
