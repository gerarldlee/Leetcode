package com.algomonster.ads;

import java.util.*;

public class SizeOfConnectedComponent {
    public static class DisjointSet {
        HashMap<Integer, Integer> map = new HashMap<>();
        public Integer find(int x) {
            int z = map.getOrDefault(x, x);
            if (z != x) {
                z = find(z);
                map.put(x, z);
            }
            return z;
        }
        public void union(int x, int y) {
            map.put(find(x), find(y));
        }
        public boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }
        public int count(int x) {
            int root = find(x);
            int count = 1;
            // find all the elements in the map that has the root as its parent
            for (int key : map.keySet()) {
                if (root == find(key)) {
                    count++;
                }
            }
            return count;
        }
    }


    public static class SetCounter {
        DisjointSet dsu = new DisjointSet();
        public void merge(int x, int y) {
            // WRITE YOUR BRILLIANT CODE HERE
            dsu.union(x, y);
        }

        public int count(int x) {
            // AND HERE
            return dsu.count(x);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SetCounter sol = new SetCounter();
        int queryLength = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < queryLength; i++) {
            String[] segs = scanner.nextLine().split(" ");
            String op = segs[0];
            int x = Integer.parseInt(segs[1]);
            if (op.equals("union")) {
                int y = Integer.parseInt(segs[2]);
                sol.merge(x, y);
            } else if (op.equals("count")) {
                int res = sol.count(x);
                System.out.println(res);
            }
        }
        scanner.close();
    }
}
