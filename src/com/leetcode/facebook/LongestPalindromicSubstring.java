package com.leetcode.facebook;

public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {

        if (s == null || s.length() <= 1) return s;

        int max = 0;
        int start = 0;

        for (int i=0; i<s.length()-1; i++) {
            int odd = palindrome(s, i, i);
            int even = palindrome(s, i, i+1);
            if (odd > 0 && max < odd) {
                max = odd;
                start = i - (max / 2);
            }
            if (even > 0 && max < even) {
                max = even;
                start = i - (max / 2) + 1;
            }
            // System.out.println("max: " + max + " odd: " + odd + " even: " + even + " start: " + start);
        }

        return s.substring(start, start+max);

    }

    int palindrome(String s, int left, int right) {

        while (left >= 0 && left <= right && right < s.length() &&
                s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1;
    }
}
