package com.leetcode.facebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LetterCombinations {

    String[][] map = new String[8][3];

    LetterCombinations() {
        // map with i = 0 + 2
        map[0] = new String[] {"a", "b", "c"};
        map[1] = new String[] {"d", "e", "f"};
        map[2] = new String[] {"g", "h", "i"};
        map[3] = new String[] {"j", "k", "l"};
        map[4] = new String[] {"m", "n", "o"};
        map[5] = new String[] {"p", "q", "r", "s"};
        map[6] = new String[] {"t", "u", "v"};
        map[7] = new String[] {"w", "x", "y", "z"};
    }

    public List<String> populateList(int number, List<String> result) {
        List<String> n = new ArrayList<>();
        // multiply and append each item in the result.
        for (int i=0; i<map[number].length; i++) {
            if (!result.isEmpty()) {
                for (String s: result) {
                    n.add(s + map[number][i]);
                }
            }
            else {
                n.add(map[number][i]);
            }
        }
        return n;
    }

    // possible combinations is (3 * digits.length)! factorial
    public List<String> letterCombinations(String digits) {
        List<String> s = new ArrayList<>();
        if (digits.length() < 1) return s;

        for (int i=0; i < digits.length(); i++) {
            char c = digits.charAt(i);

            s = populateList(c-50, s);
        }
        return s;
    }

    public static void main(String[] a) {
        LetterCombinations n = new LetterCombinations();
        List<String> l = n.letterCombinations("23");

    }

    public static void printList(List<String> s) {
        System.out.println(Arrays.toString(s.toArray()));
    }
}
