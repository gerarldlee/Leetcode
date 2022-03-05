package com.leetcode.hashtable;

import java.util.Arrays;
import java.util.HashSet;

public class ContainsDuplicate {

    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);

        int prev = nums[0];
        for (int i=1; i < nums.length; i++) {
            if (nums[i] == prev) return true;
            prev = nums[i];
        }
        return false;
    }

    public boolean containsDuplicate1(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int i : nums) {
            if (set.contains(i)) return true;
            set.add(i);
        }
        return false;
    }
}
