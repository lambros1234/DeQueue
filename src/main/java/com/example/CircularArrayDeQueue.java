package com.example;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularArrayDeQueue<E> implements DeQueue<E> {
    private static final int DEFAULT_CAPACITY = 8;
    private int size;
    private int f; //front
    private int r; //rear
    private E[] array;
    private volatile int modCount; // Modification count for iterator safety

    public CircularArrayDeQueue() {
        this.size = 0;
        this.f = 0; //initialize front with -1 
        this.r = 0;
        this.array = (E[]) new Object[DEFAULT_CAPACITY];
    }


    @Override
    public void pushFirst(E elem) {
        if (size == array.length) {
            doubleCapacity();
        } else if (isEmpty()) { // If it's empty, push to the first 
            f = 0;
        } else {
            f = (f - 1 + array.length) % array.length;
        }
    
        array[f] = elem;
        size++;
        modCount++;
    }
    

    @Override
    public void pushLast(E elem) {
        if (size == array.length) {
            doubleCapacity();
        }
        array[r] = elem;
        r = (r + 1)%array.length;
        size++;
        modCount++;
    }

    @Override
    public E popFirst() {
        
        return null; 
    }

    @Override
    public E popLast() {
        
        return null; 
    }

    @Override
    public E first() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return (E) array[f];
    }

    @Override
    public E last() {
       
        return null; 
    }

    @Override
    public boolean isEmpty() {
        return f == r; 
    }

    @Override
    public int size() {
       
        return 0; 
    }

    @Override
    public void clear() {
        
    }

    @Override
    public Iterator<E> iterator() {
        
        return new Iterator<E>() {
            private int current = f; // Initialize the iterator starting position at the front 

            private final int lastReturnedModCount = modCount; // Keep track of the modification count for iterator safety

            @Override
            public boolean hasNext() { // Check if the current position is not equal to the rear
                return current != r;
            }

            @Override
            public E next() {
                checkForComodification();// Check for concurrent modification

                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                E result = array[current];
                current = (current + 1) % array.length; // Move to the next position with a circular increment
                return result; // Retrive element
            }

            private void checkForComodification() {
                if (modCount != lastReturnedModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    @Override
    public Iterator<E> descendingIterator() {
        // Your implementation
        return null; // Placeholder, replace with the actual return statement
    }

    private void doubleCapacity() {
        int newCapacity = 2 * array.length;
        E[] newArray = (E[]) new Object[newCapacity];

        for(int i = 0; i < size ; i++) {
            newArray[i] = array[(f + i) % array.length];
        }
        array = newArray;
        f = 0;
        r = size;
    }


}