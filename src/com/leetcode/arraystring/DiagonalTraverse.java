package com.leetcode.arraystring;

import java.util.Arrays;

public class DiagonalTraverse {

    public int[] findDiagonalOrder(int[][] mat) {
        int row_size = mat.length;
        int col_size = mat[0].length;

        int[] res = new int[row_size * col_size];
        int population_size = 0;

        int r = 0, c = 0;
        int direction = 1;

        while (population_size < res.length) {
            res[population_size++] = mat[r][c];

            if (direction == 1) {
                // up
                if (c == col_size - 1) {
                    r++;
                    direction = -1;
                }
                else if (r == 0) {
                    c++;
                    direction = -1;
                }
                else {
                    r--;
                    c++;
                }
            }
            else {
                // down
                if (r == row_size - 1) {
                    c++;
                    direction = 1;
                }
                else if (c == 0) {
                    r++;
                    direction = 1;
                }
                else {
                    c--;
                    r++;
                }
            }
        }
        return res;
    }

    public static void main(String[] argv) {
        DiagonalTraverse d = new DiagonalTraverse();

        System.out.println(Arrays.toString(d.findDiagonalOrder(new int[][]{
                new int[]{1, 2, 3},
                new int[]{4, 5, 6},
                new int[]{7, 8, 9}}))
        );
        System.out.println(Arrays.toString(d.findDiagonalOrder(new int[][]{
                new int[]{1, 2, 3, 4, 5},
                new int[]{6, 7, 8, 9, 10},
                new int[]{11, 12, 13, 14, 15},
                new int[]{16, 17, 18, 19, 20}}))
        );
    }
}
