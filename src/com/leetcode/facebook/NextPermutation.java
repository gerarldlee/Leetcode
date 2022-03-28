package com.leetcode.facebook;

public class NextPermutation {

    /*
    Optimized approach
    1. if descending, no next larger permutation is possible, so have to search for the first decrease in value,
        given a array of descending order. [9,8,4,7,5,3,2,1]
    2. swap the greater value closest to it: 4 and 5. [9,8,5,7,4,3,2,1]
    3. reverse the array starting from its next element, 7, to the last element, to make it lexicographically small
        [9,8,1,2,3,4,5,7]
     */

    public void nextPermutation(int[] nums) {

        if (nums.length == 1) return;

        int i = nums.length-1;
        while (i > 0 && nums[i-1] > nums[i]) {
            i--;
        }

        int next_smallest = (i == 0 ? 0 : i-1);     // edge case where i = 0, means array sorted in descending
        int j=nums.length-1;
        while (j >= i) {
            if (nums[j] > nums[next_smallest]) {
                swap(nums, j, next_smallest);
                break;
            }
            j--;
        }

        // reverse the array starting from i
        reverse(nums, i, nums.length-1);
    }
    void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
    void swap(int[] nums, int x, int y) {
        int tmp = nums[x];
        nums[x] = nums[y];
        nums[y] = tmp;
    }

}
