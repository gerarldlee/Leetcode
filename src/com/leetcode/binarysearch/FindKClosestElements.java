package com.leetcode.binarysearch;

import java.util.ArrayList;
import java.util.List;

public class FindKClosestElements {

    public List<Integer> findClosestElements(int[] nums, int k, int x) {
        List<Integer> result = new ArrayList<>(k);

        int l = 0;
        int r = nums.length - k;

        while (l < r) {
            int m = (l + r) / 2;

            int left = Math.abs(nums[m] - x);
            int right = Math.abs(nums[m+k] - x);
//            if (left <= right && nums[m] < nums[m+k]) {
            if (x < nums[m]) {
                r = m;
            }
            else if (x > nums[m+k]) {
                l = m + 1;
            }
            else if (left <= right) {
                r = m;
            }
            else {
                l = m + 1;
            }
        }

        // copy the values from leftmost index
        for (int i=l; i < l+k; i++) {
            result.add(nums[i]);
        }

        return result;
    }

    public static void main(String[] a) {
        FindKClosestElements k = new FindKClosestElements();
        System.out.println(k.findClosestElements(new int[]{1,2,3,4,4,4,4,5,5}, 3, 3));
        System.out.println(k.findClosestElements(new int[]{1,2,3,4,5}, 4, 3));
        System.out.println(k.findClosestElements(new int[]{1,1,2,2,2,2,2,3,3}, 3, 3));
    }

}
