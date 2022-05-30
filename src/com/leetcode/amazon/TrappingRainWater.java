package com.leetcode.amazon;

public class TrappingRainWater {
    public int trap(int[] height) {
        int left = 0;
        int right = height.length-1;
        int result = 0;
        int maxLeft = 0, maxRight = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= maxLeft) {
                    maxLeft = height[left];
                }
                else {
                    result = result + (maxLeft - height[left]);
                }
                ++left;
            }
            // when left >= right
            else {
                // update the maxRight when height is > maxRight
                if (height[right] >= maxRight) {
                    maxRight = height[right];
                }
                else {
                    result = result + (maxRight - height[right]);
                }
                --right;
            }
        }

        return result;
    }
}
