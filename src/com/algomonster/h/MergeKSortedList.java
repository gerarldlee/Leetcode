package com.algomonster.h;

import java.util.*;
import java.util.stream.Collectors;

public class MergeKSortedList {
    public static List<Integer> mergeKSortedLists(List<List<Integer>> lists) {
        // WRITE YOUR BRILLIANT CODE HERE

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        lists.forEach(entry -> {
            for (Integer i : entry) {
                heap.add(i);
            }
        });
        List<Integer> res = new ArrayList<>();
        while (heap.size() > 0) {
            res.add(heap.poll());
        }
        return res;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int listsLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < listsLength; i++) {
            lists.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        scanner.close();
        List<Integer> res = mergeKSortedLists(lists);
        System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}
