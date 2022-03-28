package com.leetcode.math;

/**
 * x = k(x^2) - 1
 */
public class Chaos {

    public static void main(String[] a) {

        // the bigger the K, the more random it gets
        // from 1 - 1.5  - not wildly random
        // from 1.5 - 2 - wildly random
        double k = 1.5;

        double x = 0.54321;
        for (int i=1; i <= 50; i++) {
            x = k * Math.pow(x, 2) - 1;
            System.out.println("x = " + x);
        }

        for (int i=1; i <= 100; i++) {
            x = k * Math.pow(x, 2) - 1;
//            System.out.println("x = " + x);
        }
    }
}
