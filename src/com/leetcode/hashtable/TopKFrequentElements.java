package com.leetcode.hashtable;

import java.util.*;
import java.util.function.IntFunction;

public class TopKFrequentElements {

    /*
    Create a hashmap where the key is the value of nums[i], and the value is the count or frequency of duplication.

    Create a map of K size, where using sliding window technique, expunges the lowest value while scanning the hashmap
    and updating the resulting map.
        - how to do this in linear time?
        - create a variable that holds the index of the lowest value.
        - update this map of k size, while updating the variable for the index of the lowest value
        - update this map, without expunging if k size has not been reached.
        - only expunge the index of the lowest value, when the k size has been reached.

    Space: o(k + m) whree k = result array, and m = unique elements of nums
    Time: o(n)
     */

    public int[] topKFrequent(int[] nums, int k) {
        // o(1), theres nothing to compute since all of nums fit in k
        if (nums.length == k) return nums;

        // time: o(n), space: o(n)
        Map<Integer, Integer> uniqueNums = new HashMap<>();
        for (int i : nums) {
            uniqueNums.put(i, uniqueNums.getOrDefault(i, 0) + 1);
        }

        // space: o(k), time: o(n log k) = o(k log k) for adding elements up to k, and o((N-k) log(k)) afterwards
        Queue<Integer> queue = new PriorityQueue<>(k, (n1, n2) -> uniqueNums.get(n1) - uniqueNums.get(n2));

        // iterate over each unique entry in the map, add then to the queue, and remove some when queue.size > k
        for (Map.Entry<Integer, Integer> entry : uniqueNums.entrySet()) {
            queue.add(entry.getKey());  // will automatically get the value of key; defined from the priorityqueue comparator lambda
            if (queue.size() > k) queue.poll();     // removes the top, highest priority (smallest value)
        }

        // o(k) - just convert this into an int array
        return queue.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] a) {
        TopKFrequentElements t = new TopKFrequentElements();

        int[] n1 = new int[] {5,2,5,3,5,3,1,1,3};
        int k1 = 2;
        System.out.println(Arrays.toString(t.topKFrequent(n1, k1)));        // should be [3,5]

        int[] n2 = new int[] {1,1,1,2,2,3};
        int k2 = 2;
        System.out.println(Arrays.toString(t.topKFrequent(n2, k2)));        // should be [1,2]
    }
}
