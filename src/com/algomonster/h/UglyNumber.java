package com.algomonster.h;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

public class UglyNumber {

    // ugly numbers are numbers whos prime factors are either 2, 3, or 5, or any combination of them
    // that means building on our way up to n, we use the sieve of erathosthenes to compute for primes and non primes
    // of 2, 3, 5, and save it into a min-heap and a set to flag if its already been seen
    // we then get the smallest value in the min-heap as nth ugly number

    // time: o(3n) * O(2 * log N) = O(N log N)
    // space:
    public static int nthUglyNumber(int n) {
        // WRITE YOUR BRILLIANT CODE HERE

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        HashSet<Integer> seen = new HashSet<>();

        heap.offer(1);  // 1 is ugly number
        n--;    // since there has been 1 ugly number, decrement n
        while (heap.size() > 0 && n > 0) {
            int ugly = heap.poll();     // remove the ugly numbers before n

            // compute for the ugly number of 2,3,5
            for (int factor : new int[]{2,3,5}) {
                int potentiallyUgly = factor * ugly;
                if (!seen.contains(potentiallyUgly)) {
                    heap.offer(potentiallyUgly);
                    seen.add(potentiallyUgly);
                }
            }
            n--;
        }

        return heap.poll();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        scanner.close();
        int res = nthUglyNumber(n);
        System.out.println(res);
    }
}
