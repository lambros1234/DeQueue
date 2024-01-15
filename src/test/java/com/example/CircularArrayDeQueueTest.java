package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Iterator;

public class CircularArrayDeQueueTest {

    @Test
    public void testPushAndPop() {
        // Test pushing elements to both ends and popping them
        CircularArrayDeQueue<Integer> deque = new CircularArrayDeQueue<>();

        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());

        deque.pushFirst(1);
        deque.pushLast(2);

        assertFalse(deque.isEmpty());
        assertEquals(2, deque.size());
        assertEquals(Integer.valueOf(1), deque.popFirst());
        assertEquals(Integer.valueOf(2), deque.popLast());

        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
    }

    @Test
    public void testFirstAndLast() {
        // Test accessing the first and last elements
        CircularArrayDeQueue<String> deque = new CircularArrayDeQueue<>();

        deque.pushFirst("first");
        deque.pushLast("last");

        assertEquals("first", deque.first());
        assertEquals("last", deque.last());

        deque.popFirst();
        deque.popLast();

        assertTrue(deque.isEmpty());
    }

    @Test
    public void testIterator() {
        // Test iterating over the elements
        CircularArrayDeQueue<Character> deque = new CircularArrayDeQueue<>();

        deque.pushFirst('A');
        deque.pushLast('B');
        deque.pushLast('C');
        Iterator<Character> iterator = deque.iterator();
        StringBuilder result = new StringBuilder();
        while (iterator.hasNext()) {
            result.append(iterator.next());
        }

        assertEquals("ABC", result.toString());
    }

    @Test
    public void testResize() {
        // Test resizing of the circular array
        CircularArrayDeQueue<Integer> deque = new CircularArrayDeQueue<>();

        for (int i = 1; i <= 10; i++) {
            deque.pushLast(i);
        }

        assertEquals(10, deque.size());

        for (int i = 1; i <= 5; i++) {
            deque.popFirst();
        }

        assertEquals(5, deque.size());
        assertFalse(deque.isEmpty());
    }

}