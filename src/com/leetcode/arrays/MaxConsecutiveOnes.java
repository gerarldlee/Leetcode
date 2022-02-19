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
        int zero_count = 0;
        int last_zero_index = 0;
        int count = 0;

        for (int i=0; i<nums.length-1; i++) {
            count++;
            if (nums[i] == 0) {
                if (zero_count < 1) {
                    zero_count++;   // initialize the zero count on the first 0
                }
                else {
                    // this is the 2nd zero.
                    max = Math.max(max, count); // record the count
                    count = i - last_zero_index;    // begin counting from the last zero index + 1
                    zero_count = 0; // reset the zero count
                }
                last_zero_index = i;
            }

            max = Math.max(max, count);
        }

        return max;
    }
}
