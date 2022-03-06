package com.leetcode.hashtable;

public class IntersectionTwoArrays2 {

    /*
    - Sort both arrays - o(n log n) + o(m log m)
    - Use both as base for comparison. Iterate through it using a pointer in each array.
    - Since they are both sorted, we can compare duplicates directly. We dont need to use a map.
    - o(n + m) since we will be iterating over both arrays
    - It does not matter if nums2 is longer or even stored on disk and acts as stream, since we are under the
    - assumption that they are sorted.
    - Time: o(n log(n) + m log(m))
    - Space: o(k) k = duplicates that we store in another resulting array

    Another approach would be to use a map for the shorter array
    - Save it. Time: o(n) Space: o(k) - for unique entries in n's
    - We compare the values of another array. Does not matter if they are sorted or not or in stream or not.
    - We create another array for the result. We also need to check for the values we entered for duplicate entries.

    Time: o(n + m) for traversing array 1 and 2.
    Space: o(k + d) k = unique elements of the smaller array, d = the unique intersection result of both array1 and 2

     */
    public int[] intersect(int[] nums1, int[] nums2) {
        return new int[0];
    }
}
