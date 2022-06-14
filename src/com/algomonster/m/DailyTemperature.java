package com.algomonster.m;

import java.util.*;
import java.util.stream.Collectors;

public class DailyTemperature {
    public static List<Integer> dailyTemperatures(List<Integer> t) {
        // WRITE YOUR BRILLIANT CODE HERE
        if (t == null || t.size() == 0) return new ArrayList<>();

        ArrayList<Integer> result = new ArrayList<>(Collections.nCopies(t.size(), 0));
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        for (int i=0; i<t.size(); i++) {
            while (queue.size() > 0 && t.get(queue.peekLast()) < t.get(i)) {
                int index = queue.pollLast();
                int change = i - index;
                result.set(index, change);
            }
            queue.addLast(i);
        }

        return result;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> t = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
        scanner.close();
        List<Integer> res = dailyTemperatures(t);
        System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}
