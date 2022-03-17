package com.leetcode.graph;

public class NumberOfProvinces {

    /**
     * First we initialize the array of disjoint set 
     * int[] disjointRoot = new int[n];
     * 
     * We create a find() and union() function for the disjoint problem
     * 
     * The trick is to represent this n x n matrix to an array
     * isConnected[i][j] = 1 if connected, 0 otherwise
     * 
     * loop over i
     *   loop over j
     *      if [i][j] == 1
     *          union(i, j)
     * 
     * By default, all the nodes are disconnected. As it gets connected, i != j, the count of these
     * independent nodes decrease.
     * 
     * This count represents the provinces.
     *    
     */
    public int findCircleNum(int[][] isConnected) {
        if (isConnected == null || isConnected.length == 0) {
            return 0;
        }

        int n = isConnected.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }

        return uf.getCount();
    }

    class UnionFind {
        private int[] root;
        private int[] rank;
        private int count;

        UnionFind(int size) {
            root = new int[size];
            rank = new int[size];
            count = size;
            for (int i = 0; i < size; i++) {
                root[i] = i;
                rank[i] = 1;
            }
        }

        int find(int x) {
            if (x == root[x]) {
                return x;
            }
            return root[x] = find(root[x]);
        }

        void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY]) {
                    root[rootY] = rootX;
                } else if (rank[rootX] < rank[rootY]) {
                    root[rootX] = rootY;
                } else {
                    root[rootY] = rootX;
                    rank[rootX] += 1;
                }
                count--;
            }
        }

        int getCount() {
            return count;
        }
    }

    public static void main(String[] args) throws Exception {
        NumberOfProvinces np = new NumberOfProvinces();

        int[][] isConnected = new int[][]{ new int[] {1,1,0}, new int[] {1,1,0}, new int[] {0, 0, 1}};
        np.findCircleNum(isConnected);
    }
}
