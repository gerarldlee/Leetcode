package com.leetcode.recursion;

public class Fibonacci {

    // We do the bottoms up approach where we calculate each fibonacci number starting from the bottom
    // We will save this number and use this to calculate the next.
    // It is said that fibonacci f(n) = f(n-1) + f(n-2)
    // time: o(n)
    // space: o(1)
    public int fib(int n) {
        if (n == 0) return 0;
        int[] memo = new int[2];
        memo[0] = 1;
        memo[1] = 1;
        for (int i=2; i < n; i++) {
            int fibN = memo[0] + memo[1];
            memo[1] = memo[0];
            memo[0] = fibN;
        }
        return memo[0];
    }

    // use the golden's ratio (binets formula)
    // time: o(1) + o(log N) from goldenratio ^ N
    // space: o(1)
    public int fib1(int N) {
        double goldenRatio = (1 + Math.sqrt(5)) / 2;
        return (int) Math.round(Math.pow(goldenRatio, N) / Math.sqrt(5));
    }

}
