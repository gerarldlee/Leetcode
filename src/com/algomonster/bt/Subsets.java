package com.algomonster.bt;

import java.util.*;
import java.util.stream.Collectors;

public class Subsets {
    public static List<List<Integer>> subsets(List<Integer> nums) {
        // WRITE YOUR BRILLIANT CODE HERE
        List<List<Integer>> results = new ArrayList();
        backtrack(results, nums, new ArrayList(), 0);
        return results;
    }

    private static void backtrack(List<List<Integer>> results, List<Integer> nums, List<Integer> state, int startIndex) {
        // just add anything in the state
        results.add(new ArrayList(state));

        // use the startIndex to dedup
        for (int i=startIndex; i<nums.size(); i++) {
            state.add(nums.get(i));
            backtrack(results, nums, state, i+1);
            state.remove(state.size()-1);
        }
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> nums = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
        scanner.close();
        List<List<Integer>> res = subsets(nums);
        List<String> output = new ArrayList<>();
        for (List<Integer> x : res) {
            Collections.sort(x);
            List<String> sNumbers = x.stream().map(
                    n -> n.toString()).collect(Collectors.toList()
            );
            output.add(String.join(" ", sNumbers));
        }
        Collections.sort(output);
        for (String row : output) {
            System.out.println(row);
        }
    }
}
