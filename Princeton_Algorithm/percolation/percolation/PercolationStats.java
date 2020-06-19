//package percolation;
/**
 * Percolation
 *
 * @author Cheng Qian
 */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {

    private double[] result;
    private Double m;
    private Double std;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
        if (n<=0 || trials<=0){
            throw new IllegalArgumentException();
        }
        result = new double[trials];
        for (int i=0;i<trials;i++){
            Percolation p = new Percolation(n);
            while (!p.percolates()){
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);

                p.open(row, col);

            }
            result[i] = (double) p.numberOfOpenSites() / (n * n);
        }
    }
    // order of calling these functions should give same results
    // sample mean of percolation threshold
    public double mean(){
        if (m == null){
            m = StdStats.mean(result);
        }
        return m;
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        if (std == null) {
            std = StdStats.stddev(result);
        }
        return std;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        if (m == null){
            mean();
        }
        if (std == null){
            stddev();
        }
        return m - 1.96 * std/Math.sqrt(result.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        if (m == null){
            mean();
        }
        if (std == null){
            stddev();
        }
        return m + 1.96 * std/Math.sqrt(result.length);
    }

    // test client (see below)
    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
//        int n = 200;
//        int trials = 100;
        PercolationStats ps = new PercolationStats(n, trials);
        System.out.println(ps.mean());
        System.out.println(ps.stddev());
        System.out.print(ps.confidenceLo());
        System.out.print(", ");
        System.out.print(ps.confidenceHi());

    }
}
