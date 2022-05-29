package com.algomonster.h;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Smallest3PriorityQueue {
    public static List<Integer> heapTop3(List<Integer> arr) {
        // WRITE YOUR BRILLIANT CODE HERE

        PriorityQueue<Integer> heap = new PriorityQueue<>();

        for (Integer i : arr) {
            heap.add(i);
        }

        return List.of(heap.remove(), heap.remove(), heap.remove());
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> arr = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
        scanner.close();
        List<Integer> res = heapTop3(arr);
        System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}
