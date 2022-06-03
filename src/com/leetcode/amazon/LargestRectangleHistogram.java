package com.leetcode.amazon;

public class LargestRectangleHistogram {

    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        return area(heights, 0, heights.length-1);
    }

    /*
    Divide and conquer
    time: o(n log n), space: o(n)
     */
    private int area(int[] nums, int left, int right) {
        if (left > right) return 0;

        int min = left;
        for (int i=left; i<= right; i++)
            if (nums[i] < nums[min])
                min = i;

        int currentArea = nums[min] * (right-left+1);
        int leftArea = area(nums, left, min-1);
        int rightArea = area(nums, min+1, right);
        return Math.max(currentArea, Math.max(leftArea, rightArea));
    }
}
