package com.leetcode.arrays;

public class RemoveElement {

    // space: o(1), time o(n) worst o(log n) average
    public int removeElement(int[] nums, int val) {

        int k = 0;
        int end = nums.length-1;

        // iterate thru nums
        for (int i=0; i<=end; i++) {
            k++;
            // searching for val
            if (nums[i] == val) {
                k--;
                // move/swap val to the end,
                while (end > k) {
                    if (nums[end] != val) {
                        int tmp = nums[i];
                        nums[i] = nums[end];
                        nums[end] = tmp;
                        end--;
                        k++;
                        break;
                    }
                    end--;
                }
            }
        }

        return k;
    }

    public static void main(String[] a) {
        RemoveElement e = new RemoveElement();
//        e.removeElement(new int[] {3,2,2,3}, 3);
        e.removeElement(new int[] {0,1,2,2,3,0,4,2}, 2);
//        e.removeElement(new int[]{1}, 1);
    }
}
