package com.leetcode.queuestack;

import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

public class MaximumWidthRamp {
    public int maxWidthRamp(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int max = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        // strictly decreasing stack so that we know nums[i] <= nums[j]
        for (int i=0; i<nums.length; i++) {
            if (nums[i] < nums[stack.peek()])
                stack.push(i);
        }

        for (int j=nums.length-1; j>=0; j--) {
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[j]) {
                int i = stack.pop();
                max = Math.max(j - i, max);
            }
        }
        return Math.max(max, 0);
    }

    public int maxWidthRampUsingSortedMap(int[] nums) {
        TreeMap<Integer, Integer> indexes = new TreeMap<>(); // 6 => 0, 0 => 1
        int res = 0; // 2
        int min = Integer.MAX_VALUE; // 6
        for (int i = 0 ; i < nums.length ; i++) { // 1
            Map.Entry<Integer, Integer> floor = indexes.floorEntry(nums[i]);
            if (floor != null) {
                res = Math.max(res, i - floor.getValue());
            }
            if (nums[i] < min) {
                indexes.put(nums[i], i);
                min = nums[i];
            }
        }
        return res;
    }

    public static void main(String[] argv) {
        MaximumWidthRamp m = new MaximumWidthRamp();

        System.out.println(m.maxWidthRamp(new int[] {6,0,8,2,1,5}));
    }
}
