package com.algomonster.bt;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class WordBreak {
    public static boolean wordBreak(String s, List<String> words) {
        // WRITE YOUR BRILLIANT CODE HERE

        if (s == null || words == null) return false;

        return dfs(words, 0, s, new StringBuilder(), new Boolean[s.length()]);
    }

    public static boolean dfs(List<String> words, int length, String target, StringBuilder state, Boolean[] memo) {

        if (target.equals(state.toString())) {
            return true;
        }

        if (memo[length] != null) return memo[length];

        boolean res = false;
        for (String word : words) {

            String subtarget = target.substring(length);
            if (subtarget.startsWith(word)) {       // eliminate recursion for invalid words
                state.append(word);
                int newLength = length + word.length();
                res = dfs(words, newLength, target, state, memo);
                if (res) {
                    break;
                }
                else {
                    state.delete(length, newLength);
                }
            }
        }

        memo[length] = res;

        return res;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        List<String> words = splitWords(scanner.nextLine());
        scanner.close();
        boolean res = wordBreak(s, words);
        System.out.println(res);
    }
}
