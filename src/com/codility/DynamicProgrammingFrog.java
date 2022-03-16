package com.codility;

import java.util.Arrays;

public class DynamicProgrammingFrog {

    /**
     * Frog jump from 1 to k, using steps found in S
     *
     */
    public int frog(int[] S, int K, int Q) {
        int n = S.length;

        int[] dp = new int[K+1];
        dp[0] = 1;

        // iterate over the K
        for (int j = 1; j < K+1; j++) {
            // iterate over the steps
            for (int i=0; i < n; i++) {
                if (S[i] <= j) {    // only when each step is below J we can be sure it will not be overflow
                    dp[j] = dp[j] + (dp[j - S[i]]) ; // % Q;
                }
            }
        }

        return dp[K];
    }

    public static void main(String[] argv) {
        DynamicProgrammingFrog dp = new DynamicProgrammingFrog();
        System.out.println(dp.frog(new int[] {1,3,4}, 6, 2));
    }
}
