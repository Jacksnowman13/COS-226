import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // boolean grid of open or closed sites
    private boolean[][] grid;
    // value of one side of the grid
    private int size;
    // Weighted Quick Union Find object to use for better efficiency
    private WeightedQuickUnionUF set;
    // counter of the number of open sites
    private int counter;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("N is a negative number");
        }
        else {
            size = n;
            grid = new boolean[n][n];
            set = new WeightedQuickUnionUF((n * n) + 2);
        }
    }

    // private method to convert row and col numbers into integer representing a site
    private int converter(int row, int col) {
        return ((row * size) + col);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row >= size || row < 0 || col >= size || col < 0)
            throw new IllegalArgumentException();
        if (!grid[row][col]) {
            counter++;
            grid[row][col] = true;
            if (row == 0) {
                set.union(converter(row, col), size * size);
            }
            if (row == size - 1) {
                set.union(converter(row, col), size * size + 1);
            }
            if ((row + 1) <= (size - 1)) {
                if (grid[row + 1][col]) {
                    set.union(converter(row, col), converter(row + 1, col));
                }
            }
            if ((row - 1) > -1) {
                if (grid[row - 1][col]) {
                    set.union(converter(row, col), converter(row - 1, col));
                }
            }
            if ((col + 1) <= (size - 1)) {
                if (grid[row][col + 1]) {
                    set.union(converter(row, col), converter(row, col + 1));
                }
            }
            if ((col - 1) > -1) {
                if (grid[row][col - 1]) {
                    set.union(converter(row, col), converter(row, col - 1));
                }
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row >= size || row < 0 || col >= size || col < 0)
            throw new IllegalArgumentException();
        return grid[row][col];

    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row >= size || row < 0 || col >= size || col < 0)
            throw new IllegalArgumentException();
        return set.find(converter(row, col)) == set.find((size * size));


    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return counter;

    }

    // does the system percolate?
    public boolean percolates() {

        return set.find((size * size)) == set.find((size * size) + 1);

    }

    // unit testing (required)
    public static void main(String[] args) {
        Percolation simulation = new Percolation(3);
        simulation.open(2, 2);
        simulation.open(1, 0);
        simulation.open(2, 0);
        System.out.println(simulation.isOpen(2, 0));
        System.out.println(simulation.isFull(2, 2));
        System.out.println(simulation.percolates());
        System.out.println(simulation.numberOfOpenSites());


    }
}
