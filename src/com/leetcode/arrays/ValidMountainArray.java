package com.leetcode.arrays;

public class ValidMountainArray {

    // test cases:
    /*  [2,1] - false
        [3,5,5] - false
        [0,3,2,1] - true
        [2,0,2] - false
        [0,1,2,3,4,5,6,7,8,9] - false
     */

    // space: o(1) time: o(n)
    public boolean validMountainArray(int[] arr) {

        if (arr.length < 3) return false;

        Boolean directionDown = null;

        for (int i=1; i < arr.length; i++) {
            int d = arr[i] - arr[i-1];
            if (d == 0) return false;

            if (directionDown != null && d > 0) return false;

            // assume direction always positive, until the first descent, which should be when i > 0
            if (d < 0) {
                // edge case where at the very first attempt, a mountn should ascent
                if (i == 1) return false;
                directionDown = true;
            }
            else {
                // edge case where at the last step, a mountain should descent
                if (i== arr.length-1) return false;
            }
        }
        return true;
    }
}
