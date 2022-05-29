package com.algomonster.tp;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MaximumScore {

    public static int maximumScore(List<Integer> arr1, List<Integer> arr2) {

        final int MODULO_AMT = 1000000007;

        int runningSum1 = 0, runningSum2 = 0;
        int left=0, right=0;
        int result = 0;

        // compare both elements in the array, when they are equal, compare both runningSum whichever is higher, add them to result
        // when they are not equal, if arr1 element is less than arr2, add them to runningSum1, vice versa for arr2
        while (left < arr1.size() && right < arr2.size()) {
            int leftElement = arr1.get(left);
            int rightElement = arr2.get(right);
            if (leftElement == rightElement) {
                result += Math.max(runningSum1, runningSum2) + leftElement; // we need to add the current element
                result %= MODULO_AMT;       // we need to mod the amount since it might be large
                runningSum1 = runningSum2 = 0;
                left++;
                right++;
            }
            else if (leftElement < rightElement) {
                runningSum1 += leftElement;
                left++;
            }
            else {
                runningSum2 += rightElement;
                right++;
            }
        }

        // because we only compare up to the shorter element of both arrays, we need to compare up to the end of both arrays
        while (left < arr1.size()) {
            runningSum1 += arr1.get(left++);
        }
        while (right < arr2.size()) {
            runningSum2 += arr2.get(right++);
        }

        result += Math.max(runningSum1, runningSum2);
        result %= MODULO_AMT;

        return result;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        List<Integer> arr1 = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
//        List<Integer> arr2 = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());

        List<Integer> arr1 = List.of(2, 4, 5, 8, 10);
        List<Integer> arr2 = List.of(4, 6, 8, 9);
        scanner.close();
        int res = maximumScore(arr1, arr2);
        System.out.println(res);
    }
}
