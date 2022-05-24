package com.algomonster.tp;

import java.util.*;

public class MinimumWindowSubstring {
    public static boolean isWindowComplete(HashMap<Character, Integer> checkmap, HashMap<Character, Integer> window) {
        // the size of running window should at least be greater than checkmap
        if (checkmap.size() > window.size()) return false;

        // check the window against the checkmap, and deduct the counts
        // all the chars from checkmap should be in the window, but not vice versa
        for (Character c : checkmap.keySet()) {
            if (!window.containsKey(c))
                return false;
            if (window.get(c) - checkmap.get(c) < 0)
                return false;
        }
        return true;
    }

    public static String getMinimumWindow(String original, String check) {
        // WRITE YOUR BRILLIANT CODE HERE

        if (original.length() < check.length()) return "";

        // checkmap counts
        HashMap<Character, Integer> checkmap = new HashMap<>();
        for (Character c: check.toCharArray()) {
            checkmap.put(c, checkmap.getOrDefault(c, 0) + 1);
        }
        // running window map
        HashMap<Character, Integer> runningwindow = new HashMap<>();
        int absolute_l = 0, absolute_r = 0, absolute_length = original.length();
        String window = original;

        for (int left=0, right=0; right < original.length(); right++) {
            char c = original.charAt(right);
            runningwindow.put(c, runningwindow.getOrDefault(c, 0) + 1);

            // try to compare if the window is valid and complete
            if (isWindowComplete(checkmap, runningwindow)) {

                // try to shrink the window then
                while(left <= right && isWindowComplete(checkmap, runningwindow)) {
                    // is this lexicographically smaller than the previous?
                    String current = original.substring(left, right+1);
                    if (right+1 - left <= absolute_length && current.compareTo(window) < 0) {
                        absolute_l = left;
                        absolute_r = right+1;
                        absolute_length = absolute_r - absolute_l;
                        window = current;
                    }

                    // remove the left value from the runningwindow
                    char l = original.charAt(left);
                    runningwindow.put(l, runningwindow.get(l) - 1);

                    left++;
                }
            }
        }

        return original.substring(absolute_l, absolute_r);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String original = scanner.nextLine();
        String check = scanner.nextLine();
        scanner.close();
        String res = getMinimumWindow(original, check);
        System.out.println(res);
    }
}
