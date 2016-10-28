package bddqueue;
import java.util.Iterator;

/**
 * A First in First Out approach to organize Generic Objects. 
 * This is a class
 * that implements the Queue interface and implements it's methods.
 * 
 * @author kevinmichie
 * @version 2016.10.03
 * @param <E>
 *            the type of elements in this queue
 */

public abstract class AbstractQueue<E> implements Queue<E> {

    /**
     * An integer value that provides the bounds for
     * the queue to fall within.
     */
    public final int capacity;

    /**
     * Constructs a new empty queue with a bound of {@code max}.
     * 
     * @param max
     *            the bound of the new queue
     */
    public AbstractQueue(int max) {
        capacity = max;
    }

    /**
     * Returns the size of the queue
     * 
     * @return the size of the queue
     */
    public int capacity() {
        return capacity;
    }

    /**
     * Returns a boolean if a queue is empty
     * 
     * @return a boolean if a queue is empty
     */
    public boolean isEmpty() {
        return length() == 0;
    }

    /**
     * Returns a boolean if a queue is full
     * 
     * @return a boolean if a queue is full
     */
    public boolean isFull() {
        return length() == capacity();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void append(Queue<E> that) 
            throws IllegalStateException {
        if ((this.length() + that.length()) > capacity) {
            throw new IllegalStateException(); }
        int thatQueueLength = that.length();
        for (int i = 0; i < thatQueueLength; i++) {
            this.enqueue(that.dequeue());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Queue<E> copy() {
        Queue<E> result = this.newInstance();
        for (E elem : this) {
            result.enqueue(elem);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; }
        if (obj == null) {
            return false; }
        if (!(obj instanceof Queue)) {
            return false; }
        Queue<?> other = (Queue<?>) obj;
        if (capacity() != other.capacity()) {
            return false; }
        if (length() != other.length()) {
            return false; }
        Iterator<?> otherIter = other.iterator();
        for (E elem : this) {
            if (!elem.equals(otherIter.next())) {
                return false; }
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator<E> iter = this.iterator();
        while (iter.hasNext()) {
            E elem = iter.next();
            if (iter.hasNext()) {
                sb.append(elem.toString() + ", ");
            } 
            else {
                sb.append(elem.toString());
            }
        }
        sb.append("]:");
        sb.append(this.capacity());
        return sb.toString();
    }
}
