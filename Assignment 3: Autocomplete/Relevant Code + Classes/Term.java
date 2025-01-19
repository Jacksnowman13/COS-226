import java.util.Comparator;


public class Term implements Comparable<Term> {
    // initializing the query
    private String query;
    // and the weight
    private long weight;


    // Initializes a term with the given query string and weight.
    public Term(String query, long weight) {
        if (query == null || weight < 0) throw
                new IllegalArgumentException("Inputs are invalid");
        this.query = query;
        this.weight = weight;

    }

    // Compares the two terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder() {
        OrderReverseWeight orderReverseWeight = new OrderReverseWeight();
        return orderReverseWeight;
    }

    // Compares the two terms in lexicographic order,
    // but using only the first r characters of each query.
    public static Comparator<Term> byPrefixOrder(int r) {
        if (r < 0) throw new IllegalArgumentException("Argument is invalid");
        Comparator<Term> comparePrefixOrder = new ComparePrefixOrder(r);
        return comparePrefixOrder;


    }

    // creating a private class to support the byReverseWeightOrder
    private static class OrderReverseWeight implements Comparator<Term> {
        // compares the two weights, and returns a value
        // based on if one is greater than the other
        public int compare(Term term1, Term term2) {
            // do term2 first because it is reverse order
            int returnValue = (int) (term2.weight - term1.weight);
            return Integer.compare(returnValue, 0);


        }
    }

    // Again, private static class to support the byPrefixOrder
    private static class ComparePrefixOrder implements Comparator<Term> {
        // Initializing a private integer to use later
        private int lengthOfPrefix;

        // setting lengthOfPrefix to the input r
        public ComparePrefixOrder(int r) {
            this.lengthOfPrefix = r;
        }

        // Compares the two terms queries and the first r in the queries, returning
        // which is greater according to lexicographic order
        public int compare(Term term1, Term term2) {
            String shortened1 = term1.query;
            String shortened2 = term2.query;
            // going through each character 1 by 1
            for (int i = 0; i < lengthOfPrefix; i++) {
                // dealing with the corner cases of the input being less than
                // the length of prefix
                if (i >= shortened1.length() && i >= shortened2.length()) return 0;
                if (i >= shortened2.length()) return 1;
                if (i >= shortened1.length()) return -1;

                if (shortened1.charAt(i) < shortened2.charAt(i)) return -1;
                if (shortened1.charAt(i) > shortened2.charAt(i)) return 1;

            }
            // returning 0 because if nothing returned in the previous statement,
            // then they are the same
            return 0;


        }


    }


    // Compares the two terms in lexicographic order by query.
    public int compareTo(Term that) {
        return this.query.compareTo(that.query);


    }

    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString() {
        return this.weight + "\t" + this.query;

    }

    // unit testing (required)
    public static void main(String[] args) {
        Term dog = new Term("dog", 7);
        Term dogcatcher = new Term("dogcatcher", 5);
        Term d = new Term("d", 12);
        Term p = new Term("CGT", 1);
        Term q = new Term("GGA", 0);


        System.out.println(dog.compareTo(d));
        System.out.println(dog.compareTo(dog));
        System.out.println(dog.compareTo(dogcatcher));
        System.out.println(byPrefixOrder(4).compare(dog, dogcatcher));
        System.out.println(byPrefixOrder(3).compare(dog, dogcatcher));
        System.out.println(byPrefixOrder(4).compare(dog, d));
        System.out.println(byReverseWeightOrder().compare(dogcatcher, dog));
        System.out.println(byReverseWeightOrder().compare(d, dog));
        System.out.println(byReverseWeightOrder().compare(p, q));

    }

}
