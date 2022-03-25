package com.leetcode.facebook;

import java.util.Arrays;
import java.util.LinkedList;

public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        LinkedList<int[]> list = new LinkedList<>();

        for (int i=0; i<intervals.length; i++) {
            int key = intervals[i][0];
            int value = intervals[i][1];


            if (list.isEmpty() || list.peekLast()[1] < key) {
                list.offerLast(new int[] {key, value});
                continue;
            }
            else {
                int temp = list.peekLast()[1];

                list.peekLast()[1] = Math.max(value, temp);
            }
        }

        return list.toArray(new int[0][]);
    }
}
