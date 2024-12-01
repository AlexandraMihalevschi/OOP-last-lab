interface Queue<T> {
    fun enqueue(item: T)
    fun dequeue(): T?
    fun isEmpty(): Boolean
    fun size(): Int
}
