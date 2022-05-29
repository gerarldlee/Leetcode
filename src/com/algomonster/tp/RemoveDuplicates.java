package com.algomonster.tp;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RemoveDuplicates {
    public static int removeDuplicates(List<Integer> arr) {
        // WRITE YOUR BRILLIANT CODE HERE
        if (arr == null || arr.size() == 0) return 0;

        int uniquePointer = 0;
        int dupPointer = 1;

        while (dupPointer < arr.size()) {
            if (arr.get(uniquePointer) != arr.get(dupPointer)) {
                uniquePointer++;
                arr.set(uniquePointer, arr.get(dupPointer));
            }
            dupPointer++;
        }

        return uniquePointer + 1;	// because the length is non-zero
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> arr = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
        scanner.close();
        int res = removeDuplicates(arr);
        System.out.println(arr.stream().limit(res).map(String::valueOf).collect(Collectors.joining(" ")));
    }
}
