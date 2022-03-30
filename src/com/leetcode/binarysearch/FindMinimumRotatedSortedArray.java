package com.leetcode.binarysearch;

public class FindMinimumRotatedSortedArray {
    public int findMin(int[] array) {
        int n = array.length;

        int l = 0;
        int r = n-1;

        while (l < r) {
            int mid = (l + r) / 2;

            if (array[l] < array[r]) {
                return array[l];
            }
            else if (array[r] < array[mid] || array[l] < array[mid]) {
                l = mid + 1;
            }
            else {
                r = mid;
            }
        }
        return array[r];
    }

    public static void main(String[] a) {
        FindMinimumRotatedSortedArray m = new FindMinimumRotatedSortedArray();
        System.out.println(m.findMin(new int[] {7,0,1,2,3,4,5,6}));
    }
}
