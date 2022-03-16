package com.codility;

import java.util.Arrays;

public class DynamicProgramming {


    public int[] dp(int[] C, int K) {
        int n = C.length;
        int[][] dp = new int[n+1][K+1];
        Arrays.fill(dp[0], Integer.MAX_VALUE);
        dp[0][0] = 0;

        int[] dp_optimize = new int[K+1];
        Arrays.fill(dp_optimize, Integer.MAX_VALUE);
        dp_optimize[0] = 0;

        for (int i=1; i<n+1; i++) {
            int c = C[i-1];
//
//            for (int j=0; j < c; j++) {
//                dp[i][j] = dp[i-1][j];
//            }
//
//            for (int j=c; j < K+1; j++) {
//                int a = dp[i][j - c] + 1;
//                int b = dp[i - 1][j];
//                dp[i][j] = Math.min(a,b);
//            }
//          return dp[n];

            // space - optimize version
            for (int j=c; j<K+1; j++) {
                int a = dp_optimize[j - c] + 1;
                int b = dp_optimize[j];
                dp_optimize[j] = Math.min(a, b);
            }
        }
        return dp_optimize;
    }

    public static void main(String[] argv) {
        DynamicProgramming dp = new DynamicProgramming();
        System.out.println(Arrays.toString(dp.dp(new int[] {1,3,4}, 6)));
    }
}
