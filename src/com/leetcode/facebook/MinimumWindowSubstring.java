package com.leetcode.facebook;

import java.util.HashMap;

public class MinimumWindowSubstring {

    /*

     */
    public String minWindow(String s, String t) {

        if (s == null ||
                s.length() == 0 ||
                t == null ||
                t.length() == 0) return "";

        HashMap<Character, Integer> dict = new HashMap<>();
        for (char c : t.toCharArray()) {
            dict.put(c, dict.getOrDefault(c, 0) + 1);
        }

        int completed = 0;
        int[] resulting_index = new int[2];
        int window_length = Integer.MAX_VALUE;

        HashMap<Character, Integer> window = new HashMap<>();
// create a sliding window
        int l = 0;
        for (int r = 0; r < s.length(); r++) {

            // add each character into the window
            char c = s.charAt(r);
            window.put(c, window.getOrDefault(c, 0) + 1);

            // completed is an easy way of determining the count of unique entries in the window
            if (dict.containsKey(c) && dict.get(c).equals(window.get(c))) {
                completed++;
            }

            // determine if the window is complete?
            // and try to shrink the window ONCE we completed the dictionary
            while (l <= r && completed == dict.size()) {

                // add the l and the r index to resulting_index
                if (r - l + 1 < window_length) {
                    window_length = r - l + 1;
                    resulting_index[0] = l;
                    resulting_index[1] = r + 1;
                }

                // remove to validate the string inside the window
                char cc = s.charAt(l);
                window.put(cc, window.get(cc) - 1);

                // but also decrease the value of completed if the one that got decreased is a valid dict letter
                if (dict.containsKey(cc) && window.get(cc) < dict.get(cc)) {
                    completed--;
                }

                // System.out.println("reduce: " + completed + " " + cc);
                l++;
            }

        }

        return s.substring(resulting_index[0], resulting_index[1]);
    }
}
