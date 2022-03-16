package com.codility;

public class NumberSolitaire {

    public int solution(int[] A) {

        int N = A.length;

        int[] dp = new int[A.length];
        dp[0] = A[0];       // marked

        for (int i=1; i < N; i++) {
            int max = Integer.MIN_VALUE;
            for (int s=1; s <= 6; s++) {
                if (i - s >= 0) {
                    int a = A[i] + dp[i - s];
                    max = Math.max(a, max);
                }
            }
            dp[i] = max;
        }

        return dp[N-1];
    }

    public static void main(String[] a) {
        NumberSolitaire n = new NumberSolitaire();
        System.out.println(n.solution(new int[] {1,-2,0,9,-1,-2})); // 8
    }
}
