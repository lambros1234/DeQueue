package com.example;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularArrayDeQueue<E> implements DeQueue<E> {
    private static final int DEFAULT_CAPACITY = 8;
    private int size; //The current number of elements in the circular array deque.
    private int f; //front of dequeue
    private int r; //rear of dequeue
    public E[] array;
    private volatile int modCount; // Modification count for iterator safety

    public CircularArrayDeQueue() {
        this.size = 0;
        this.f = 0;  
        this.r = 0;
        this.array = (E[]) new Object[DEFAULT_CAPACITY];
    }


    @Override
    public void pushFirst(E elem) {
        if (size == array.length) {
            doubleCapacity();
        }
    
        if (isEmpty()) {
            f = array.length - 1; // Set front to the last position if the queue is initially empty
        } else {
            f = (f - 1 + array.length) % array.length; // Move the front position circularly
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
        if (isEmpty()) {
            throw new NoSuchElementException("Que is empty");
        }
        E removedElement = array[f]; // Deleted element
        array[f] = null; 

        f = (f + 1) % array.length;

        size--;
        if(isEmpty()) {
            f = r = 0; // The Queue has become empty
            size = 0;
        }
        if (size <= array.length/4) {
            halfCapacity();
        }
        
        return removedElement; 
    }

    @Override
    public E popLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Que is empty");
        }
        r = (r - 1 + array.length) % array.length;

        E removedElement = array[r];

        array[r] = null;

        size--;

        if(isEmpty()) {
            f = r = 0; // The Queue has become empty
            size = 0;
        }
        if (size <= array.length/4) {
            halfCapacity();
        }
        return removedElement; 
    }

    @Override
    public E first() {

        if (isEmpty()) {
            throw new NoSuchElementException("Que is empty");
        }
        return (E) array[f]; //Return the element at the front of the queue
    }

    @Override
    public E last() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
    
        int lastIndex = (r - 1 + array.length) % array.length;
        return array[lastIndex];
    }

    @Override
    public boolean isEmpty() {
        return size == 0 && f == r;  //The queue is empty when the rear is equal to the front
    }

    @Override
    public int size() {
       return size;
    }

    @Override
    public void clear() {
        f = 0;
        r = 0;
        size = 0;

        array = (E[]) new Object[DEFAULT_CAPACITY];
        modCount++;

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
                checkForComodification(); // Check for concurrent modification

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
        return new Iterator<E>() {
            private int current = (r - 1 + array.length) % array.length;
            private boolean hasPrintedFront = false; //becomes true when the front has been printed

            private final int lastReturnedModCount = modCount;

            @Override
            public boolean hasNext() {
                return !hasPrintedFront; // Check if the current position is not equal to the front or front hasn't been printed
            }

            @Override
            public E next() {
                checkForComodification();

                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                E result = array[current];

                if (current == f) {
                    hasPrintedFront = true; // Set to true after printing the front
                }

                current = (current - 1 + array.length) % array.length;
                return result;
            }

            private void checkForComodification() {
                if (modCount != lastReturnedModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
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

    private void halfCapacity() {
        int oldCapacity = array.length;
        int newCapacity = oldCapacity/2;

        E[] newArray = (E[]) new Object[newCapacity];

        int index = 0;
        for (int i = f; i < f + size; i++){
            newArray[index] = array[i % oldCapacity];
            index++;
        }
        array = newArray;
        f = 0;
        r = size;
    }

    public void printDequeStatus() {
        if (!isEmpty()) {
            System.out.println(Arrays.toString(array));
            System.out.println("Front is: " + first());
            System.out.println("Rear is: " + last());
        } else {
            System.out.println("Deque is empty.");
        }

        System.out.println();
    }
}