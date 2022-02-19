package com.leetcode.arrays;

import java.util.Arrays;

public class DuplicateZeros {

    // time: o(n * log n)
    // space: o(1)
    public void duplicateZeros(int[] arr) {
        boolean isDup = false;
        for (int i=0; i < arr.length-1; i++) {
            if (arr[i] == 0 && !isDup) {
                for (int j=arr.length-2; j >= i; j--) {
                    arr[j+1] = arr[j];
                }
                arr[i+1] = 0;
                isDup = true;
            }
            else {
                isDup = false;
            }
        }
    }

    // time: o(2 n - m) m = dup 0s  or O(N)
    // space: o(1)
    public String duplicateZeros1(int[] arr) {
        int dups = 0;
        boolean copy_last_zero = false;
        for (int i=0; i < arr.length - dups; i++) {
            if (arr[i] == 0) {
                if (i+1 == arr.length-dups) {
                    copy_last_zero = true;
                }
                dups++;
            }
        }

        // start from back, two pointer approach
        int dup_index = arr.length-1;

        if (copy_last_zero) {
            arr[dup_index] = 0;
            dups--;
            dup_index--;
        }

        int index = dup_index - dups;
        while (dups > 0) {
            // copy from length to dup_index
            arr[dup_index] = arr[index];

            // if 0 then decrease the dup to be copied
            if (arr[index] == 0) {
                dups--;
                dup_index--;
                arr[dup_index] = 0;
            }
            dup_index--;
            index--;
        }
        return Arrays.toString(arr);
    }

    public static void main(String[] a) {
        DuplicateZeros m = new DuplicateZeros();
        System.out.println(m.duplicateZeros1(new int[] {1,0,2,3,0,4,5,0})); // 1,0,0,2,3,0,0,4
        System.out.println(m.duplicateZeros1(new int[] {1,0,2,3,0,0,5,0})); // 1,0,0,2,3,0,0,0
        System.out.println(m.duplicateZeros1(new int[] {1,5,2,0,6,8,0,6,0})); // 1,5,2,0,0,6,8,0,0
        System.out.println(m.duplicateZeros1(new int[] {8,4,5,0,0,0,0,7})); //[8,4,5,0,0,0,0,0]
    }

}
