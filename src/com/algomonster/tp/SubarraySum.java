package com.algomonster.tp;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SubarraySum {
    public static List<Integer> subarraySum(List<Integer> arr, int target) {
        // WRITE YOUR BRILLIANT CODE HERE

        int left=0, right=0;
        int runningSum = 0;
        while (left <= right && right<=arr.size()) {
            if (runningSum < target) {
                runningSum += arr.get(right);
                right++;
            }
            else if (runningSum > target) {
                runningSum -= arr.get(left);
                left++;
            }
            else {
                return List.of(left, right);
            }
        }

        return List.of(0, 0);
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> arr = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
        int target = Integer.parseInt(scanner.nextLine());
        scanner.close();
        List<Integer> res = subarraySum(arr, target);
        System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}
