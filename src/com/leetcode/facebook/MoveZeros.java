package com.leetcode.facebook;

import java.util.Arrays;

public class MoveZeros {

    public int[] moveZeroes(int[] nums) {

        int n = nums.length;
        int zero_idx = -1;

        int i=0;    // iterator
        while (i < n) {
            // assign the first 0 to zero index
            if (nums[i] == 0 && zero_idx < 0) {
                zero_idx = i;
            }
            // if the zero index has been assigned a starting point
            if (nums[i] != 0 && zero_idx < i && zero_idx >=0) {
                nums[zero_idx] = nums[i];   // flip it
                zero_idx++;
            }
            i++;
        }

        // fill the remaining with zeros
        while (zero_idx < n && zero_idx != -1) {
            nums[zero_idx] = 0;
            zero_idx++;
        }

        return nums;
    }

    public static void main(String[] a) {
        MoveZeros m = new MoveZeros();
        System.out.println(Arrays.toString(m.moveZeroes(new int[]{0,0,1,2,0,0,3,4,5,0})));   // 1,2,3,4,5,0,0,0,0,0
        System.out.println(Arrays.toString(m.moveZeroes(new int[]{1,2,0,0,0,3,4,5})));   // 1,2,3,4,5,0,0,0
    }
}
