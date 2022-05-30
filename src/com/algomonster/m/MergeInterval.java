package com.algomonster.m;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MergeInterval {

    // overlapping is when: x2 >= y1 or y1 <= x2
    // left most border = Math.max(x1, y1) <= right most border = Math.min(x2, y2)
    public static boolean isOverlapping(int x1, int x2, int y1, int y2) {
        return Math.max(x1, y1) <= Math.min(x2, y2);
    }

    public static List<List<Integer>> mergeIntervals(List<List<Integer>> intervals) {
        // WRITE YOUR BRILLIANT CODE HERE
        List<List<Integer>> result = new ArrayList<>();
        int i=0;
        while (i<intervals.size()-1) {
            // compare i and i+1
            List<Integer> x = intervals.get(i);
            List<Integer> y = intervals.get(i+1);
            if (isOverlapping(x.get(0), x.get(1), y.get(0), y.get(1))) {
                // the left border of the overlap is Math.min(x1, y1)
                // right border of the overlap is Math.max(x2, y2)
                result.add(List.of(Math.min(x.get(0), y.get(0)), Math.max(x.get(1), y.get(1))));
            }
            else {
                // add them both into the list
                result.add(x);
                result.add(y);
            }
            i+=2;
        }

        return result;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int intervalsLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> intervals = new ArrayList<>();
        for (int i = 0; i < intervalsLength; i++) {
            intervals.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        scanner.close();
        List<List<Integer>> res = mergeIntervals(intervals);
        for (List<Integer> row : res) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }
}
