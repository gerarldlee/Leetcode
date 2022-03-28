package com.leetcode.facebook;

public class ClimbingStairs {

    public int climbStairs(int n) {
        int one = 1;
        int two = 2;

        if (n < 3) return n;

        int ans = 0;
        // start with 2, which means the 3rd fibonacci, from 0th index i
        // since we already returned one, two-nd fibonacci
        // which is just the same as n
        for (int i=2; i<n; i++) {
            ans = two + one;

            one = two;
            two = ans;
        }
        return ans;
    }
}
