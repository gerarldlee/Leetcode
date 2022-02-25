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

    public int kthGrammarBitSolution(int N, int K) {
        return Integer.bitCount(K-1) & 1;
    }
}


