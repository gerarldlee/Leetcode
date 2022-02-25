package com.leetcode.recursion;

public class ClimbingStairs {

    // It looks like this is just the nextth fibonacci number
    public int climbStairs(int n) {
        if (n < 2) return n;

        int one = 1, two = 2;
        for (int i=3; i < n+1; i++) {
            int tmp = one + two;
            one = two;
            two = tmp;
        }
        return two;
    }

    public int climbStairs1(int n) {
        double goldenRatio = (1 + Math.sqrt(5)) / 2;
        return (int) Math.round(Math.pow(goldenRatio, n+1) / Math.sqrt(5));
    }
}
