package com.algomonster.g;

import java.util.*;
import java.util.stream.Collectors;

public class AlienDictionary {

    public static String alienDictionary(List<String> words) {

        // sort and arrange the letters to form an adjcent list i.e.
        // create a hashmap of letters, and in each entry, create a list of adjacent characters
        // scan through 2 words letter by letter, i and i+1
        // each letter, add it to the map if they are the same
        // if they are different, still add them together in the map, but create an adjacency link that
        //      the second differing character depends on the first differing character
        // then move to the next word after encountering different characters
        // if the next character exist in the first word, but does not exist in the second word, that means we dont have a way to compare

        HashMap<Character, List<Character>> graph = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                if (!graph.containsKey(c)) {
                    graph.put(c, new ArrayList<>());
                }
            }
        }

        for (int i=0; i<words.size()-1; i++) {  // we dont need the last, since we will compare them with i+1
            String w1 = words.get(i);
            String w2 = words.get(i+1);

            for (int w1i=0; w1i<w1.length(); w1i++) {
                if (w1i >= w2.length()) return "";        // cant be formed properly
                if (w1.charAt(w1i) != w2.charAt(w1i)) {
                    // w2 depends on w1
                    graph.get(w2.charAt(w1i)).add(w1.charAt(w1i));
//                    graph.get(w1.charAt(w1i)).add(w2.charAt(w1i));
                    break;
                }
            }
        }

        // from this point on, we can use BFS / Khans algorithm, or DFS

        // lets use BFS
        // we have to count the parents of the nodes in the graph
        HashMap<Character, Integer> count = new HashMap<>();
        for (Character c : graph.keySet()) {
            count.put(c, 0);
        }
        for (Map.Entry<Character, List<Character>> entry : graph.entrySet()) {
            for (Character neighbors : entry.getValue()) {
                count.put(neighbors, count.getOrDefault(neighbors, 0)+1);
            }
        }

        List<Character> result = new ArrayList<>();

        ArrayDeque<Character> queue = new ArrayDeque<>();
        // put the character where count = 0
        count.entrySet().forEach(entry -> {
            if (entry.getValue() == 0) queue.add(entry.getKey());
        });

        while (queue.size() > 0) {
            char c = queue.poll();
            result.add(c);
            for (Character neighbor : graph.get(c)) {
                count.put(neighbor, count.get(neighbor)-1);
                if (count.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }

        for (int cnt : count.values()) {
            if (cnt != 0) return "";
        }

        Collections.reverse(result);

        String r = result.stream().map(Object::toString).collect(Collectors.joining());

        return r;
    }

    public static boolean dfs(List<Character> result, HashSet<Character> visited, Character c, HashMap<Character, List<Character>> graph) {
        if (visited.contains(c)) return true;

        visited.add(c);
        List<Character> neighbors = graph.get(c);
        for (char d : neighbors) {
            if (dfs(result, visited, d, graph))
                return true;
        }
        visited.remove(c);
        result.add(c);
        return false;
    }

    public static void main(String[] sa) {

        List<String> words = List.of("wrt", "wrf", "er", "ett", "rftt");

        System.out.println(alienDictionary(words));
        System.out.println(alienDictionary(words).equals("wertf"));
    }
}
