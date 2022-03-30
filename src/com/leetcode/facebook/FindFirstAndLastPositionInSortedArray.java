package com.leetcode.facebook;

import java.util.Arrays;

public class FindFirstAndLastPositionInSortedArray {
    public int[] searchRange(int[] nums, int target) {

        if (nums == null || nums.length == 0) return new int[] {-1,-1};

        int[] result = new int[2];
        Arrays.fill(result, -1);

        int l=0;
        int r = nums.length-1;

        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] < target) {
                l = mid + 1;
            }
            else {
                if (nums[mid] == target) {
                    result[0] = mid;
                }
                r = mid - 1;
            }
        }

        if (result[0] == -1) return result;

        r = nums.length-1;

        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] <= target) {
                if (nums[mid] == target) {
                    result[1] = mid;
                }
                l = mid + 1;
            }
            else {
                r = mid - 1;
            }
        }

        return result;
    }

}
