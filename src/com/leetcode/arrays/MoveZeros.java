package com.leetcode.arrays;

public class MoveZeros {

    // space: O(1), time O(N)
    public void moveZeroes(int[] nums) {

        // two pointer
        int j = 0;
        for (int i=0; i<nums.length; i++) {
            // if i = non-zero, just copy it to j
            if (nums[i] != 0) {
                nums[j] = nums[i];
                j++;
            }
        }

        // populate remaining with zeros
        while (j < nums.length) {
            nums[j] = 0;
            j++;
        }
    }

}
