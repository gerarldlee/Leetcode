package com.leetcode.recursion2;

import java.util.Arrays;

public class QuickSort {

    public void quicksort(int[] array, int left, int right) {
        // base case
        if (left < 0 || right < 0 || left > right) return;

        // the pivot is where the partition iteration finished its job
        int pivot = partition(array, left, right);
        // sort left and right of the pivot
        quicksort(array, left, pivot-1);
        quicksort(array, pivot+1, right);
    }

    private int partition(int[] array, int left, int right) {
        int pivot = array[right];
        int i = left;
        for (int j=left; j<right; j++) {
            if (array[j] < pivot) {
                swap(array, i, j);
                i++;
            }
        }
        swap(array, i, right);
        return i;
    }

    private void swap(int[] array, int left, int right) {
        int tmp = array[right];
        array[right] = array[left];
        array[left] = tmp;
    }

    public static void main(String[] ar) {
        QuickSort q = new QuickSort();
        int[] array = (new int[] {56,3,7,9,3,4});
        q.quicksort(array, 0, array.length-1);

        System.out.println(Arrays.toString(array));
    }
}
