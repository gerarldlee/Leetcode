package com.leetcode.hashtable;

public class HappyNumber {
    /**
     * The idea is that for a given n, doing a mod % 10, will get the last digit. Dividing it by 10 will
     * reduce the n to the remaining digits.
     *
     * Example: n = 29
     * d=9, n=2, totalSum=81
     * d=2, n=0, totalSum=85
     *
     * This is also an implicit LinkedList - we dont have actual linked nodes and pointers, but the data does still
     * form a LinkedList structure. The resulting n, is obtained by getNext(n) in a form of chain.
     */
    public int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    /**
     * The idea is given n, we get the next n number until it reaches either 1 or 4
     * Why 4? They are the numbers that forms a cycle.
     *
     * Time: o(log n), Space: o(1)
     */
    public boolean isHappy(int n) {
        while (n != 1 && n != 4) {
            n = getNext(n);
        }
        return n == 1;
    }

    /**
     * The idea is given n, when we get the next n number, it forms a LinkedList chain cycle, therefore
     * we can use Floyds cycle-finding algorithm - the hare and the tortoise.
     * Regardless of where the tortoise and hare starts, they are guaranteed to eventually meet.
     *
     * If there is no cycle, the hare will eventually get to 1 before the tortoise
     * If there is a cycle, both hare and tortoise will be on the same number.
     *
     * Time: o(2 log n), Space: o(1)
     */
    public boolean isHappyFloyds(int n) {
        int tortoise = n;
        int hare = getNext(n);  // advance it by 1 already.
        while (hare != 1 && tortoise != hare) {
            tortoise = getNext(tortoise);   // advance it by 1.
            hare = getNext(getNext(hare));  // advance it by 2.
        }
        return hare == 1;
    }

}
