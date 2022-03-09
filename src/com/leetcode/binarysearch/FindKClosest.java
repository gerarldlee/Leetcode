package com.leetcode.binarysearch;

import java.util.ArrayList;
import java.util.List;

public class FindKClosest {

    /*
    The idea is that we use binary search to find the left most boundary. And then from here, copy the values up to k.
    Since it is already sorted, we will assign the right index to array.length - k

    The intuition for finding the left most boundary is to determine first the midpoint index, and compare it:
        - if (x - arr[mid]) <= (arr[mid + k] - x), that means the left is closer to x than right, so we need to make it further left
        - until left == right

    Time: o(log(n - k) + k), space: o(1)
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // Initialize binary search bounds
        int left_index = 0;
        int right_index = arr.length - k;

        // Binary search against the criteria described
        while (left_index < right_index) {
            int mid_index = (left_index + right_index) / 2;
            if (x - arr[mid_index] <= arr[mid_index + k] - x) {
                right_index = mid_index;
            } else {
                left_index = mid_index + 1;
            }
        }

        // Create output in correct format
        List<Integer> result = new ArrayList<>(k);
        for (int i = left_index; i < left_index + k; i++) {
            result.add(arr[i]);
        }

        return result;
    }
}
