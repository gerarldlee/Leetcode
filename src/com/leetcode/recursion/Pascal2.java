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
    // time: o(2^rowindex)
    // space: o(2 x rowindex)
    public List<Integer> getRow(int rowIndex) {
        List<Integer> rows = new ArrayList<>(rowIndex + 1);
        int[][] memo = new int[rowIndex + 1][rowIndex + 2];
        for (int j = 0; j < rowIndex + 1; j++) {
            rows.add(pascal(rowIndex, j, memo));
        }
        return rows;
    }

    // with memo
    // time: o(rowindex ^ 2)
    // space: o(2 x rowindex)
    private int pascal(int rowIndex, int j, int[][] memo) {
        if (rowIndex == 0 || j == 0 || rowIndex == j) return 1;
        if (memo[rowIndex][j] > 1) return memo[rowIndex][j];
        memo[rowIndex][j] = pascal(rowIndex - 1, j - 1, memo) + pascal(rowIndex - 1, j, memo);
        return memo[rowIndex][j];
    }

    // The idea is a bottoms up approach where it reuses the List, constructs a new row based from 1,1 values
    // until the desired row is computed. Saving stack, and storage
    // time: o(rowindex ^2) - still the same
    // space: o(rowindex)
    public List<Integer> getRow1(int rowIndex) {
        List<Integer> curr, prev = new ArrayList<>();
        prev.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            curr = new ArrayList<>(i + 1);
            curr.add(1);

            for (int j = 1; j < i; j++) {
                curr.add(prev.get(j - 1) + prev.get(j));
            }
            curr.add(1);
            prev = curr;
        }
        return prev;
    }

    // Intuition
    // Generate the rows bottoms up, using the binomial coefficient formula of Pascal
    public List<Integer> getRowFast(int rowIndex) {
       List<Integer> rows = new ArrayList<>();
       rows.add(1);
       for (int k = 1; k < rowIndex + 1; k++) {
           int value = rows.get(rows.size() - 1) * (rowIndex - k + 1);
           rows.add(value / k);
       }
       return rows;
    }

    public static void main(String[] a) {
        Pascal2 p = new Pascal2();
        p.getRowFast(5);
    }
}
