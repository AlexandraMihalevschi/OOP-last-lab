import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class QueueTests {

    @Test
    fun `ArrayQueue should handle basic operations`() {
        val queue: Queue<Int> = ArrayQueue()
        assertTrue(queue.isEmpty())
        queue.enqueue(1)
        queue.enqueue(2)
        assertEquals(2, queue.size())
        assertEquals(1, queue.dequeue())
        assertEquals(1, queue.size())
        assertFalse(queue.isEmpty())
    }

    @Test
    fun `CircularQueue should handle basic operations`() {
        val queue: Queue<String> = CircularQueue(2)
        queue.enqueue("A")
        queue.enqueue("B")
        assertThrows(IllegalStateException::class.java) { queue.enqueue("C") }
        assertEquals("A", queue.dequeue())
        queue.enqueue("C")
        assertEquals("B", queue.dequeue())
    }

    @Test
    fun `PriorityQueue should handle basic operations`() {
        val queue: Queue<Int> = PriorityQueue()
        queue.enqueue(5)
        queue.enqueue(1)
        queue.enqueue(3)
        assertEquals(1, queue.dequeue()) // Smallest first
        assertEquals(3, queue.dequeue())
        assertEquals(5, queue.dequeue())
    }
}
