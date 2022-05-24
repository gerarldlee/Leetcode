package com.algomonster.bt;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DecodeWays {
    private static final List<String> LETTERS = IntStream.range(1, 27).mapToObj(Integer::toString).collect(Collectors.toList());

    public static int decodeWays(String digits) {
        // WRITE YOUR BRILLIANT CODE HERE
        return dfs(digits, 0, new int[digits.length()+1]);
    }

    public static int dfs(String digits, int index, int[] memo) {

        if (digits.length() == index) {
            return 1;
        }

        if (memo[index] > 0) return memo[index];

        String newDigits = digits.substring(index);
        for (int i=1; i <= 26; i++) {            // iterate and compare remaining
            String prefix = String.valueOf(i);
            if (newDigits.startsWith(prefix)) {
                memo[index] += dfs(digits, index + prefix.length(), memo);
            }
        }
        return memo[index];
    }
    // Driver code
    public static void main(String[] args) {

        String[] inputs = {"12", "123", "11223"};
        for (int i = 0; i<inputs.length; i++) {
            System.out.println("Decode ways : " + DecodeWays.decodeWays(inputs[i]));
        }
    }
}
