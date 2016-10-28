package bddqueue;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
/**
 * A First in First Out approach to organize Generic
 * Objects. This is a class that extends AbstractQueue
 * and implements it's methods.
 * 
 * @author kevinmichie
 * @version 2016.10.03
 * @param <E> the type of elements in this queue
 * */
public class ListQueue<E> extends AbstractQueue<E> {

    private List<E> list;
    
    /**
     * Constructs a new empty queue with a bound of {@code max}.
     * @param max the bound of the new queue
     */
    protected ListQueue(int max) {
        super(max);
        if (max <= 0) { throw new IllegalArgumentException(); }
        list = new LinkedList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enqueue(E element)
            throws IllegalArgumentException, IllegalStateException {
        if (element == null) { throw new IllegalArgumentException(); }
        if (list.size() == capacity) { throw new IllegalStateException(); }
        list.add(element);    
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E dequeue() throws IllegalStateException {
        if (list.isEmpty()) { throw new IllegalStateException(); }
        E item = list.remove(0);  
        return item;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int length() {
        return list.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Queue<E> newInstance() {
        return new ListQueue<E>(capacity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        list.clear();
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

}
