package com.leetcode.amazon;

import java.util.*;

public class Skyline {

    /*
    Using heap and sorted map
     */
    public List<List<Integer>> skyline(int[][] buildings) {
        TreeMap<Integer, List<int[]>> map = new TreeMap<>();
        for (int[] b : buildings) {
            map.putIfAbsent(b[0], new ArrayList<>());
            map.putIfAbsent(b[1], new ArrayList<>());
            map.get(b[0]).add(b);
            map.get(b[1]).add(b);
        }

        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        List<List<Integer>> res = new ArrayList<>();

        for (int a : map.keySet()) {
            List<int[]> bs = map.get(a);
            for (int[] b : bs) {
                if (b[0] == a) {
                    maxHeap.offer(b);
                } else {
                    maxHeap.remove(b);
                }
            }

            if (maxHeap.size() == 0) {
                List<Integer> tmp = new ArrayList<>();
                tmp.add(a);
                tmp.add(0);
                res.add(tmp);
            } else {
                int maxHeight = maxHeap.peek()[2];

                if (res.size() == 0 || res.get(res.size() - 1).get(1) != maxHeight) {
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(a);
                    tmp.add(maxHeight);
                    res.add(tmp);
                }
            }
        }

        return res;
    }
}
