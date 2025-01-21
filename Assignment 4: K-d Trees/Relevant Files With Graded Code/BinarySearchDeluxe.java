import edu.princeton.cs.algs4.StdRandom;

import java.util.Comparator;

public class BinarySearchDeluxe {

    // Returns the index of the first key in the sorted array a[]
    // that is equal to the search key, or -1 if no such key.
    public static <Key> int
    firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) throw new
                IllegalArgumentException("Arguments are invalid");
        // markers to indicate where the search is bounded by
        int lowMarker = 0;
        int highMarker = a.length - 1;
        // will use later to determine the first instance of the key
        int lastInstance = -1;
        // going through and determining if the middle of the bounds
        // are greater, less than, or equal to the key
        while (lowMarker <= highMarker) {
            int mid = (highMarker + lowMarker) >>> 1;
            int compare = comparator.compare(a[mid], key);
            if (compare < 0) lowMarker = mid + 1;
            else if (compare > 0) highMarker = mid - 1;
                // once it finds the key, it then performs another binary search
                // of the first instance of the key by setting the high marker
                // as mid-1 and looking from there, since we know mid is an instance
                // of key
            else {
                lastInstance = mid;
                highMarker = mid - 1;
            }

        }
        return lastInstance;
    }

    // Returns the index of the last key in the sorted array a[]
    // that is equal to the search key, or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        // markers to indicate where the search is bounded by
        if (a == null || key == null || comparator == null) throw new
                IllegalArgumentException("Arguments are invalid");
        int lowMarker = 0;
        int highMarker = a.length - 1;
        // will use later to determine the first instance of the key
        int firstInstance = -1;
        // going through and determining if the middle of the bounds
        // are greater, less than, or equal to the key
        while (lowMarker <= highMarker) {
            int mid = (highMarker + lowMarker) >>> 1;
            int compare = comparator.compare(a[mid], key);
            if (compare < 0) lowMarker = mid + 1;
            else if (compare > 0) highMarker = mid - 1;
                // once it finds the key, it then performs another binary search
                // of the last instance of the key by setting the low marker
                // as mid+1 and looking from there, since we know mid is an instance
                // of key
            else {

                firstInstance = mid;
                lowMarker = mid + 1;
            }
        }
        return firstInstance;
    }


    // unit testing (required)
    public static void main(String[] args) {
        // Unit testing
        // Input an integer n, creates an integer array of numbers 1 through n
        // 5 times and then brute forces where the first and last should be
        // of a random key and then uses the above methods to check they are right

        int input = Integer.parseInt(args[0]);
        Integer[] testArray = new Integer[5 * input];
        int key = StdRandom.uniformInt(0, input + 1);

        for (int i = 1; i <= input; i++) {
            for (int j = 0; j < 5; j++) {
                testArray[(5 * (i - 1)) + j] = i;
            }
        }
        System.out.println(key);
        int i = 0;
        while (i < testArray.length) {
            if (testArray[i] == key) {
                System.out.println("First instance is " + i);
                break;
            }
            else i++;
        }

        int i1 = testArray.length - 1;

        while (i1 > 0) {
            if (testArray[i1] == key) {
                System.out.println("Last instance is " + i1);
                break;
            }
            else i1--;
        }


        System.out.println(key);
        System.out.println(lastIndexOf(testArray, key, Integer::compareTo));
        System.out.println(firstIndexOf(testArray, key, Integer::compareTo));


    }


}


