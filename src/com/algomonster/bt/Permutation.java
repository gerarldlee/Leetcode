package com.algomonster.bt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Permutation {
    public static List<String> permutations(String letters) {
        // WRITE YOUR BRILLIANT CODE HERE

        if (letters == null || letters.length() == 0) return new ArrayList();

        List<String> results = new ArrayList<>();
        char[] chars = letters.toCharArray();
        boolean[] used = new boolean[chars.length];
        Arrays.fill(used, false);

        permute(chars, used, results, new StringBuilder());

        return results;
    }

    public static void permute(char[] letters, boolean[] used, List<String> results, StringBuilder state) {
        if (state.length() == letters.length) {
            results.add(state.toString());
//             state.setLength(0);
            return;
        }

        for (int i=0; i<letters.length; i++) {

            if (!used[i]) {
                // enter this character into the state
                state.append(letters[i]);
                used[i] = true;

                // recurse with the next index value
                permute(letters, used, results, state);

                // remove this character
//                System.out.printf("%d, %d\n", i, state.length()-1);
                state.deleteCharAt(state.length()-1);
                used[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String letters = scanner.nextLine();
        scanner.close();
        List<String> res = permutations(letters);
        for (String line : res) {
            System.out.println(line);
        }
    }
}
