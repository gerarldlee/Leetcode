package com.leetcode.dp;

import java.util.List;

public class WordBreak {
    public boolean wordBreak(String s, List<String> dict) {
        boolean[] visited = new boolean[s.length() + 1];
        visited[0] = true;

        for (int i=1; i<s.length()+1; i++) {
            for (int j=0; j<i; j++) {
                if (visited[j] && dict.contains(s.substring(j, i))) {
                    visited[i] = true;
                    break;
                }
            }
        }
        return visited[s.length()];
    }
}
