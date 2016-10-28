package bddqueue;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * A First in First Out approach to organize Generic Objects. 
 * This is a class
 * that extends AbstractQueue and implements it's methods.
 * 
 * @author kevinmichie
 * @version 2016.10.03
 * @param <E>
 *            the type of elements in this queue
 */

public class CircArrayQueue<E> extends AbstractQueue<E> {

    private E[] elements;
    private int first; // Think of this has depth
    private int length; // Think of this as capacity

    /**
     * Constructs a new empty queue with a bound of {@code max}.
     * 
     * @param max
     *            the bound of the new queue
     */
    @SuppressWarnings("unchecked")
    public CircArrayQueue(int max) {
        super(max);
        if (max <= 0) {
            throw new IllegalArgumentException(); }
        elements = (E[]) new Object[max]; // How to handle generics
        length = max;
        first = 0;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void enqueue(E element)
            throws IllegalArgumentException, IllegalStateException {
        if (element == null) {
            throw new IllegalArgumentException(); }
        if (first == length) {
            throw new IllegalStateException(); }

        Set<E> set = new HashSet<>(Arrays.asList(elements));
        set.remove(null);
        set.add(element);
        elements = (E[]) set.toArray(new String[] {});
        first++;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public E dequeue() throws IllegalStateException {
        if (first == 0) {
            throw new IllegalStateException(); }
        E result = elements[elements.length - 1];
        Set<E> set = new HashSet<>(Arrays.asList(elements));
        set.remove(null);
        set.remove(result);
        elements = (E[]) set.toArray(new String[] {});
        first -= 1; 
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int length() {
        return first;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Queue<E> newInstance() {
        return new CircArrayQueue<>(capacity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        first = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<E> iterator() {
        return new StackIterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < first; i++) {
            sb.append(elements[i]);
            if (i < first - 1) {
                sb.append(", "); }
        }
        sb.append("]:");
        sb.append(capacity);
        return sb.toString();
    }

    private class StackIterator implements Iterator<E> {

        private int index;

        /**
         * creates a new stack iterator that
         * starts at index of 0.
         */
        public StackIterator() {
            index = 0;
        }

        /**
         * checks if there is a next item.
         * @return boolean if there is a next item.
         */
        @Override
        public boolean hasNext() {
            return index < first;
        }

        /**
         * returns the next item in the queue if available
         * @return next item in queue if available
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException(); }
            E result = elements[index];
            index++;
            return result;
        }
    }
}
