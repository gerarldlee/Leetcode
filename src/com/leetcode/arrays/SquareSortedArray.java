package com.leetcode.arrays;

import java.util.Arrays;

public class SquareSortedArray {

    // time: O(n log(n)) due to array sort
    // space: o(log N) - only the array sort
    public int[] sortedSquares(int[] nums) {
        for (int i=0; i<nums.length; i++) {
            nums[i] *= nums[i];
        }
        Arrays.sort(nums);
        return nums;
    }
}
