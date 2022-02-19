package com.leetcode.arrays;

public class SortArrayByParity {

    // space o(1), time o(n)
    public int[] sortArrayByParity(int[] nums) {

        if (nums.length < 2) return nums;

        // two pointers again
        int left = 0;
        int right = nums.length-1;

        while (left < right) {
            boolean l_even = nums[left] % 2 == 0;
            boolean r_odd = nums[right] % 2 != 0;

            if (l_even) left++;
            if (r_odd) right--;

            if (!l_even && !r_odd) {
                // swap them
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
            }
        }
        return nums;
    }

}
