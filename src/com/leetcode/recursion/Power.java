package com.leetcode.recursion;

public class Power {


    // Intuition
    // brute solution
    public double myPow(double x, int n) {
        if (x == 0) return 0;
        double ret = 0;
        if (n >= 0) {
            for(int i=0; i<n; i++) ret *= x;
        }
        else {
            for(int i=0; i<Math.abs(n); i++) ret *= x;
            ret = 1 / ret;
        }
        return ret;
    }

    private double myPowRecursive(double x, int n) {
        if (n < 0) {
            x = 1 / x;
            n = -n;
        }

        if (n == 1) return x;

        return x * myPowRecursive(x, n-1);
    }

    // Intuition: we can get X^n by getting (X^(n/2)) * (X^(n/2))
    // If  n == odd, we can get it by (X^(n/2)) * (X^(n/2)) * X <-- multiplying to itself
    private double fastPower(double x, int n) {
        if (n == 0) return 1;

        double half = fastPower(x, n/2);

        if (n % 2 ==0) return half * half;
        else return half * half * x;
    }

    private double fastPowerIterative(double x, int n) {
        if (n == 0) return 1;

        double ans = 1;
        for (int i=n; i > 0; i /= 2) {
            if (i % 2 == 1) ans *= x;
            x *= x;
        }

        return ans;
    }
}
