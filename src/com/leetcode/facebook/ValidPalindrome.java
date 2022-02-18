package com.leetcode.facebook;

import com.leetcode.Main;

import java.util.Locale;

public class ValidPalindrome {

    public boolean isPalindrome(String s) {

        String lowercase = s.toLowerCase();
        StringBuilder sb = new StringBuilder();
        // remove white space, and non-alphanumeric
        for (int i=0; i<lowercase.length(); i++) {
            char c = lowercase.charAt(i);
            char z = '{';
            if ((c >= 97 && c <= 122) ||
                    (c >= 48 && c <= 57)) {
                sb.append(c);
            }
        }

        // determine if a string is a palindrome
        int n = sb.length();
        for (int i=0; i<n/2; i++) {
            char c1 = sb.charAt(i);
            char c2 = sb.charAt(n-i-1);
            if (c1 != c2)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        ValidPalindrome m = new ValidPalindrome();
        System.out.println(m.isPalindrome("A man, a plan, a canal: Panama"));   // true
        System.out.println(m.isPalindrome("race a car"));   // true
        System.out.println(m.isPalindrome(" "));   // true
        System.out.println(m.isPalindrome("0P"));   // false
        System.out.println(m.isPalindrome("Marge, let's \"[went].\" I await {news} telegram."));   // trrue

    }
}
