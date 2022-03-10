package com.leetcode.facebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Permutations2 {

    /*
    The idea is to use a hashmap to count the number of duplicates of a number. In this way, we wont be iterating
    over the duplicate values of num. We will instead use the count to add the duplicate

    Time: O(N!) - worst time
    Space: O(N) - o(n) - map, o(n) - temp, o(n) - stack
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();

        Map<Integer, Integer> count = new HashMap<>();
        for (int i : nums) {
            count.put(i, count.getOrDefault(i, 0) + 1);
        }

        permutate(nums.length, count, result, new ArrayList<>());
        return result;
    }

    private void permutate(int totalNums, Map<Integer, Integer> uniqueNums, List<List<Integer>> result, List<Integer> temp) {
        if (totalNums == temp.size()) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int num : uniqueNums.keySet()) {
            if (uniqueNums.get(num) > 0) {
                temp.add(num);
                uniqueNums.put(num, uniqueNums.get(num) - 1);

                permutate(totalNums, uniqueNums, result, temp);

                uniqueNums.put(num, uniqueNums.get(num) + 1);
                temp.remove(temp.size()-1);
            }
        }

    }
}
