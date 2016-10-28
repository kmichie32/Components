package bddqueue;
/**
 * A First in First Out approach to organize Generic Objects. This is an
 * interface to be implemented across AbstractQueue, ListQueue, and
 * CircArrayQueue. The Iterable Class makes it possible to iterate through the
 * queue.
 * 
 * @author kevinmichie
 * @version 2016.10.03
 * @param <E>
 *            the type of elements in this stack
 */

public interface Queue<E> extends Iterable<E> {

    /**
     * Adds the specified element to the end of this queue.
     *
     * @param element to be added to this queue
     * @throws IllegalArgumentException if the specified element is null
     * @throws IllegalStateException if the queue is already at capacity
     */
    public void enqueue(E element) 
            throws IllegalArgumentException, IllegalStateException;
    
    /**
     * Removes and returns the element at the beginning of the queue.
     * @return the element at the top of this stack
     * @throws IllegalStateException if this stack is empty
     */
    public E dequeue() throws IllegalStateException;
    
    /**
     * Returns the length of this queue
     * @return the length of this queue
     */
    public int length();
    
    /**
     * Returns the capacity of this queue
     * @return the capacity of this queue
     */
    public int capacity();
    
    /**
     * Returns a new, empty queue with the same capacity as this queue
     * @return a new, empty queue with the same capacity as this queue
     */
    public Queue<E> newInstance();
    
    /**
     * Empties this queue
     */
    public void clear();
    
    
    /**
     * Returns true if this queue is empty.
     * @return true if this queue is empty.
     */
    public boolean isEmpty();
    
    /**
     * Returns true if this queue is full.
     * @return true if this queue is full.
     */
    public boolean isFull();
    
    /**
     * Appends an element to the back of the Queue
     * @param that object to be appended
     */
    public void append(Queue<E> that) throws IllegalStateException;
    
    /**
     * Returns a new queue that is a shallow copy of this queue. The new queue
     * has the same capacity as this stack.
     * @return new queue that is a shallow copy of this queue.
     */
    public Queue<E> copy();

}
