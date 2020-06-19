//package percolation;
/**
 * Percolation
 *
 * @author Cheng Qian
 */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int N;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufExcludeBottom;
    private boolean[][] grid;
    private int top;
    private int bottom;
    private int numOpen;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        if (n <= 0){
            throw new IllegalArgumentException();
        }
        numOpen = 0;
        N = n;
        top = 0;
        bottom = 1 + N * N;
        grid = new boolean[N][N];
        uf = new WeightedQuickUnionUF(N*N + 2);
        ufExcludeBottom = new WeightedQuickUnionUF(N*N + 1);
        for (int i=0;i<N;i++){
            uf.union(top, getIndex(0, i));
            ufExcludeBottom.union(top, getIndex(0, i));
            uf.union(bottom, getIndex(N - 1, i));
        }
    }
    private int getIndex(int row, int col){
        return row * N + col + 1;
    }
    // opens the site (row, col) if it is not open already
    public void open(int row, int col){

        int row_index_0 = row - 1;
        int col_index_0 = col - 1;

        if ((row_index_0 < 0) || (row_index_0 >= N) || (col_index_0 < 0) || (col_index_0 >= N)){
            throw new IllegalArgumentException();
        }
        if (!grid[row_index_0][col_index_0]) {
            grid[row_index_0][col_index_0] = true;
            numOpen += 1;

            if ((row_index_0 - 1) >= 0 && isOpen(row - 1, col)) {
                uf.union(getIndex(row_index_0, col_index_0), getIndex(row_index_0 - 1, col_index_0));
                ufExcludeBottom.union(getIndex(row_index_0, col_index_0), getIndex(row_index_0 - 1, col_index_0));
            }
            if ((row_index_0 + 1) < N && isOpen(row + 1, col)) {
                uf.union(getIndex(row_index_0, col_index_0), getIndex(row_index_0 + 1, col_index_0));
                ufExcludeBottom.union(getIndex(row_index_0, col_index_0), getIndex(row_index_0 + 1, col_index_0));
            }
            if ((col_index_0 - 1) >= 0 && isOpen(row, col - 1)) {
                uf.union(getIndex(row_index_0, col_index_0), getIndex(row_index_0, col_index_0 - 1));
                ufExcludeBottom.union(getIndex(row_index_0, col_index_0), getIndex(row_index_0, col_index_0 - 1));
            }
            if ((col_index_0 + 1) < N && isOpen(row, col + 1)) {
                uf.union(getIndex(row_index_0, col_index_0), getIndex(row_index_0, col_index_0 + 1));
                ufExcludeBottom.union(getIndex(row_index_0, col_index_0), getIndex(row_index_0, col_index_0 + 1));
            }
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        int row_index_0 = row - 1;
        int col_index_0 = col - 1;

        if ((row_index_0 < 0) || (row_index_0 >= N) || (col_index_0 < 0) || (col_index_0 >= N)){
            throw new IllegalArgumentException();
        }
        return grid[row_index_0][col_index_0];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        int row_index_0 = row - 1;
        int col_index_0 = col - 1;

        if ((row_index_0 < 0) || (row_index_0 >= N) || (col_index_0 < 0) || (col_index_0 >= N)){
            throw new IllegalArgumentException();
        }

        return isOpen(row, col) && ufExcludeBottom.connected(top, getIndex(row_index_0, col_index_0));
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return numOpen;
    }

    // does the system percolate?
    public boolean percolates(){
        // corner cases
        return numOpen >= N && uf.connected(top, bottom);
    }

    // test client (optional)
    public static void main(String[] args){

    }
}
