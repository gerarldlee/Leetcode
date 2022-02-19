package com.leetcode.arrays;

import java.util.Arrays;

public class HeightChecker {

    // space: o(n)
    // time: o(n log n)
    public int heightChecker(int[] heights) {

        int[] expected = Arrays.copyOf(heights, heights.length);
        Arrays.sort(expected);

        int count = 0;
        for (int i=0; i<heights.length; i++) {
            if (heights[i] != expected[i]) count++;
        }
        return count;
    }

    public static void main(String[] argv) {
        HeightChecker h = new HeightChecker();
        h.heightChecker(new int[] {1,1,4,2,1,3});
    }
}
