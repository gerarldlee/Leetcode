package com.algomonster.tp;

import java.util.Scanner;

public class ValidPalindrome {
    private static boolean isValid(char c) {
        int alpha = c - 'a';
        int num = c - '0';
        if ((alpha >= 0 && alpha < 26) ||
                (num >= 0 && num < 10)) {
            // is valid
//             System.out.println(c - 'a');
//             System.out.println(c - '0');
            return true;
        }
        return false;
    }

    public static boolean isPalindrome(String s) {
        // WRITE YOUR BRILLIANT CODE HERE

        if (s == null) return false;

        // convert the string into smaller letters
        s = s.toLowerCase();

        int left = 0;
        int right = s.length()-1;

        while (left < right) {
            // alphanumeric chars are between char c = 'a' >= 26, and char n = '0' >= 10
            char l = s.charAt(left);
//             System.out.println("left: " + l);
            if (!isValid(l)) {
                left++;
                continue;
            }

            char r = s.charAt(right);
//             System.out.println("right: " + r);
            if (!isValid(r)) {
                right--;
                continue;
            }

            if (l == r) {
                left ++;
                right --;
                continue;
            }
            else return false;
        }

        return Math.abs(left - right) <= 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        scanner.close();
        boolean res = isPalindrome(s);
        System.out.println(res);
    }
}
