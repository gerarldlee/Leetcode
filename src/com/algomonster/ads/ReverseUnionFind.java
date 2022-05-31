package com.algomonster.ads;

import java.util.*;

public class ReverseUnionFind {
    
    public static class UnionFind<T> {
        private HashMap<T, T> id = new HashMap<>();

        public T find(T x) {
            T y = id.getOrDefault(x, x);
            if (y != x) {
                y = find(y);
                id.put(x, y);
            }
            return y;
        }

        public void union(T x, T y) {
            id.put(find(x), find(y));
        }
    }
    
    public static List<Integer> umbristan(int n, List<List<Integer>> breaks) {
        // WRITE YOUR BRILLIANT CODE HERE
        UnionFind<Integer> dsu = new UnionFind();
        List<Integer> result = new ArrayList<>();
        
        Collections.reverse(breaks);
        
        for (List<Integer> connectedCities : breaks) {
            Integer city1 = connectedCities.get(0);
            Integer city2 = connectedCities.get(1);
            
            result.add(n);
            if (dsu.find(city1) != dsu.find(city2)) {
                 dsu.union(city1, city2);        // for every connected city means less n
                --n;
            }
        }
        Collections.reverse(result);
        return result;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int breaksLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> breaks = new ArrayList<>();
        for (int i = 0; i < breaksLength; i++) {
            breaks.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        scanner.close();
        List<Integer> res = umbristan(n, breaks);
        System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}
