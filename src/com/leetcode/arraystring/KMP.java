package com.leetcode.arraystring;

public class KMP {

    public int search(String haystack, String needle) {
        if (needle == null || needle.length() == 0) return 0;

        int[] prefixes = new int[needle.length()];

        int prevPrefixIndex = 0;
        int i = 1;
        while (i < needle.length()) {
            if (needle.charAt(i) == needle.charAt(prevPrefixIndex)) {
                prefixes[i] = prevPrefixIndex + 1;
                prevPrefixIndex++;
                i++;
            }
            else if (prevPrefixIndex == 0) {
                prefixes[i] = 0;
                i++;
            }
            else {
                prevPrefixIndex = prefixes[prevPrefixIndex - 1];
            }
        }

        int haystack_idx = 0;
        int needle_idx = 0;
        while (haystack_idx < haystack.length()) {
            if (haystack.charAt(haystack_idx) == needle.charAt(needle_idx)) {
                haystack_idx++;
                needle_idx++;
            }
            else {
                if (needle_idx == 0) {
                    haystack_idx++;
                }
                else {
                    needle_idx = prefixes[needle_idx - 1];
                }
            }

            if (needle_idx == needle.length()) {
                return haystack_idx - needle.length();
            }
        }
        return -1;
    }

    public static void main(String[] a) {
        String haystack = "ASDFASFASDFASDGERARDFGASDFASDFASDFADSFSADF";
        String needle = "GERARD";

        KMP k = new KMP();

        System.out.println(k.search(haystack, needle)); // 14
    }
}
