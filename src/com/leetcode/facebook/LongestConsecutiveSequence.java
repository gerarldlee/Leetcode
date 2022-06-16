package com.leetcode.facebook;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {

        // put this into a set, so that we can easily search later
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            set.add(n);
        }

        // iterate nums elements
        // each num, if its the start of subsequence, then it will have an increasing value in the set
        int bestCount = 0;
        for (int n : nums) {
            if (!set.contains(n-1)) {   // optimization: see if n = the first element of subsequence
                int num = n;
                int count = 1;
                // increase the num and try to determine if its present in the set
                while (set.contains(num+1)) {
                    count++;
                    num++;
                }
                bestCount = Math.max(bestCount, count);
            }
        }
        return bestCount;
    }
}
