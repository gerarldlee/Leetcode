package com.leetcode.recursion;

import java.util.ArrayList;
import java.util.List;

public class Pascal2 {

    // Intuition
    // Pascal triangles formula is f(i,j) = f(i-1,j-1) + f(i-1,j)
    // given the row index, i. Make a recursive calls to obtain the preceding sums
    // the base formula for pascals triangle is when i == j, its 1, or when j == 1, then 1
    // from this, we can add every i,j combination into a new list

    // without memo
    // time: o(n^2) or o((rowindex * j)^2) space: o(rows + depth)^2 also redundantly!
    public List<Integer> getRow(int rowIndex) {
        List<Integer> rows = new ArrayList<>(rowIndex + 1);
        int[][] memo = new int[rowIndex+1][rowIndex+2];
        for (int j=0; j<rowIndex+1; j++) {
            rows.add(pascal(rowIndex, j, memo));
        }
        return rows;
    }

    // with memo
    // time: o(n) or o(rowindex * j) and pretty much o(1) for the memo
    // space: o(rows + depth)^2, wont create stack unnecessarily bec of the memo. memo has row x row+1
    private int pascal(int rowIndex, int j, int[][] memo) {
        if (rowIndex == 0 || j == 0 || rowIndex == j) return 1;
        if (memo[rowIndex][j] > 1) return memo[rowIndex][j];
        memo[rowIndex][j] = pascal(rowIndex-1, j-1, memo) + pascal(rowIndex-1,j, memo);
        return memo[rowIndex][j];
    }
}
