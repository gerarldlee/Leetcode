package com.leetcode.facebook;

public class ValidPalindrome2 {

    /*
    Search by two-pointers approach where left pointer is pointed at the beginning, right pointer is pointed at the end
    The two pointers move at the same time to reach the middle. and in each move, they should be equal in character
    However, a left OR a right pointer can make a mistake of 1.
        - given a character mistake, try to move the left pointer by one, if its the same character as the right pointer points.
        - or the right character
        - then continue moving both pointers at the same time.
        if it reach another error again, except for the middle when the string length is odd, then return false.
     */
    public boolean validPalindrome(String s) {
        if (s == null || s.length() == 0) return false;
        char[] chars = s.toCharArray();
        boolean mistake = false;
        int left = 0, right = chars.length-1;
        while (left < right && !mistake) {
            if (chars[left] != chars[right])
                return validPalindromeMistake(chars, left+1, right) || validPalindromeMistake(chars, left, right-1);
            left++;
            right--;
        }
        return true;
    }

    private boolean validPalindromeMistake(char[] chars, int left, int right) {
        while (left < right) {
            if (chars[left] != chars[right]) return false;
            left++;
            right--;
        }
        return true;
    }

    public boolean validPalindrome2(String s) {
        if (s == null || s.length() == 0) return false;

        char[] c = s.toCharArray();

        int mistake = 0;
        int left = 0;
        int right = c.length-1;

        int last_left_mistake = 0;
        int last_right_mistake = 0;

        while (left <= right && mistake <= 2) {

            if (c[left] != c[right]) {
                if (mistake == 0) {
                    // but try something if the mistake was made from the left
                    last_left_mistake = left;
                    last_right_mistake = right;
                    left++;
                }

                // we can then try from the right, recovering from the last left state
                else if (mistake >= 1) {
                    left = last_left_mistake;
                    right = last_right_mistake;
                    right--;
                }

                mistake++;
            }
            else {
                left++;
                right--;
            }
        }
        return mistake < 3;
    }

    public static void main(String[]a) {
        String s = "aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga";

        ValidPalindrome2 v = new ValidPalindrome2();
        System.out.println(v.validPalindrome(s));
    }
}
