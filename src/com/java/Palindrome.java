package com.java;

public class Palindrome {

    public static boolean palindrome(String str) {

        int prevLeft = 0;
        int prevRight = str.length()-1;
        int error = 0;

        for (int left=0, right=str.length()-1; left < right; ) {
            char l = str.charAt(left);
            char r = str.charAt(right);

            if (l == r) {
                left++;
                right--;
                continue;
            }

            error++;

            // record the state of left and right
            if (error == 1) {
                // try to move left to 1 first
                prevLeft = left;
                prevRight = right;
                error++;
                left++;
            }
            // try to move the right side now
            else if (error == 2) {
                // restore the previous state of the left and the right
                // error will still be 1, since we have restored the previous state
                left = prevLeft;
                right = prevRight;
                right--;
            }
            else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] a) {

        Palindrome p = new Palindrome();

        System.out.println(p.palindrome("abc") == false);
        System.out.println(p.palindrome("abba") == true);
        System.out.println(p.palindrome("abda") == true);

    }
}
