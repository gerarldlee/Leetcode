package com.algomonster.m;

import java.util.Arrays;
import java.util.Scanner;

public class Prime {
    public static int nthPrime(int n) {
        // WRITE YOUR BRILLIANT CODE HERE

        boolean[] primes = new boolean[100001];    // index is used
        Arrays.fill(primes, true);            // everything is a prime unless its a factor of the prime
        int count = 0;
        primes[0] = primes[1] = false;    // both 0 and 1 are non-prime

        int i=2;
        for (; i<=100000; i++) {
            if (primes[i]) {
                n--;
                if (n==0) return i;
                // all of the factors of this prime, will be tagged as non-prime
                for (int j=i+i;j<=100000;j+=i) {
                    primes[j]=false;
                }
            }
        }

        return i;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        scanner.close();
        int res = nthPrime(n);
        System.out.println(res);
    }
}
