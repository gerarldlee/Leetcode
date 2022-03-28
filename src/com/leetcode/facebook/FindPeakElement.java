package com.leetcode.facebook;

public class FindPeakElement {

    /*
    we are allowed to return any peak, and peak is defined as an element that's strictly greater than its neighbors
     */
    public int findPeakLinear(int[] nums) {

        for (int i=0; i<nums.length-1; i++){
            if (nums[i] > nums[i+1]) {
                return i;
            }
        }

        return nums.length-1;
    }

    /*
    using the advanced form of binary search because we would like to compare its value to its right neighbors

    guaranteed to have o(log n) time, with o(1) space
     */

    public int findPeakBinary(int[] nums) {
        int l=0;
        int r=nums.length-1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid+1]) {
                r = mid;
            }
            else {
                l = mid+1;
            }
        }
        return l;
    }
}
