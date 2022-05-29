package com.algomonster.g;

import java.util.*;
import java.util.stream.Collectors;

public class CourseScheduleDFS {
    private enum State {
        TO_VISIT, VISITING, VISITED
    }

    private static Map<Integer, List<Integer>> buildGraph(List<List<Integer>> prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>(prerequisites.size());
        for (List<Integer> dependency : prerequisites) {
            graph.computeIfAbsent(dependency.get(0), k -> new ArrayList<>()).add(dependency.get(1));
        }
        return graph;
    }

    private static boolean dfs(int start, State[] states, Map<Integer, List<Integer>> graph) {
        // mark self as visiting
        states[start] = State.VISITING;

        if (graph.get(start) != null) {
            for (Integer nextVertex : graph.get(start)) {
                // ignore visited nodes
                if (states[nextVertex] == State.VISITED) continue;
                // revisiting a visiting node, CYCLE!
                if (states[nextVertex] == State.VISITING) return false;
                // recursively visit neighbours
                // if a neighbour found a cycle, we return false right away
                if (!dfs(nextVertex, states, graph)) return false;
            }
        }

        // mark self as visited
        states[start] = State.VISITED;
        // if we have gotten this far, our neighbours haven't found any cycle, return true
        return true;
    }

    public static boolean isValidCourseSchedule(int n, List<List<Integer>> prerequisites) {
        Map<Integer, List<Integer>> graph = buildGraph(prerequisites);
        State[] states = new State[n];
        Arrays.fill(states, State.TO_VISIT);
        // dfs on each node
        for (int i = 0; i < n; i++) {
            if (!dfs(i, states, graph)) return false;
        }
        return true;
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
