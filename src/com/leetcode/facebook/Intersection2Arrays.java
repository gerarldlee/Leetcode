package com.leetcode.facebook;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Intersection2Arrays {


    // time: O(max(n log n, m log m)) = more efficient than o(m + n)
    // space: o(min(n, m)) worse case is there are m union in n array
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int[] shortest = nums2;
        int[] longest = nums1;

        if (nums1.length < nums2.length) {
            shortest = nums1;
            longest = nums2;
        }

        Set<Integer> set = new HashSet<>();
        int n1 = 0;
        int n2 = 0;

        while (n1 < shortest.length && n2 < longest.length) {
            if (shortest[n1] == longest[n2]) {
                if (!set.contains(shortest[n1]))
                    set.add(shortest[n1]);
                n1++;
                n2++;
            }
            else if (shortest[n1] < longest[n2]) {
                n1++;
            }
            else {
                n2++;
            }
        }

        return set.stream().mapToInt(i -> i).toArray();
    }
}
