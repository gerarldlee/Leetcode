package com.algomonster.tp;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TwoSumSorted {
    public static List<Integer> twoSumSorted(List<Integer> arr, int target) {
        // WRITE YOUR BRILLIANT CODE HERE

        // indexes
        int left = 0;
        int right = arr.size()-1;

        while(left < right) {
            // because it is sorted, sum of both left and right
            // if > target, move right --, else move left ++

            if (arr.get(left) + arr.get(right) < target) {
                left++;
            }
            else if (arr.get(left) + arr.get(right) > target) {
                right--;
            }
            else {
                break;
            }
        }

        return List.of(left, right);
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> arr = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
        int target = Integer.parseInt(scanner.nextLine());
        scanner.close();
        List<Integer> res = twoSumSorted(arr, target);
        System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}
