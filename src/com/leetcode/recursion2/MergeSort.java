package com.leetcode.recursion2;

import java.util.Arrays;

public class MergeSort {

    /*
    We divide the problem into problems recursively by half.
    The base case is when the num is just 1 or null.
    We merge the result from then on.

    Time: O(n log(n))
    Space: o(2 * (n / 2)) = o(n)
     */
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length == 0) return null;

        return mergesort(nums);
    }

    private int[] mergesort(int[] nums) {
        if (nums.length <= 1) return nums;

        int pivot = nums.length / 2;
        int[] left = mergesort(Arrays.copyOfRange(nums, 0, pivot));
        int[] right = mergesort(Arrays.copyOfRange(nums, pivot, nums.length));
        return merge(left, right);
    }

    /*
    Merging the result is done by creating a pointer for both arrays, increasing them at a time.
    Creating a new int[] of left + right array size.
    Inserting the left or right value whichever comes first.
    And then copying what remains of left or right that was not inserted.

     */
    private int[] merge(int[] left, int[] right) {
        int[] res = new int[left.length + right.length];
        int l = 0, r = 0, rdx = 0;
        while (l < left.length && r < right.length) {
            if (left[l] <= right[r]) {
                res[rdx++] = left[l++];
            }
            if (right[r] < left[l]) {
                res[rdx++] = right[r++];
            }
        }
        while (l < left.length) {
            res[rdx++] = left[l++];
        }
        while (r < right.length) {
            res[rdx++] = right[r++];
        }
        return res;
    }

    public static void main(String[] a) {
        int[] nums = new int[] {1,2,3,4,5,6};
        int pivot = nums.length / 2;
        System.out.println("1: " + Arrays.toString(Arrays.copyOfRange(nums, 0, pivot)) + " 2: " + Arrays.toString(Arrays.copyOfRange(nums, pivot, nums.length)));
    }
}
