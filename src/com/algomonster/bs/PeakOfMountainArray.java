package com.algomonster.bs;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PeakOfMountainArray {
    public static int peakOfMountainArray(List<Integer> arr) {
        // WRITE YOUR BRILLIANT CODE HERE

        int left = 0;
        int right = arr.size() - 1;
        int res = -1;

        int last = right;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (arr.get(mid) > arr.get(mid + 1)) {
                res = mid;
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }

        }

        return res;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> arr = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
        scanner.close();
        int res = peakOfMountainArray(arr);
        System.out.println(res);
    }
}
