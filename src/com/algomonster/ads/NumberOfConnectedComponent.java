package com.algomonster.ads;

import java.util.*;
import java.util.stream.Collectors;

public class NumberOfConnectedComponent {
    

    public static class DisjointSet {
        HashMap<Integer, Integer> map = new HashMap<>();
        public boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }
        public void union(int x, int y) {
            map.put(find(x), find(y));
        }
        public Integer find(int x) {
            int z = map.getOrDefault(x, x);
            if (z != x) {
                z = find(z);
                map.put(x, z);
            }
            return z;
        }
    }
    public static List<Integer> numberOfConnectedComponents(int n, List<List<Integer>> connections) {
        // WRITE YOUR BRILLIANT CODE HERE
        ArrayList<Integer> result = new ArrayList<>();
        DisjointSet dsu = new DisjointSet();
        
        for (List<Integer> component : connections) {
            Integer c1 = component.get(0);
            Integer c2 = component.get(1);
            if (!dsu.isConnected(c1, c2)) {
                dsu.union(c1, c2);
                result.add(--n);
            }
            else {
                result.add(n);
            }
        }
        
        
        return result;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int connectionsLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> connections = new ArrayList<>();
        for (int i = 0; i < connectionsLength; i++) {
            connections.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        scanner.close();
        List<Integer> res = numberOfConnectedComponents(n, connections);
        System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}
