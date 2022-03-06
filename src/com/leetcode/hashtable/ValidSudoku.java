package com.leetcode.hashtable;

import java.util.HashSet;
import java.util.Set;

public class ValidSudoku {

    /*
    Brute:
    Create 3 verifiers:
        - row verifyer, iterate all the rows 9x - o(n^2)
        - column verifyer, iterate all the cols 9x - o(n^2)
        - 3x3 box verifyer, iterate all the boxes 9x - o(n^2)

    Another approach:
    Create a row, cols, and box array with NxN - this will be used to mark a number in a row, col, or box [N][mark]
    Iterate all the row and cols one by one
        - for each value = board[row][col],
            - mark the row if its not been marked; if it is, return false
            - mark the col if its not been marked; if it is, return false
            - mark the box if its not been marked; if it is, return false
                - In order for the box array to be represented, we need to use the formula,
                    - r / 3 for the row - cuz each row has 3 buckets - [0,1,2]
                    - c / 3 for the col - cuz each col has 3 buckets - [0,1,2]
                    - add them, multiply by 3 to represent 2D space - [0,0][0,1][0,2], [1,0][1,1][1,2], [2,0][2,1][2,2]

      Time: o(n^2), Space: o(n^2)
     */
    public boolean isValidSudoku(char[][] board) {
        int N = 9;

        // Use an array to record the status
        int[][] rows = new int[N][N];
        int[][] cols = new int[N][N];
        int[][] boxes = new int[N][N];

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                // Check if the position is filled with number
                if (board[r][c] == '.') {
                    continue;
                }
                int pos = board[r][c] - '1';

                // Check the row
                if (rows[r][pos] == 1) {
                    return false;
                }
                rows[r][pos] = 1;

                // Check the column
                if (cols[c][pos] == 1) {
                    return false;
                }
                cols[c][pos] = 1;

                // Check the box
                int idx = (r / 3) * 3 + c / 3;
                if (boxes[idx][pos] == 1) {
                    return false;
                }
                boxes[idx][pos] = 1;
            }
        }
        return true;
    }

    /*
    Simplified version.
    Time: O(n^2)
    Space: O(N^2)
     */
    public boolean isValidSudoku1(char[][] board) {
        Set<String> set = new HashSet<>();

        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                char val = board[i][j];
                if (val != '.') {
                    // check row
                    if (!set.add("row: " + val + " in " + i)) return false;
                    // check col
                    if (!set.add("col: " + val + " in " + j)) return false;
                    // check box
                    if (!set.add("box: " + val + " in [" + i/3 + "," + j/3 + "]")) return false;
                }
            }
        }
        return true;
    }
}
