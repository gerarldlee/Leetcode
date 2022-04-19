package com.leetcode.facebook;

import java.util.Arrays;

public class LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] nums) {

        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int max = 1;

        for (int i=1; i<nums.length; i++) {
            for (int j=0; j<i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
//                else {
//                    dp[i] = Math.max(dp[j], dp[i]);
//                }
                 max = Math.max(dp[i], max);
            }
        }
        return max;
    }

    public static void main(String[] a) {
        LongestIncreasingSubsequence l = new LongestIncreasingSubsequence();
        System.out.println(l.lengthOfLIS(new int[] {0,1,0,3,2,3})); // 4

        System.out.println(l.lengthOfLIS(new int[]{10,9,2,5,3,7,101,18})); // 4
        System.out.println(l.lengthOfLIS(new int[] {7,7,7,7}));  // 1
    }
}
