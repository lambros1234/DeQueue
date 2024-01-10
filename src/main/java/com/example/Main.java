package com.example;

import java.util.Iterator;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) {

        
        CircularArrayDeQueue queue = new CircularArrayDeQueue();


        for(int i = 0; i < 10; i++) {
            queue.pushFirst(i);   
        }

        System.out.println(Arrays.toString(queue.array));
        System.out.println("Front is: " + queue.first());
        System.out.println("Rear is: " + queue.last());
        System.out.println();


        

        /*System.out.println("Add 3 elements");
        queue.pushFirst(11);
        queue.pushLast(22);
        queue.pushLast(44);
        queue.pushFirst(33);
        queue.printQueue();
        queue.printArray();
        System.out.println();

        System.out.println("Remove 1 element from rear");
        System.out.println("Remove: " + queue.last());
        queue.popLast();
        queue.printQueue();
        queue.printArray();
        System.out.println();

        System.out.println("Add 1 more element at rear");
        queue.pushLast(45);
        queue.printQueue();
        queue.printArray();
        System.out.println();

        System.out.println("Add 1 more element at front");
        queue.pushFirst(55);
        queue.printQueue();
        queue.printArray();
        System.out.println();

        System.out.println("Remove 1 element from rear");
        System.out.println("Remove: " + queue.last());
        queue.popLast();
        queue.printQueue();
        queue.printArray();
        System.out.println();


        System.out.println("Pop twice from front");
        System.out.println("Pop: " +  queue.first());
        queue.popFirst();
        queue.printQueue();
        queue.printArray();
        System.out.println("Pop: " + queue.first());
        queue.popFirst();
        queue.printQueue();
        queue.printArray();
        queue.printQueue();
        queue.printArray();
        System.out.println();



        System.out.println("Add 4 more elements");
        queue.pushFirst(66);
        queue.pushFirst(77);
        queue.pushFirst(88);
        queue.pushFirst(99);
        queue.pushLast(100);
        queue.pushLast(101);

        queue.printQueue();
        queue.printArray();

        System.out.println("Add 1 element to front");
        queue.pushFirst(200);
        queue.printQueue();
        queue.printArray();
        System.out.println();;
        System.out.println("Add 1 element to rear");
        queue.pushLast(203);
        queue.printQueue();
        queue.printArray();
        System.out.println();

        System.out.println("Queue elements using iterator:");
        Iterator<Integer> iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    


        System.out.println(queue.size());
        System.out.println(queue.getSize());*/
    }

}
