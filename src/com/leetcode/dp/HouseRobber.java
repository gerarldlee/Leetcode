package com.leetcode.dp;

public class HouseRobber {

    // time: o(n)
    // space: o(1)
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int curr = nums[0];
        int prev = curr;
        int prev_prev = 0;

        for (int i=1; i<nums.length; i++) {
            curr = Math.max(prev,  nums[i] + prev_prev);
            prev_prev = prev;
            prev = curr;
        }

        return curr;
    }
}
