package com.algomonster.ads;

import java.util.*;

public class UnionFind {

    public static class SameSet {
        HashMap<Integer, Integer> map = new HashMap<>();
        
        private Integer find(int x) {
            int z = map.getOrDefault(x, x);    // if x does not belong, return x
            if (x != z) {        // if x belongs to a set, look for the set's parent
                z = find(z);
                map.put(x, z);    // compress the path
            }
            return z;
        }
        
        public void merge(int x, int y) {
            // WRITE YOUR BRILLIANT CODE HERE
            map.put(find(x), find(y));    // put y in the set of x
        }

        public boolean isSame(int x, int y) {
            // AND HERE
            int parent = find(y);    // check if y belongs to the set of x
            return parent == find(x);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SameSet sol = new SameSet();
        int queryLength = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < queryLength; i++) {
            String[] segs = scanner.nextLine().split(" ");
            String op = segs[0];
            int x = Integer.parseInt(segs[1]);
            int y = Integer.parseInt(segs[2]);
            if (op.equals("union")) {
                sol.merge(x, y);
            } else if (op.equals("is_same")) {
                boolean res = sol.isSame(x, y);
                System.out.println(res);
            }
        }
        scanner.close();
    }
}