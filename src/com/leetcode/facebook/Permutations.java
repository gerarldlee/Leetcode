package com.leetcode.facebook;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0)
            return new ArrayList<>();

        List<List<Integer>> result = new ArrayList<>();
        permute(nums, result, new ArrayList<>(), new boolean[nums.length]);
        return result;
    }

    /*
    The idea is to iterate over the nums array and using a boolean memo array with its size to determine which value is valid next.

        We will create a recursive function that has params: nums array, result, arraylist, memo
        - If the num.array == arraylist.size, then its full, we can add it to the result.
        - If not, we continue iterating

        Time: O(N!) - permutation
        Space: O(N) - memo + O(N) - temp + O(N!) - result
    */
    private void permute(int[] nums, List<List<Integer>> result, List<Integer> temp, boolean[] memo) {
        if (temp.size() == nums.length) {       // base case
            result.add(new ArrayList<>(temp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (memo[i]) continue;

            temp.add(nums[i]);
            memo[i] = true;

            permute(nums, result, temp, memo);

            memo[i] = false;
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] a) {
        Permutations p = new Permutations();
        System.out.println(p.permute(new int[] {1,2,3}));
    }
}
