package com.leetcode.dp;

public class DeleteAndEarn {
    public int deleteAndEarn(int[] nums) {

        // the max num[i] = 10^4
        int[] buckets = new int[10001];
        // sort the nums by its index, and add the frequency of occurrences in a duplicate nums
        for(int n : nums) {
            buckets[n] += n;
        }

        int prev = buckets[1];
        int prev_prev = buckets[0];
        int curr = 0;

        // the recurrence relation is whether to take the previous value, or to take the previous' previous value and the current value
        for (int i=2; i<buckets.length; i++) {
            curr = Math.max(prev_prev + buckets[i], prev);
            prev_prev = prev;
            prev = curr;
        }

        return curr;
    }
}
