package com.leetcode.recursion2;

import java.util.Arrays;

public class SelectionSort {

    /*
    gets the smallest value in each iteration and swaps them to the current starting point of each iteration
     */
    // time: o(n^2), space:o(1)
    public int[] sort(int[] nums) {
        for (int start=0; start<nums.length; start++) {
            int min_index = start;
            for (int i=start+1; i<nums.length; i++) {
                if (nums[i] < nums[min_index])
                    min_index = i;
            }
            // swap the minimum from the starting index
            if (min_index != start) {
                int tmp = nums[min_index];
                nums[min_index] = nums[start];
                nums[start] = tmp;
            }
        }
        return nums;
    }

    public static void main(String[] ar) {
        SelectionSort q = new SelectionSort();
        int[] array = (new int[] {56,3,7,9,3,4});

        System.out.println(Arrays.toString(q.sort(array)));
    }
}
