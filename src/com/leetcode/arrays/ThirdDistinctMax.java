package com.leetcode.arrays;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ThirdDistinctMax {


    // space = o(1), time = o(n)
    public int thirdMax(int[] nums) {
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        Set<Integer> distinct = new HashSet<>();
        for (int i=0; i< nums.length; i++) {
            if (nums[i] > max1) {
                max3 = max2;
                max2 = max1;
                max1 = nums[i];
            }
            if (nums[i] < max1 && nums[i] > max2) {
                max3 = max2;
                max2 = nums[i];
            }
            if (nums[i] < max2 && nums[i] > max3) {
                max3 = nums[i];
            }
            distinct.add(nums[i]);
        }
        return (distinct.size() < 3 ? max1 : max3);
    }

    // space o(1), time o(n), cleaner
    public int thirdMax1(int[] nums) {
        Set<Integer> maxs = new HashSet<>(4);
        for (int n : nums) {
            maxs.add(n);
            if (maxs.size() > 3) maxs.remove(Collections.min(maxs));
        }
        return maxs.size() < 3 ? Collections.max(maxs) : Collections.min(maxs);
    }
}
