import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    // Confidence interval value
    private static final double CONFIDENCE_95 = 1.96;

    // average to be used later on in the instance method
    private double realavg;
    // standard deviation to used later on in the instance method
    private double realStd;
    // number of trials to used later on in the instance methods
    private int trials;

    // Stats of the percolation class
    public PercolationStats(int n, int trials) {
        this.trials = trials;
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("Use different arguments)");
        double[] avgs = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation sim = new Percolation(n);
            double counter = 0;
            while (!sim.percolates()) {
                int x = StdRandom.uniformInt(0, n);
                int y = StdRandom.uniformInt(0, n);

                if (!sim.isOpen(x, y)) {
                    sim.open(x, y);
                    counter++;
                }
            }
            avgs[i] = (counter / (n * n));
        }
        realavg = StdStats.mean(avgs);
        realStd = StdStats.stddev(avgs);


    }


    // sample mean of percolation threshold
    public double mean() {
        return realavg;

    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return realStd;

    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return realavg - (CONFIDENCE_95 * realStd) / Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return realavg + (CONFIDENCE_95 * realStd) / Math.sqrt(trials);


    }

    public static void main(String[] args) {
        Stopwatch time = new Stopwatch();
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats poop = new PercolationStats(n, trials);
        System.out.println("mean() = " + poop.mean());
        System.out.println("stddev = " + poop.stddev());
        System.out.println("confidenceLow() = " + poop.confidenceLow());
        System.out.println("confidenceHigh() = " + poop.confidenceHigh());
        System.out.println("elapsed time = " + time.elapsedTime());

    }
}
