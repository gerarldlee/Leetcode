package com.algomonster.dp;

public class EditDistance {
    public int minDistance(String word1, String word2) {
        return dfs(word1, word1.length(), word2, word2.length());
    }

    private int dfs(String word1, int m, String word2, int n) {
        // base case
        if (m == 0) return n;
        if (n == 0) return m;

        int change = (word1.charAt(m - 1) == word2.charAt(n - 1)) ? 0 : 1;

        // minimum insert or delete
        int min = Math.min(dfs(word1, m - 1, word2, n) + 1, dfs(word1, m, word2, n - 1) + 1);
        // minimum replace
        return Math.min(min, dfs(word1, m - 1, word2, n - 1) + change);
    }
}
