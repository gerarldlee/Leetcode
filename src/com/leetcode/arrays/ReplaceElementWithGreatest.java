package com.leetcode.arrays;

public class ReplaceElementWithGreatest {

    // time: o(n)
    // space: o(1)
    public int[] replaceElements(int[] arr) {

        if (arr.length < 2) return new int[] {-1};

        // start from reverse, get the maximum, and override every preceding element
        // until it finds another maximum element
        int max = -1;
        for (int i=arr.length-1; i >= 0; i--) {
            int tmp = arr[i];
            arr[i] = max;
            max = Math.max(tmp, max);
        }
        return arr;
    }
}
