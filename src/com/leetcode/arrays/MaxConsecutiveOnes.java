package com.leetcode.arrays;

/**
 * Test case
 * [1,0,1,1,0] - 4
 * [1,0,1,1,0,1] - 4
 * [1,0,0,1,1,0] - 3
 * [1,0,1,1,0,1,1,1] - 6
 * [0,0,0,1,0,1,1,0] - 4
 * [0] - 1
 * [0,0] - 1
 * [0,1] - 2
 */
public class MaxConsecutiveOnes {

    // space o(1), time o(n)
    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums.length < 2) return 1;
        int max = 0;
        int last_zero_index = 0;
        int count = 0;
        boolean zero_init = false;
        for (int i=0; i<nums.length; i++) {
            if (nums[i] == 0) {
                if (zero_init) count = i-(last_zero_index+1);    // begin counting from the last zero index
                last_zero_index = i;
                zero_init = true;
            }
            count++;
            max = Math.max(max, count);
        }
        return max;
    }

    // sliding window, time o(n), space o(1)
    public int findMaxConsecutiveOnes1(int[] nums) {
        if (nums.length < 2) return 1;
        int left_idx = 0, right_idx = 0, zeros = 0;
        int max = 0;
        while (right_idx < nums.length) {
            if (nums[right_idx] == 0) zeros++;
            while (zeros > 1) {
                if (nums[left_idx] == 0) zeros--;
                left_idx++;
            }
            max = Math.max(max, right_idx - left_idx + 1);
            right_idx++;
        }
        return max;
    }
}
