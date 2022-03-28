package com.leetcode.facebook;

public class Divide2Integers {

    public int divide(int dividend, int divisor) {

        long dd = Math.abs((long)dividend);
        long dv = Math.abs((long)divisor);
        long result = 0;

        // if (dv == 1) {
        //     if ((dividend < 0 && divisor >= 0) ||
        //         (dividend >= 0 && divisor < 0)) {
        //         return -dividend;
        //     }
        //     return dividend;
        // }

        while (dd >= dv) {
            long multiple_dv = dv;
            long multiples = 1;

            while (multiple_dv <= dd) {
                result += multiples;

                dd -= multiple_dv;
                multiples += multiples;
                multiple_dv += multiple_dv;
            }

        }

        if ((dividend < 0 && divisor >= 0) ||
                (dividend >= 0 && divisor < 0)) {
            result = -result;
        }

        if (result < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        else if (result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        else {
            return (int) result;
        }

    }

    public static void main(String[] a) {
        Divide2Integers d = new Divide2Integers();
        System.out.println(d.divide(-2147483648, -1));
    }
}
