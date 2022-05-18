package com.algomonster.tp;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ValidPalindrome2 {
    public static boolean isPalindrome(String s) {
        // WRITE YOUR BRILLIANT CODE HERE

        if (s == null) return false;

        // make all characters lower case
        s = s.toLowerCase();

        int left = 0;
        int right = s.length()-1;

        while(left < right) {
            // determine if left char is valid
            if (!Character.isLetterOrDigit(s.charAt(left))) {
                left++;
                continue;
            }

            // determine if right char is valid
            if (!Character.isLetterOrDigit(s.charAt(right))) {
                right--;
                continue;
            }

            // if they are both valid, then compare them
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            else {
                left++;
                right--;
            }
        }

        // left and right should at most, 1 character difference
        return Math.abs(left - right) <= 1;
    }

    /*
    remove the non-alpha numberic first
     */
    public static boolean isPalindrome2(String s) {
        List<Character> arr = s.codePoints().mapToObj(c -> (char) c).filter(c -> Character.isLetterOrDigit(c)).collect(Collectors.toList());
        int l = 0;
        int r = arr.size() - 1;
        while (l < r) {
            if (Character.toLowerCase(arr.get(l)) != Character.toLowerCase(arr.get(r))) return false;
            l++;
            r--;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        scanner.close();
        boolean res = isPalindrome(s);
        System.out.println(res);
    }
}
