import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class Autocomplete {

    // private Term array to be used later
    private Term[] autocompleteTerms;


    // Initializes the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {
        if (terms == null) throw new IllegalArgumentException("Null is not valid");
        // Set the terms in the terms to be the terms in autocompleteTerms
        autocompleteTerms = new Term[terms.length];
        for (int i = 0; i < terms.length; i++) {
            if (terms[i] == null)
                throw new IllegalArgumentException("Null is not valid");
            autocompleteTerms[i] = terms[i];
        }
        // sort these terms automatically
        Arrays.sort(autocompleteTerms);


    }

    // Returns all terms that start with the given prefix,
    // in descending order of weight.
    public Term[] allMatches(String prefix) {
        // if prefix is null throw error
        if (prefix == null) throw
                new IllegalArgumentException("Prefix cannot be null");
        // create a comparator of byPrefixOrder to be used
        Comparator<Term> comparatorOfPrefixes = Term.byPrefixOrder(prefix.length());
        // create an array of all matching terms
        Term[] allMatchingPrefixes;
        // key to be used in binary search
        Term key = new Term(prefix, 0);

        int indexFirst, indexLast;
        // binary search where the prefixes are in the sorted autoCompleteTerms
        indexFirst = BinarySearchDeluxe.firstIndexOf(autocompleteTerms,
                                                     key, comparatorOfPrefixes);
        indexLast = BinarySearchDeluxe.lastIndexOf(autocompleteTerms,
                                                   key, comparatorOfPrefixes);
        // if index is first then allMatchingPrefixes is an array of 0 terms
        if (indexFirst == -1) {
            allMatchingPrefixes = new Term[0];
            return allMatchingPrefixes;
        }
        // if not make the allMatchingPrefixes array the size of the difference
        // in the indexes
        allMatchingPrefixes = new Term[indexLast - indexFirst + 1];
        int index = 0;
        // put them in
        for (int i = indexFirst; i <= indexLast; i++) {
            allMatchingPrefixes[index] = autocompleteTerms[i];
            index++;
        }
        // sort the array by reverse weight
        Comparator<Term> comparatorByWeight = Term.byReverseWeightOrder();

        Arrays.sort(allMatchingPrefixes, comparatorByWeight);

        return allMatchingPrefixes;


    }

    // Returns the number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {
        // if prefix null throw error
        if (prefix == null) throw
                new IllegalArgumentException("Prefix cannot be null");
        // prefixOrderComparator same code used in allMatches
        Comparator<Term> comparatorOfPrefixes = Term.byPrefixOrder(prefix.length());
        Term key = new Term(prefix, 0);

        int indexFirst, indexLast;
        indexFirst = BinarySearchDeluxe.firstIndexOf(autocompleteTerms,
                                                     key, comparatorOfPrefixes);
        indexLast = BinarySearchDeluxe.lastIndexOf(autocompleteTerms,
                                                   key, comparatorOfPrefixes);
        // if none, return 0
        if (indexFirst == -1) {
            return 0;
        }
        // number of matches is just the size of
        // the allMatchingPrefixes array without calling it
        return (indexLast - indexFirst) + 1;
    }

    // unit testing
    public static void main(String[] args) {

        String filename = args[0];
        In in = new In(filename);
        int n = in.readInt();
        Term[] terms = new Term[n];
        for (int i = 0; i < n; i++) {
            long weight = in.readLong();           // read the next weight
            in.readChar();                         // scan past the tab
            String query = in.readLine();          // read the next query
            terms[i] = new Term(query, weight);    // construct the term
        }

        // read in queries from standard input and print the top k matching terms
        int k = Integer.parseInt(args[1]);
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            StdOut.printf("%d matches\n", autocomplete.numberOfMatches(prefix));
            for (int i = 0; i < Math.min(k, results.length); i++)
                StdOut.println(results[i]);
        }


    }

}
