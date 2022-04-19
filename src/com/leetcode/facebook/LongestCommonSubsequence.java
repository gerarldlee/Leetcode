package com.leetcode.facebook;

import java.util.Arrays;

public class LongestCommonSubsequence {


    // time: O(N + M)
    // space: O(N + M)
    public int longestCommonSubsequence(String text1, String text2) {

        int[][] memo = new int[text1.length()][text2.length()];
        for (int[] m : memo) {
            Arrays.fill(m, -1);
        }

        return lcs(text1, text2, 0, 0, memo);
    }

    private int lcs(String text1, String text2, int i, int j, int[][] memo) {

        if (i >= text1.length() || j >= text2.length()) return 0;

        if (memo[i][j] != -1) return memo[i][j];

        char a = text1.charAt(i);
        char b = text2.charAt(j);

        if (a == b) {
            return 1 + lcs(text1, text2, i+1, j+1, memo);
        }
        else {
            int x = lcs(text1, text2, i+1, j, memo);
            int y = lcs(text1, text2, i, j+1, memo);
            int res = Math.max(x, y);
            memo[i][j] = res;
            return res;
        }
    }
}
