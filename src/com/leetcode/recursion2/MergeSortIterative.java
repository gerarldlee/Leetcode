package com.leetcode.recursion2;

import java.util.Arrays;

public class MergeSortIterative {

    public int[] sortArray(int[] nums) {
        int n = nums.length;

        // current size of subarrays to be merged. it varies from 1 to n/2
        int curr_size;
        // for picking the starting index of the left subarray to be merged
        int left_start;

        // merge subarrays in the bottom up approach.
        // - Merge subarrays of size 1 to create sorted subarrays of size 2
        // - then merge subarrays of size 2 to create sorted subarrays of size 4.
        // - etc...
        for (curr_size = 1; curr_size <= n-1; curr_size *= 2) {

            // pick starting point of different subarrays of current size
            for (left_start = 0; left_start < n-1; left_start += 2 * curr_size) {

                // find ending point of left subarray.
                // mid + 1 is the starting point of the right
                int mid = Math.min(left_start + curr_size - 1, n-1);
                int right_end = Math.min(left_start + 2 * curr_size - 1, n-1);

                // merge the subarrays num[left_start ... mid], num[mid+1 ... right_end]
                merge(nums, left_start, mid, right_end);
            }
        }

        return nums;
    }

    private void merge(int[] nums, int left, int mid, int right) {

        int left_size = mid - left + 1;
        int right_size = right - mid;

        // create a temporary array for left and right
        int L[] = new int[left_size];
        int R[] = new int[right_size];
        for (int i=0; i<left_size; i++) L[i] = nums[left + i];
        for (int j=0; j<right_size; j++) R[j] = nums[mid + 1 + j];

        int l=0, r=0, k=left;

        // sort the left and the right
        while (l < left_size && r < right_size) {
            if (L[l] <= R[r]) {
                nums[k++] = L[l++];
            }
            else {
                nums[k++] = R[r++];
            }
        }

        // copy the remaining arrays
        while (l < left_size) {
            nums[k++] = L[l++];
        }
        while (r < right_size) {
            nums[k++] = R[r++];
        }
    }

    public static void main(String[] a) {
        int[] nums = new int[] {6,4,3,2,1,6,5};

        MergeSortIterative m = new MergeSortIterative();
        System.out.println(Arrays.toString(m.sortArray(nums)));
    }
}
