package com.leetcode.hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MinimumIndexSumTwoList {

    /*
    The idea is to put the list 1 into a map, with key as its number, and value as its index where we can deduct
    from list2's index
     */
    public String[] findRestaurant(String[] list1, String[] list2) {
        HashMap<String, Integer> list1map = new HashMap<>();
        for (int i = 0; i < list1.length; i++)
            list1map.put(list1[i], i);

        List<String> res = new ArrayList<>();
        int min_sum = Integer.MAX_VALUE, sum;

        for (int j = 0; j < list2.length; j++) {
            if (list1map.containsKey(list2[j])) {
                sum = j + list1map.get(list2[j]);
                // if sum < min_sum, then delete the list and insert list2[j]
                if (sum < min_sum) {
                    min_sum = sum;
                    res.clear();
                    res.add(list2[j]);
                }
                // but when the case of sum == min_sum, then update the list to include the list2[j] entry too
                else if (sum == min_sum) {
                    res.add(list2[j]);
                }
            }
        }
        return res.toArray(new String[0]);
    }
}
