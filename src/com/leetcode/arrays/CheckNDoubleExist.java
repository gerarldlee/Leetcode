package com.leetcode.arrays;

import java.util.HashSet;
import java.util.Set;

public class CheckNDoubleExist {

    // time: o(n) space: o(n)
    public boolean checkIfExist(int[] arr) {

        if (arr.length < 2) return false;

        Set<Integer> set = new HashSet<>();

        for (int i=0; i<arr.length; i++) {
            if (set.contains(2 * arr[i]) || ((arr[i] % 2 == 0) && set.contains(arr[i]/2))) {
                return true;
            }
            set.add(arr[i]);
        }

        return false;
    }
}
