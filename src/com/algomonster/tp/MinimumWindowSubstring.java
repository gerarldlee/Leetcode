package com.algomonster.tp;

import java.util.*;

public class MinimumWindowSubstring {

    public static String getMinimumWindow(String original, String check) {
        // WRITE YOUR BRILLIANT CODE HERE

        if (original == null || check == null) return "";

        // put all the check into a count map
        HashMap<Character, Integer> countMap = new HashMap<>();
        for (char c : check.toCharArray()) countMap.put(c, countMap.getOrDefault(c, 0)+1);

        // begin moving right pointer, and within the window, put everything to the window map
        // once everything is in the window map, try to decrease the window size and the map
        // but record the left and right window first.
        // try to find a smaller window by moving the right to the right, until all of the original
        // length is finished. then return the window
        int left=0, right=0;
        int start=left, windowSize=Integer.MAX_VALUE;
        HashMap<Character, Integer> windowMap = new HashMap<>();
        int trackWindowCount=0;    // we also need a tracker for the correct elements in the window map
        for (; right < original.length(); right++) {
            char c = original.charAt(right);
            windowMap.put(c, windowMap.getOrDefault(c,0)+1);

            if (countMap.containsKey(c) && windowMap.containsKey(c) && countMap.get(c) == windowMap.get(c)) {
                trackWindowCount++;
            }

            while (trackWindowCount == countMap.size()) {
                // update the result. we need to compare if the window is shorter than the previous window
                if (windowSize == Integer.MAX_VALUE || right+1 - left < windowSize) {
                    start = left;
                    windowSize = (right+1 - left);
                }
                else if (right+1 - left == windowSize) {
                    // if they are the same length though, compare its lexicographically smaller
                    String previous = original.substring(start, windowSize+start);
                    String current = original.substring(left, right+1);
                    if (previous.compareTo(current) > 0) {
                        start = left;
                        windowSize = (right+1 - left);
                    }
                }

                // decrease the window
                char l = original.charAt(left);
                windowMap.put(l, windowMap.get(l)-1);
                if (countMap.containsKey(l) && windowMap.get(l) < countMap.get(l)) {
                    trackWindowCount--;
                }
                left++;
            }
        }

        if (windowSize == Integer.MAX_VALUE) return "";
        return original.substring(start, windowSize+start);
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
