package com.leetcode.dp;

public class DecodeWays {

    // time: o(N)
    // space: O(N)
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;

        int[] dp = new int[s.length()+1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;

        for (int i=2; i<s.length()+1; i++) {
            int single = s.charAt(i-1) - '0';
            if (single > 0 && single <= 9) {
                dp[i] = dp[i-1];
            }
            int two = Integer.parseInt(s.substring(i-2,i));
            if (two >= 10 && two <= 26) {
                dp[i] = dp[i] + dp[i-2];
            }
        }
        return dp[s.length()];
    }

    // time: o(n), space: o(1)
    public int numWays(String s) {
        if (s == null || s.length() == 0) return 0;

        int prev_prev = 1;
        int prev = s.charAt(0) == '0' ? 0 : 1;
        int curr = prev;

        for (int i=2; i<s.length()+1; i++) {
            int single = s.charAt(i-1) - '0';
            if (single > 0 && single <= 9) {
                curr = prev;
            }
            else {
                curr = 0;
            }
            int two = Integer.parseInt(s.substring(i-2,i));
            if (two >= 10 && two <= 26) {
                curr = curr + prev_prev;
            }
            prev_prev = prev;
            prev = curr;
        }
        return curr;
    }
}
