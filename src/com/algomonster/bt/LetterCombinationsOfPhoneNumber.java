package com.algomonster.bt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LetterCombinationsOfPhoneNumber {
    private static final Map<Character, char[]> digitToLetterMap = Map.of(
            '2', "abc".toCharArray(),
            '3', "def".toCharArray(),
            '4', "ghi".toCharArray(),
            '5', "jkl".toCharArray(),
            '6', "mno".toCharArray(),
            '7', "pqrs".toCharArray(),
            '8', "tuv".toCharArray(),
            '9', "wxyz".toCharArray()
    );

    public static List<String> letterCombinationsOfPhoneNumber(String digits) {
        // WRITE YOUR BRILLIANT CODE HERE

        if (digits == null || digits.length() == 0 ) return new ArrayList<>();

        List<String> results = new ArrayList<>();

        dfs(digits, 0, results, new StringBuilder());

        return results;
    }

    public static void dfs(String digits, int index, List<String> results, StringBuilder state) {
        if (state.length() == digits.length()) {
            results.add(state.toString());
            return;
        }

        char c = digits.charAt(index);
        for (char x : digitToLetterMap.get(c)) {

            state.append(x);

            dfs(digits, index+1, results, state);

            state.deleteCharAt(state.length()-1);

        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String digits = scanner.nextLine();
        scanner.close();
        List<String> res = letterCombinationsOfPhoneNumber(digits);
        System.out.println(String.join(" ", res));
    }
}
