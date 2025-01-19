import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    // entryArray of items in the randomized Queue
    private Item[] entryArray;
    // size of the entryArray
    private int size = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        entryArray = (Item[]) new Object[1];

    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;

    }

    // return the number of items on the randomized queue
    public int size() {
        return size;

    }

    // private void method to resize the original entry array
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++)
            copy[i] = entryArray[i];
        entryArray = copy;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item is null");
        }
        if (size == entryArray.length) resize(2 * entryArray.length);

        entryArray[size++] = item;


    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        int randomInt = StdRandom.uniformInt(size);
        Item returnItem = entryArray[randomInt];
        entryArray[randomInt] = entryArray[--size];
        entryArray[size] = null;
        if (size > 0 && size == entryArray.length / 4) {
            resize(entryArray.length / 2);
        }
        return returnItem;


    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty!");
        int randomInt = StdRandom.uniformInt(size);
        return entryArray[randomInt];

    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();

    }


    private class RandomizedQueueIterator implements Iterator<Item> {
        // a counter for the randomized copy entryArray
        private int counter = 0;
        // A copy of the original entryArray to be shuffled and used in this iterator
        private Item[] copyArray = (Item[]) new Object[size];

        // Put the entries of the original entryArray
        // into a new copy entryArray and shuffle that entryArray
        public RandomizedQueueIterator() {
            for (int i = 0; i < size; i++) {
                copyArray[i] = entryArray[i];
            }
            StdRandom.shuffle(copyArray);

        }

        // check if counter is less than the original entryArray
        public boolean hasNext() {
            return counter < size;
        }

        // return the next element in the randomized copy entryArray
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copyArray[counter++];

        }
    }

    // unit testing (optional)
    public static void main(String[] args) {


    }


}
