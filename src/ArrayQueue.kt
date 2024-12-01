class ArrayQueue<T> : Queue<T> {
    private val items = mutableListOf<T>()

    override fun enqueue(item: T) {
        items.add(item)
    }

    override fun dequeue(): T? {
        return if (items.isNotEmpty()) items.removeAt(0) else null
    }

    override fun isEmpty(): Boolean = items.isEmpty()

    override fun size(): Int = items.size
}
