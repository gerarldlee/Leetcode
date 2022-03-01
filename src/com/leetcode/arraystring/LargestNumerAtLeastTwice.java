package com.leetcode.arraystring;

public class LargestNumerAtLeastTwice {

    /*
    Intuition:

    We get the largest number - O(N)
    We compute for all numbers if its at least twice. If not then return -1 - O(N), space O(1)
    If nums contains only 1 index/length, there are nothing to compare to, so output index 0.

     */
    public int dominantIndex(int[] nums) {

        int max = nums[0];
        int maxIndex = 0;
        for (int i=0; i<nums.length; i++) {
            if (max < nums[i]) {
                max = nums[i];
                maxIndex = i;
            }
        }

        for (int i=0; i < nums.length; i++) {
            if (2 * nums[i] > max && i != maxIndex) return -1;
        }

        return maxIndex;
    }
}
