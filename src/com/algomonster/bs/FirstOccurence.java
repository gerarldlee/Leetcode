package com.algomonster.bs;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FirstOccurence {
    public static int findFirstOccurrence(List<Integer> arr, int target) {
        // WRITE YOUR BRILLIANT CODE HERE
        int n = arr.size();
        int begin = 0;
        int end = n-1;
        int ret = -1;

        while (begin <= end) {

            int mid = begin + (end - begin) / 2;

            if (arr.get(mid) <= target) {
                end = mid - 1;
                if (arr.get(mid) == target)
                    ret = mid;
            }
            else if (arr.get(mid) < target) {
                begin = mid + 1;
            }

        }

        return ret;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> arr = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
        int target = Integer.parseInt(scanner.nextLine());
        scanner.close();
        int res = findFirstOccurrence(arr, target);
        System.out.println(res);
    }
}
