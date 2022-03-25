package com.leetcode.facebook;

public class RangeSum2DImmutable {

    int[][] cummulative_sum;

    public RangeSum2DImmutable(int[][] matrix) {
        cummulative_sum = new int[matrix.length + 1][matrix[0].length + 1];

        for (int r=0; r<matrix.length; r++) {
            for (int c=0; c<matrix[0].length; c++) {
                int A = cummulative_sum[r][c];
                int B = cummulative_sum[r+1][c];
                int C = cummulative_sum[r][c+1];
                cummulative_sum[r+1][c+1] = matrix[r][c] + C + B - A;
            }
        }
    }

    public int sumRegion(int r1, int c1, int r2, int c2) {
        // if (r1 <= 0 || r2 <= 0 || c1 <= 0 || c2 <= 0) return -1;
        // if (c2 < c1) return -1;
        // if (r2 < r1) return -1;

        // just the inverse of constructor
        int A = this.cummulative_sum[r1][c1];
        int B = this.cummulative_sum[r1][c2+1];
        int C = this.cummulative_sum[r2+1][c1];
        int D = this.cummulative_sum[r2+1][c2+1];
        return D - B - C + A;
    }
}
