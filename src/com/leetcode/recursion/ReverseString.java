package com.leetcode.recursion;

public class ReverseString {

    // Intuition
    // Iterative approach
    // Reverse a string by pointing 2 pointers, begin and end.
    // Swap them. Then increment begin, decrement end. Both approaches the middle.
    // Disregard the middle if begin == end.
    // time: O(n/2) space: o(1)

    // Recursive approach
    // We could use a similar approach using recursion
    // Each level, swapping the characters, incrementing begin, decrementing end
    // Stops when theres nothing more to reverse. i.e. begin >= end
    // time: o(n/2) space: o(n/2)
    public void reverseString(char[] s) {
        if (s.length <= 1) return;
        reverse(s, 0, s.length-1);
    }

    private void reverse(char[] s, int i, int i1) {
        if (i < i1) {
            char c = s[i];
            s[i] = s[i1];
            s[i1] = c;
            reverse(s, i+1, i1-1);
        }
    }
}
