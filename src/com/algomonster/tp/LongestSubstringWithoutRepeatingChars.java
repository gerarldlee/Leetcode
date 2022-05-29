package com.algomonster.tp;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class LongestSubstringWithoutRepeatingChars {
    public static int longestSubstringWithoutRepeatingCharacters(String s) {
        // WRITE YOUR BRILLIANT CODE HERE

        // hash set to monitor the sliding windows values
        Set<Character> set = new HashSet<>();

        int count = 0;
        int left = 0, right = 0;

        while (right < s.length()) {
            char c = s.charAt(right);
            if (set.contains(c)) {
                set.remove(c);
                left++;
            }
            else {
                set.add(c);
                right++;
            }

            count = Math.max(count, right - left);
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        scanner.close();
        int res = longestSubstringWithoutRepeatingCharacters(s);
        System.out.println(res);
    }
}
