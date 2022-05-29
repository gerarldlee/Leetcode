package com.algomonster.h;

import java.util.*;
import java.util.stream.Collectors;

public class KLargestElement {
    public static int findKthLargest(List<Integer> nums, int k) {
        // WRITE YOUR BRILLIANT CODE HERE

        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (Integer i : nums) {
            queue.add(i);
        }

        int result = 0;
        while (k < queue.size() && k > 0) {
            result = queue.poll();
            k--;
        }

        return result;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> nums = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
        int k = Integer.parseInt(scanner.nextLine());
        scanner.close();
        int res = findKthLargest(nums, k);
        System.out.println(res);
    }
}
