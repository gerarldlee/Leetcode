package com.leetcode.recursion2;

import java.util.Arrays;

public class InsertionSort {

    /*
    any number that's greater than the current number is moved to replace it; inserting the current number to its
    proper sorted order.  this will make the left of the current iteration sorted.
     */
    // time: o(n^2), space: o(1)
    public int[] sort(int[] nums) {
        if (nums == null || nums.length <= 1) return nums;
        for (int i=1; i<nums.length; i++) {
            int tmp = nums[i];
            int j=i-1;
            while (j >=0 && nums[j] > tmp) {
                nums[j+1] = nums[j];
                j--;
            }
            nums[j+1] = tmp;
        }
        return nums;
    }

    public static void main(String[] ar) {
        InsertionSort q = new InsertionSort();
        int[] array = (new int[] {56,3,7,9,3,4});

        System.out.println(Arrays.toString(q.sort(array)));
    }
}
