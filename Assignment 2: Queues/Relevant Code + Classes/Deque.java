import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    // Nodes for the pointing to the first and last objects in the linked list
    private Node first, last;
    // counter to keep track of the size
    private int size;

    private class Node {
        // declaration of item, the item of the node
        private Item item;
        // Linking a node to the next node
        private Node next;
        // Linking a node to the previous node
        private Node previous;

    }


    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;


    }

    // is the deque empty?
    public boolean isEmpty() {
        // if first == null, list is empty
        return first == null;


    }

    // return the number of items on the deque
    public int size() {

        return size;

    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("Cannot add null item");
        // increase size
        size++;
        // create a new node and assign its item to be the added item
        Node newItem = new Node();
        newItem.item = item;

        if (isEmpty()) {
            // if it is empty, last = first and assign the first to be the new item
            first = newItem;
            first.item = newItem.item;
            last = first;

        }
        else {
            // else, keep track of the old first item and push the new first item in.
            Node oldFirst = first;
            first = newItem;
            first.item = item;
            first.next = oldFirst;
            oldFirst.previous = first;

        }


    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("Cannot add null item");
        // increase size and create a new node with the new item as its item
        size++;
        Node newItem = new Node();
        newItem.item = item;

        if (isEmpty()) {
            // if empty first = last and assign last to be the newItem
            last = newItem;
            last.item = newItem.item;
            first = last;

        }
        else {
            // else, keep track of the old last item and push the new first item in
            Node oldLast = last;
            last = newItem;
            last.item = item;
            oldLast.next = last;
            last.previous = oldLast;

        }


    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");
        // edge case of size == 1, make list essentially empty
        if (size == 1) {
            Item item = first.item;
            first = null;
            last = null;
            size = 0;
            return item;
        }
        // else remove item, set first to the node after first,return the old first.
        Item temp = first.item;
        first = first.next;
        first.previous = null;
        size--;
        return temp;


    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");
        if (size == 1) {
            // edge case of size == 1, make list essentially empty
            Item item = first.item;
            first = null;
            last = null;
            size = 0;
            return item;
        }
        // else remove item, set last to the node before last, and return the old last.
        Item item = last.item;
        last = last.previous;
        last.next = null;
        size--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();

    }

    private class DequeIterator implements Iterator<Item> {
        // private node to use during the iterator, current = first
        private Node current = first;

        // hasNext to determine if iterator keeps going
        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            // Iterate through the linked list;
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;

            return item;
        }

    }


    public static void main(String[] args) {

        Deque<Integer> testingDeque = new Deque<Integer>();


        for (int i = 1; i < 11; i++) {
            testingDeque.addFirst(i);
        }
        // System.out.println(testingDeque.size());

        for (int i = 0; i < 10; i++) {
            System.out.println(testingDeque.removeLast());
        }

        for (int i = 1; i < 11; i++) {
            testingDeque.addFirst(i);
        }

        for (Integer item : testingDeque) {
            System.out.println(item);
        }


        System.out.println("Start Test");
        Deque<Integer> deque = new Deque<>();
        deque.addLast(1);
        for (Integer item : deque) {
            System.out.println(item);
        }
        deque.removeLast();
        for (Integer item : deque) {
            System.out.println(item);
        }

        deque.addLast(3);
        deque.addLast(4);
        deque.removeFirst();

        for (Integer item : deque) {
            System.out.println(item);
        }
        System.out.println(deque.isEmpty());
        System.out.println(deque.size());




}
