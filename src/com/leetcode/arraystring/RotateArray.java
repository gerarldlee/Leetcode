package com.leetcode.arraystring;

public class RotateArray {
    /*
        Reverse the whole num array
        Then reverse the first part, 0 up to k
        Then reverse the remaining part, k up to numarray.legnth
     */
    public void rotate(int[] nums, int k) {
        if (k == 0) return;

        k = k % nums.length;    // when k > nums.length, we just want to know the diff.
        reverse(nums, 0, nums.length-1);
        reverse(nums, 0, k-1);
        reverse(nums, k, nums.length-1);
    }

    // time: o(n x k), space: o(1)
    private void reverse(int[] nums, int begin, int end) {
        while (begin < end) {
            int t = nums[end];
            nums[end] = nums[begin];
            nums[begin] = t;
            begin++;
            end--;
        }
    }

    /*
    The idea is that for each cycle, we start with initial position, 0, and move it to kth index, and move that to another k+kth index...
    until the starting position is reached.
    In the next cycle, we start with the next number from our previous starting position.
    We count the moves of all, until all of the nums has been moved, i.e. moves = nums.length
     */
    public void rotateCyclical(int[] nums, int k) {

        k = k % nums.length;
        int moves = 0;
        int cycle_starting_index = 0;

        while (moves < nums.length) {
            int current_index = cycle_starting_index;
            int previous_value = nums[current_index];
            do {
                int next_index = (current_index + k) % nums.length;
                int temp = nums[next_index];
                nums[next_index] = previous_value;
                previous_value = temp;
                current_index = next_index;
                moves++;
            }
            while (current_index == cycle_starting_index);
            cycle_starting_index++;
        }
    }
}
