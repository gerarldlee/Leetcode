package com.algomonster.dp;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LongestIncreasingSubsequence {
    /*
Iterate each number, and determine if the previous number is less than the current number.
If yes, the recurrent relation is: dp[i] = Math.max(dp[prev] + 1, dp[i]) // +1 because the prev number is less than
*/
    public static int longestSubLen(List<Integer> nums) {
        // WRITE YOUR BRILLIANT CODE HERE
        if (nums == null || nums.size()==0) return 0;
        int best = Integer.MIN_VALUE;
        int[] dp = new int[nums.size()];
        Arrays.fill(dp, 1);    // all number represent 1
        for (int i=1; i<nums.size(); i++) {
            for (int j=0; j<i; j++) {
                if (nums.get(j) < nums.get(i)) {
                    dp[i] = Math.max(dp[j]+1, dp[i]);
                    best = Math.max(best, dp[i]);
                }
            }
        }
        return best;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> nums = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
        scanner.close();
        int res = longestSubLen(nums);
        System.out.println(res);
    }
}
