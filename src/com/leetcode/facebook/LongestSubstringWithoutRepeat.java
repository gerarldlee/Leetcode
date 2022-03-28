package com.leetcode.facebook;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public class LongestSubstringWithoutRepeat {

    /*
    Intuition:
    Using a sliding window, we move the index to the right, and save the character with its index to a map, if this character is not
    found in the map. We increment the max per each unique character encountered, and compare them to its previous max value.
    If a character has been found in the map, then we need to get the index of this character, where it will be used as a starting point
    until another one is found.

    Time: O(N)
    Space: O(N) - worse/average is the same. technically its O(2N) since we are using map and we are just updating index values
     */
//    "abcdefhdijk" - 8
    public int longestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;

        int max = 0, start = 0;
        Map<Character, Integer> count = new HashMap<>();
        for (int end=0; end<s.length(); end++) {
            if (count.containsKey(s.charAt(end))) {
                start = Math.max(start, count.get(s.charAt(end)));  // edge case where the start < previous start
            }
            count.put(s.charAt(end), end + 1);  // put the index of the char
            max = Math.max(max, end + 1 - start);
        }
        return max;
    }


    /*
    Intuition:
    Using the sliding window technique, we will move the ending index to the right, and insert the character to the Set of unique characters
    Once the duplicate character has been found, then we need to clear out the previous characters until up to the duplicate character
    excluding itself. Then we continue moving the ending index again until the string S is finished.

    Time: O(N)
    Space: O(N) - worse, O(N-M) - average, where M = duplicate index, if there are duplicate characters
     */
    public int lengthOfLongestSubstring(String s) {
        int start = 0;
        int end = 0;
        int max = 0;

        HashSet<Character> set = new HashSet<>();
        while (end < s.length()) {
            if (!set.contains(s.charAt(end))) {
                set.add(s.charAt(end));
                max = Math.max(set.size(), max);
                end++;
            }
            else {
                set.remove(s.charAt(start));
                start++;
            }
        }
        return max;
    }

    public static void main(String[] a) {
        LongestSubstringWithoutRepeat l = new LongestSubstringWithoutRepeat();
        System.out.println(l.longestSubstring("abba"));
    }
}
