package bddqueue;
import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;


/**
* A Tester class for the methods implemented
* in the CircArrayQueue class.
* 
* @author kevinmichie
* @version 2016.10.03
*/

public class CircArrayQueueTest {

    private Queue<String> s0;
    private Queue<String> s3;
    private Queue<String> s5;

    /**
     * Sets up A CircArrayQueue before each
     * test.
     * @throws Exception if invalid
     */
    @Before
    public void setUp() throws Exception {
        s0 = new CircArrayQueue<>(5);
        s3 = new CircArrayQueue<>(5);
        s3.enqueue("A");
        s3.enqueue("B");
        s3.enqueue("C");
        s5 = new CircArrayQueue<>(5);
        s5.enqueue("A");
        s5.enqueue("B");
        s5.enqueue("C");
        s5.enqueue("D");
        s5.enqueue("E");
    }

    /**
     * Tests Illegal State Exception with append method
     */
    @Test(expected = IllegalStateException.class)
    public void testAppendISE() {
        s3.append(s5);
        fail();
        assertEquals(3, s3.length());
        s5.append(s3);
        fail();
        assertEquals(5, s5.length());
        s3.append(null);
        fail();
    }

    /**
     * Tests the iterate method
     */
    @Test(expected = NoSuchElementException.class)
    public void iterate() {
        Iterator<String> iter = s0.iterator();
        iter.next();
        fail();
    }
    
    /**
     * Tests creation of CircArrayQueue parameters
     */
    @Test
    public void testCircArrayQueue() {
        assertTrue(s0.isEmpty());
        assertEquals(0, s0.length());
        assertEquals(5, s0.capacity());
    }

    /**
     * Tests if object is null
     */
    @Test
    public void nullTester() {
        Queue<String> q0 = null;
        assertFalse(s0.equals(q0));
    }
    /**
     * Tests Illegal Argument Exception if
     * queue created has negative capacity
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCircArrayQueueIAE() {
        s0 = new CircArrayQueue<>(-5);
        fail();
    }

    /**
     * Tests Illegal Argument Exception if
     * queue created has zero capacity
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCircArrayQueueIAE2() {
        s0 = new CircArrayQueue<>(0);
        fail();
    }

    /**
     * Tests enqueue method
     */
    @Test
    public void testEnqueue() {
        assertEquals(3, s3.length());
    }

    /**
     * Tests enqueue method
     */
    @Test
    public void testEnqueueMethod() {
        Queue<String> q = new CircArrayQueue<>(5);
        q.enqueue("hello");
        q.enqueue("world");
        String s = q.dequeue();
        assertEquals("hello", s);
        assertEquals(1, q.length());
    }
    /**
     * Tests Illegal Argument Exception if
     * queue adds null value
     */
    @Test(expected = IllegalArgumentException.class)
    public void testEnqueueIAE() {
        s3.enqueue(null);
        fail();
    }

    /**
     * Tests Illegal State Exception if
     * queue adds item past capacity
     */
    @Test(expected = IllegalStateException.class)
    public void testEnqueueISE() {
        s3.enqueue("D");
        s3.enqueue("E");
        s3.enqueue("F");
        fail();
    }

    /**
     * Tests dequeue method
     */
    @Test
    public void testDequeue() {
        s3.dequeue();
        assertEquals(2, s3.length());
        assertEquals("B", s3.dequeue());
        Iterator<String> iter = s3.iterator();
        assertTrue(iter.hasNext());
        assertEquals("A", iter.next());
        s3.dequeue();
        assertFalse(iter.hasNext());
    }

    /**
     * Tests Illegal State Exception if
     * queue dequeues when empty
     */
    @Test(expected = IllegalStateException.class)
    public void testDequeueISE() {
        s0.dequeue();
        fail();
        assertEquals(0, s0.length());
        s3.dequeue();
        s3.dequeue();
        s3.dequeue();
        s3.dequeue();
        fail();
    }

    /**
     * Tests comparison of new queue
     * against another queue of same
     * contents
     */
    @Test
    public void testNewInstance() {
        Queue<String> s = s3.newInstance();
        assertTrue(s.isEmpty());
        assertEquals(s3.capacity(), s.capacity());
    }

    /**
     * Tests if queue is clear
     */
    @Test
    public void testClear() {
        s3.clear();
        assertTrue(s3.isEmpty());
        assertEquals(0, s3.length());
    }

    /**
     * Tests if iterator is going through queue
     */
    @Test
    public void testIterator() {
        int count = 0;
        String output = "";
        for (String s : s3) {
            output += s;
            count++;
        }
        assertEquals(count, 3);
        assertEquals(output, "ABC");
    }

    /**
     * Tests if queue is full
     */
    @Test
    public void testIsFull() {
        assertFalse(s3.isFull());
        s3.enqueue("D");
        s3.enqueue("E");
        assertTrue(s3.isFull());
    }

    /**
     * Tests if copy created is exact match
     * to original
     */
    @Test
    public void testCopy() {
        Queue<String> s = s3.copy();
        String str1 = s.dequeue();
        String str2 = s3.dequeue();
        assertEquals(str1, str2); // uses the equals for String objects
    }
    
    
    /**
     * Tests all scenarios of contents being
     * equal despite type of object.
     */
    @Test
    public void testEqualsObject() {
        assertTrue(s0.equals(s0));
        
        Queue<String> s;
        s = new CircArrayQueue<>(5);
        assertTrue(s0.equals(s));

        s.enqueue("A");
        s.enqueue("B");
        assertFalse(s3.equals(s));

        s.enqueue("C");
        assertTrue(s3.equals(s));

        s.dequeue();
        s.enqueue("D");
        assertFalse(s3.equals(s));

        assertNotNull(s3);
        
        s = new CircArrayQueue<>(10);
        assertFalse(s0.equals(s));

        assertFalse(s0.equals("A"));

        // list-stack with A, B, C should be equal to
        // an array stack with A, B, C.

        s = new ListQueue<>(5);
        s.enqueue("A");
        s.enqueue("B");
        s.enqueue("C");
        assertTrue(s3.equals(s));
    }
    

    /**
     * Tests the creation of abstraction in
     * String format
     */
    @Test
    public void testToString() {
        assertEquals("[A, B, C]:5", s3.toString());
        s0.enqueue("A");
        assertEquals("[A]:5", s0.toString());
    }

//    /**
//     * Tests toString for an empty queue
//     */
//    @Test(expected = AssertionError.class)
//    public void testToStringFail() {
//        assertEquals("[]:5", s0.toString());
//        fail();
//    }
}
