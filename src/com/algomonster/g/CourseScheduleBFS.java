package com.algomonster.g;

import java.util.*;
import java.util.stream.Collectors;

public class CourseScheduleBFS {

    private static Map<Integer, Integer> countDeps(List<List<Integer>> prerequisites, int n) {
        Map<Integer, Integer> count = new HashMap<>();
        // add the 0 to n-1 to represent the courses
        for (int i=0; i<n; i++) {
            count.put(i, 0);
        }

        // increment the dependent courses by 1
        for (List<Integer> dependencies : prerequisites) {
            Integer dependent = dependencies.get(0);
            count.put(dependent, count.get(dependent)+1);
        }
        return count;
    }

    private static List<Integer> getNeighbors(List<List<Integer>> prerequisites, Integer course) {
        List<Integer> neighbors = new ArrayList();
        for (List<Integer> dependencies : prerequisites) {
            Integer dependent = dependencies.get(0);
            Integer independent = dependencies.get(1);
            if (independent.equals(course)) {
                neighbors.add(dependent);
            }
        }
        return neighbors;
    }

    public static boolean isValidCourseSchedule(int n, List<List<Integer>> prerequisites) {
        // WRITE YOUR BRILLIANT CODE HERE

        // arrange a dependency count of courses
        Map<Integer, Integer> count = countDeps(prerequisites, n);
        ArrayDeque<Integer> queue = new ArrayDeque();

        // enlist all those courses that does not have dependencies into the queue to take
        count.entrySet().forEach(entry -> {
            if (entry.getValue() == 0) queue.add(entry.getKey());
        });

        int coursesTaken = 0;
        while (queue.size() > 0) {
            Integer course = queue.poll();
            coursesTaken++;
            // get the courses that depend on course
            for (Integer c : getNeighbors(prerequisites, course)) {
                // reduce the count since this course has been taken
                count.put(c, count.get(c) - 1);
                // when a count == 0, then we can take the course
                if (count.get(c) == 0) {
                    queue.add(c);
                }
            }
        }

        return n == coursesTaken;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int prerequisitesLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> prerequisites = new ArrayList<>();
        for (int i = 0; i < prerequisitesLength; i++) {
            prerequisites.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        scanner.close();
        boolean res = isValidCourseSchedule(n, prerequisites);
        System.out.println(res);
    }
}
