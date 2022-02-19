package com.leetcode.arrays;

public class MergeSortedArray {

    // space: O(m)
    // time: O(m+n)
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int m_i = 0;
        int n_i = 0;
        int[] m_arr = new int[m];

        // copy num1 values to new array
        for (int i=0; i < m; i++) {
            m_arr[i] = nums1[i];
        }

        for (int i=0; i < nums1.length; i++) {

            // check for borders
            if (n_i >= n) {
                nums1[i] = m_arr[m_i];
                m_i++;
                continue;
            }
            else if (m_i >= m) {
                nums1[i] = nums2[n_i];
                n_i++;
                continue;
            }

            if (m_arr[m_i] < nums2[n_i]) {
                nums1[i] = m_arr[m_i];
                m_i++;
            }
            else {
                nums1[i] = nums2[n_i];
                n_i++;
            }

        }

    }

    public static void main(String[] a) {
        MergeSortedArray m = new MergeSortedArray();
        m.merge(new int[]{1,2,3,0,0,0}, 3, new int[]{2,5,6}, 3);
        m.merge(new int[]{1}, 1, new int[]{}, 0);
        m.merge(new int[]{0}, 0, new int[]{1}, 1);
    }
}
