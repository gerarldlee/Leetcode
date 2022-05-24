package com.algomonster.g;

import java.util.*;
import java.util.stream.Collectors;

public class VanillaShortestPath {

    // gets the adjacent neighbors of a node using its index
    private static List<Integer> getNeighbor(List<List<Integer>> graph, int node) {
        return graph.get(node);
    }

    public static int shortestPath(List<List<Integer>> graph, int a, int b) {
        // WRITE YOUR BRILLIANT CODE HERE

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offer(a);    // put the "a" index node, from "graph" array
        HashSet<Integer> visited = new HashSet<>();
        visited.add(a);    // put node "a" as visited

        int path = 0;
        while (!queue.isEmpty() ) {
            int size = queue.size();
            while (size > 0) {
                size--;
                int node = queue.pop();
                // check if node is b, return path
                if (node == b)
                    return path;
                for (int neighbor : getNeighbor(graph, node)) {
                    // if a neighbor has not yet been visited, add it to queue
                    if (!visited.contains(neighbor)) {
                        queue.offer(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
            path++;
        }

        return path;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int graphLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < graphLength; i++) {
            graph.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        int a = Integer.parseInt(scanner.nextLine());
        int b = Integer.parseInt(scanner.nextLine());
        scanner.close();
        int res = shortestPath(graph, a, b);
        System.out.println(res);
    }
}
