package com.algomonster.bt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CombinationSum {
    public static List<List<Integer>> combinationSum(List<Integer> candidates, int target) {
        // WRITE YOUR BRILLIANT CODE HERE

        List<List<Integer>> results = new ArrayList();
        dfs(candidates, target, results, new ArrayList(), 0);
        return results;
    }

    private static void dfs(List<Integer> candidates, int target, List<List<Integer>> results, List<Integer> tmp, int index) {
        if (target == 0) {
            results.add(new ArrayList(tmp));
            return;
        }

        // index is used to dedup, by starting at index, it wont be able to go back to the first candidate
        // the assumption is that ALL the permutaion of the previous candidate has been explored, so we can
        // eliminate it
        for (int i=index; i<candidates.size(); i++) {
            int remaining = target - candidates.get(i);
            if (remaining >= 0) {
                tmp.add(candidates.get(i));
                dfs(candidates, remaining, results, tmp, i);
                tmp.remove(tmp.size()-1);
            }
        }
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> candidates = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
        int target = Integer.parseInt(scanner.nextLine());
        scanner.close();
        List<List<Integer>> res = combinationSum(candidates, target);
        for (List<Integer> row : res) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }
}
