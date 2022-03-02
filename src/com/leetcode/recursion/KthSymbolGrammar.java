package com.leetcode.recursion;

// 1 = 0 = 1
//2 = 01 = 2
//3 = 0110 = 4
//4 = 01101001 = 8
//5 = 0110100110010110 = 16
// ...
// 30 =
//
//n = 3, k = 3, ans = 1
public class KthSymbolGrammar {

    // Intuition
    //
    // Brute
    // We use a recursive or iterative function to iterate the nth row
    // We use a String[] of n, where it populates every iteration row, with the rules, 0 = 01, and 1 with 10
    // We will iterate through each char of the String in order for us to do the next iteration
    // Once we arrived at the nth row, then we iterate it to get to the kth index of that row
    // time: o(n * 2^n), space: o(n)

    // Observation is that nth kth size = 2 ^ n-1, with the kth index just a flip of the n-1 kth / 2 index
    // We could essentially copy the previous String, and loop through it and flip the binary numbers and add
    // it to that to form the next String
    public int kthGrammar(int n, int k) {
        if (n == 1 && k == 1) return 0;

        StringBuilder sb = new StringBuilder("0");
        for (int i=2; i <= n; i++) {
            sb.append(reverseString(sb.toString()));
        }
        return sb.charAt(k-1) == '0' ? 0 : 1;
    }

    private String reverseString(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i < str.length(); i++) {
            char c = str.charAt(i);
            sb.append(c == '0' ? "1" : "0");
        }
        return sb.toString();
    }

    /*
    The idea is that
    https://leetcode.com/explore/learn/card/recursion-i/253/conclusion/1675/discuss/113705/JAVA-one-line

    Each K has an odd or even number "1" in its binary representation:

    k = binary  | odd/even
    ----------------------
    1 = 01      | odd
    2 = 10      | odd
    3 = 11      | even (2 ones)
    4 = 100     | odd

    The kth gramar is defined as K = 2 ^ N-1. K = N-1 + flip of N-1, which is just the binary representation
    of K-1.

    The & 1 or % 2 is just to denote if the K-1 is odd or even, or 1 or 0

     */
    public int kthGrammarBitSolution(int N, int K) {
        return Integer.bitCount(K-1) & 1;
    }
}


