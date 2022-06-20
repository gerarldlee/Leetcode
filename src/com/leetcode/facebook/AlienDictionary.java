package com.leetcode.facebook;

import java.util.*;
import java.util.stream.Collectors;

public class AlienDictionary {
    // count the number of parents that a particular node has by adding to the node,
    // need to initialize this before
    public static <T> Map<T, Integer> countParents(Map<T, List<T>> graph) {
        Map<T, Integer> counts = new HashMap<>();
        graph.keySet().forEach(node -> {
            counts.put(node, 0);
        });
        // loop through every node and add to the child node 1 parent
        graph.entrySet().forEach(entry -> {
            for (T node : entry.getValue()) {
                counts.put(node, counts.get(node) + 1);
            }
        });
        return counts;
    }

    // topological sort the list
    public static List<Character> topoSort(Map<Character, List<Character>> graph) {
        // return a list of the topological sorted list
        List<Character> res = new ArrayList<>();
        // make a queue that we will use for our solution
        PriorityQueue<Character> q = new PriorityQueue<>();
        // loop through all nodes and add all nodes that do not have any parent
        Map<Character, Integer> counts = countParents(graph);
        counts.entrySet().forEach(entry -> {
            if (entry.getValue() == 0) {
                q.add(entry.getKey());
            }
        });
        // perform bfs with queue, mostly the same as template bfs
        while (!q.isEmpty()) {
            char node = q.poll();
            // add node to list to keep track of topological order
            res.add(node);
            for (char child : graph.get(node)) {
                // subtract one from every neighbour
                counts.put(child, counts.get(child) - 1);
                // once the number of parents reaches zero you add it to the queue
                if (counts.get(child) == 0) {
                    q.add(child);
                }
            }
        }

        for (int count : counts.values()){
            if (count != 0) {
                return null;
            }
        }
        return res;
    }

    public static String alienOrder(List<String> words) {
        // init graph
        HashMap<Character, List<Character>> graph = new HashMap<>();
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (!graph.containsKey(c)) {
                    graph.put(c, new ArrayList<>());
                }
            }
        }

        String prev = words.get(0);
        // derive order from adjacent words
        for (int i = 1; i < words.size(); i++) {
            String cur = words.get(i);
            for (int j = 0; j < prev.length() && j < cur.length(); j++) {
                // ignore duplicates
                if (prev.charAt(j) != cur.charAt(j)) {
                    if (!graph.get(prev.charAt(j)).contains(cur.charAt(j))) {
                        graph.get(prev.charAt(j)).add(cur.charAt(j));
                    }
                    break;
                }
            }
            prev = cur;
        }

        List<Character> sorted = topoSort(graph);
        return sorted == null ? ""
                : sorted.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        List<String> words = splitWords(scanner.nextLine());
//        scanner.close();

        List<String> words = List.of("wrt","wrf","er","ett","rftt");

        String res = alienOrder(words);
        System.out.println(res);
    }
}
