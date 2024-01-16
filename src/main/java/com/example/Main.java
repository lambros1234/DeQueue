package com.example;

import java.util.Iterator;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        DeQueue<Integer> deque = new CircularArrayDeQueue<>();

        // Test the DeQueue operations
        System.out.println("Pushing to front:");
        for(int i = 0; i < 5; i++) {
            deque.pushFirst(i);   
        }
        deque.printDequeStatus();
    

        System.out.println("\nDeque elements using Iterator:");
        Iterator<Integer> iterator = deque.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();

        System.out.println("\nPop first element: " + deque.popFirst());
        System.out.println("Pop last element: " + deque.popLast());
        System.out.println("Pop last element again: " + deque.popLast() + "\n");

        deque.printDequeStatus();
        System.out.println("Updated Deque size: " + deque.size() + "\n");
    

        System.out.println("\nPushing to last:");
        for (int i = 5; i < 12; i++) {
            deque.pushLast(i);
        }
        deque.printDequeStatus();


        System.out.println("\nDeque elements in reverse using Decsending Iterator:");
        Iterator<Integer> descendingIterator = deque.descendingIterator();
        while (descendingIterator.hasNext()) {
            System.out.print(descendingIterator.next() + " ");
        }
        System.out.println();


        System.out.println("\nUpdated Deque size: " + deque.size() + "\n");
        // Clear the DeQueue
        deque.clear();
        System.out.println("Cleared Deque size: " + deque.size());
        
        
    }

}
