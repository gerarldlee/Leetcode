package com.leetcode.arraystring;

import java.util.LinkedList;

public class PlusOne {

    /*
    Intuition:

    Start from the rightmost digit array. + 1 and if the answer > 9, put 0, with 1 as carry over.
    Do it repeatedly until the digit iterator reaches 0.
    When it reaches 0, and the leftmost is 9, append 1 in front.
     */
    public int[] plusOne(int[] digits) {
        LinkedList<Integer> result = new LinkedList<>();

        int carryover = 1;
        for (int i = digits.length-1; i >= 0; i--) {
            int d = digits[i] + carryover;
            carryover = 0;
            if (d > 9) {
                carryover = 1;
                d = 0;
            }
            result.addFirst(d);
            if (i == 0 && carryover > 0) {
                result.addFirst(carryover);
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    public int[] simplified(int[] digits) {
        int n = digits.length;

        for (int i=n-1; i >= 0; i--) {
            if (digits[i] == 9) {
                digits[i] = 0;
            }
            else {
                digits[i]++;
                return digits;
            }
        }
        // only when digits are 999 will there be the need to append 1 in front, and in any case, remaining is 0
        digits = new int[n+1];
        digits[0] = 1;
        return digits;
    }
}
