package com.algomonster.g;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DFSOnGraph {
    public static class Node<T> {
        public T val;
        public List<Node<T>> neighbors;

        public Node(T val) {
            this.val = val;
            this.neighbors = new ArrayList<>();
        }
    }

    /**
     * Is the same as DFS on a Tree, but with visited variable to keep track of all visited nodes to prevent or determine cycle
     *
     * @param node
     * @param visited
     */
    public void dfs(Node<Integer> node, Set<Node> visited) {
        if (node == null) return;

        for (Node<Integer> neighbor : node.neighbors) {

            if (visited.contains(node)) {
                continue;
            }

            visited.add(neighbor);
            dfs(neighbor, visited);
        }
    }


}
