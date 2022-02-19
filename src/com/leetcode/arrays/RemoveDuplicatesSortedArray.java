package com.leetcode.arrays;

public class RemoveDuplicatesSortedArray {

    // space: o(1) , time: o(n)
    public int removeDuplicates(int[] nums) {

        if (nums.length < 2) return 1;

        // two pointers
        int j=0;
        for (int i=1; i<nums.length; i++) {
            if (nums[j] < nums[i]) {
                nums[++j] = nums[i];
            }
        }
        return j+1;
    }

    public static void main(String[] a) {
        RemoveDuplicatesSortedArray m = new RemoveDuplicatesSortedArray();
//        m.removeDuplicates(new int[]{1,2,3});
//        m.removeDuplicates(new int[]{1,1,2});
        m.removeDuplicates(new int[]{1,1,1,1,2,3,3,3,3,4});
    }
}
