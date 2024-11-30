class CarStation(
    private val diningService: Dineable?,
    private val refuelingService: Refuelable,
    private val queue: Queue<Car>
) {
    // Adds a car to the queue
    fun addCar(car: Car) {
        queue.enqueue(car)
    }

    // Serves all cars in the queue
    fun serveCars() {
        while (!queue.isEmpty()) {
            val car = queue.dequeue()

            if (car != null) {
                println("Processing car: ${car.id}")

                // Serve dinner if applicable
                if (car.isDining) {
                    diningService?.serveDinner(car.id)
                }

                // Refuel the car
                refuelingService.refuel(car.id)
            } else {
                println("Encountered a null car in the queue.")
            }
        }
        println("All cars have been served.")
    }
}
