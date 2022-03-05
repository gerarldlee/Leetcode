package com.leetcode.hashtable;

import java.util.HashSet;
import java.util.Set;

public class SingleNumber {
    /**
     * Space: o(n), Time: o(n)
     */
    public int singleNumber(int[] nums) {
        Set<Integer> array = new HashSet<>();

        for (int i=0; i<nums.length; i++) {
            if (array.contains(nums[i])) {
                array.remove(nums[i]);
            }
            else {
                array.add(nums[i]);
            }
        }

        return array.toArray(new Integer[0])[0];
    }

    /**
     * We take XOR of a number
     * Example 1 ^ 1 = 0
     * 1 ^ 2 ^ 1 = (1 ^ 1) ^ 2 = 0 ^ 2 = 2
     */
    public int singleNumberBest(int[] nums) {
        int a = 0;
        for (int i : nums) {
            a ^= i;
        }
        return a;
    }
}
