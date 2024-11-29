class CircularQueue<T>(private val capacity: Int) : Queue<T> {
    private val items = arrayOfNulls<Any>(capacity)
    private var head = 0
    private var tail = 0
    private var count = 0

    @Suppress("UNCHECKED_CAST")
    override fun enqueue(item: T) {
        if (count == capacity) throw IllegalStateException("Queue is full")
        items[tail] = item
        tail = (tail + 1) % capacity
        count++
    }

    @Suppress("UNCHECKED_CAST")
    override fun dequeue(): T? {
        if (isEmpty()) return null
        val item = items[head] as T
        items[head] = null
        head = (head + 1) % capacity
        count--
        return item
    }

    override fun isEmpty(): Boolean = count == 0

    override fun size(): Int = count
}
