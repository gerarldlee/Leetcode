package com.leetcode.binarysearch;

public class SquareRoot {

    /*
    Intuition:
    It will always be less than x/2. And greater than 0. If x == 1, then square root is 1.
    So we just have to take the params from 2 onwards (its an int)
    Search from 2 to x/2.

     */
    public int mySqrt(int x) {
        if (x < 2) return x;

        long num;
        int pivot, left = 2, right = x / 2;
        while (left <= right) {
            pivot = left + ((right - left) / 2);
            num = (long)pivot * pivot;
            if (num > x) right = pivot - 1;
            else if (num < x) left = pivot + 1;
            else return pivot;
        }

        return right;
    }
}
