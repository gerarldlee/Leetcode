package com.leetcode.arrays;

public class FindEvenDigits {

    // time: O(N), space O(1)
    public int findNumbers(int[] nums) {

        int count = 0;
        for (int i=0; i<nums.length; i++) {
            String tmp = String.valueOf(nums[i]);
            if (tmp.length() % 2 == 0) {
                count++;
            };
        }
        return count;
    }
}
