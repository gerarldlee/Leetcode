package com.algomonster.bs;

import java.util.Scanner;

public class SquareRoot {
    public static int squareRoot(int n) {
        // WRITE YOUR BRILLIANT CODE HERE
        if (n <= 1) return n;

        int start = 1;
        int end = n/2;
        int res = 1;

        while (start <= end) {

            int mid = start + (end - start) / 2;

            if (mid * mid <= n) {
                res = mid;
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }

        }

        return res;

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        scanner.close();
        int res = squareRoot(n);
        System.out.println(res);
    }
}
