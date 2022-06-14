package com.algomonster.m;

import java.util.*;
import java.util.stream.Collectors;

public class SlidingWindowMaximum {
    public static List<Integer> slidingWindowMaximum(List<Integer> nums, int k) {
        // WRITE YOUR BRILLIANT CODE HERE

        ArrayDeque<Integer> queue = new ArrayDeque<>();    // we need to store the index of nums
        ArrayList<Integer> result = new ArrayList<>();

        for (int i=0; i<nums.size(); i++) {
            // we need to delete the entries of queue when it is less than the current num(i)
            while (queue.size() > 0 && nums.get(queue.peekLast()) <= nums.get(i)) {
                queue.pollLast();
            }
            // add the i, guaranteeing that i is max in the queue, or is less than max
            queue.addLast(i);

            // if the first element is outside the window, poll it
            if (queue.peekFirst() <= i-k) {
                queue.pollFirst();
            }

            // if the window has been reached, then we need to determine the max
            // which is already present in the queue, as its first entry, since we have poll
            // all those elements less than them
            if (k-1 <= i) {
                result.add(nums.get(queue.peekFirst()));
            }
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
        List<Integer> res = slidingWindowMaximum(nums, k);
        System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}
