package com.leetcode.facebook;

public class OneEditDistance {

    public boolean isOneEditDistance(String s, String t) {

        if (s == null || t == null) return false;

        // there can only be 1 edit distance
        int diff = Math.abs(s.length() - t.length());
        if (diff >= 2) return false;

        String shorterString = s;
        String longerString = t;
        if (longerString.length() < shorterString.length()) {
            shorterString = t;
            longerString = s;
        }

        int mistake = 0;
        for (int i=0; i<shorterString.length(); i++) {

            // if there is at least 1 difference
            if (shorterString.charAt(i) != longerString.charAt(i)) {
                mistake++;
                if (mistake > 1) return false;

                // if they are of equal length, then skip them, as it will mean 1 edit has been made
                // otherwise compare the shorterString with longerString's substring starting from i+1
                if (shorterString.length() != longerString.length()) {
                     return shorterString.substring(i).equals(longerString.substring(i+1));
                }
            }
            // System.out.println("i=" + i + ", l=" + l + ", mistake: " + mistake);
        }

        // only 1 edit distance
        return mistake == 1 || diff == 1;
    }
}
