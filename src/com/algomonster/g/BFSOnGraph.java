package com.algomonster.g;

import com.algomonster.bfs.BinaryTreeMinDepth;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BFSOnGraph {
    public static class Node<T> {
        public T val;
        public BinaryTreeMinDepth.Node<T> left;
        public BinaryTreeMinDepth.Node<T> right;

        public Node(T val) {
            this(val, null, null);
        }

        public Node(T val, BinaryTreeMinDepth.Node<T> left, BinaryTreeMinDepth.Node<T> right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * The difference between BFS on a tree and on a graph, is that:
     * 0. Is essentially the same, except for the fact that we use a visited variable to keep track of the nodes we visited.
     *
     *      A visited node means theres a cycle in the graph. A tree does not have cycle.
     *
     * 1. Tree has children, but Graph does not have parents.  We get the neighbors instead
     *
     * 2. Getting the neighbors from the adjacency list / matrix, would be returning a list of neighbors for the node.
     *
     *      We have to generate the adjacency list or matrix first.
     *
     * @param root
     */
    public void bfs(Node root) {
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.add(root);
        Set<Node> visited = new HashSet<>();        // to keep track of visited nodes
        while (queue.size() > 0) {
            Node node = queue.pop();
            for (Node neighbor : getNeighbors(node)) {      // gets the adjacent neighbor of the node
                if (visited.contains(neighbor)) {   // prevents cycle
                    continue;
                }
                queue.add(neighbor);                // add the node to the queue
                visited.add(neighbor);              // keeps track of visited nodes
            }
        }
    }

    // Gets the adjacency list
    public List<Node> getNeighbors(Node node) {
        return null;
    }

    /**
     * If we want to track the level of the Graph, we can use the queue size as well.
     *
     * This technique is also from BFS level order tracking of a TREE!
     *
     * @param root
     */
    public void bfsLevelOrder(Node root) {
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.add(root);
        Set<Node> visited = new HashSet<>();

        int level = 0;                                  // define the level of the graph

        while (queue.size() > 0) {                      // size defines the current level
            Node node = queue.pop();                    // removes the first node from the queue and decrease its size
            for (Node neighbor : getNeighbors(node)) {  // gets the adjacent neighbor or level of the node
                if (visited.contains(neighbor)) {
                    continue;
                }
                queue.add(neighbor);                    // add the node to the queue's size or level
                visited.add(neighbor);
            }
            // increment the level
            level++;
        }
    }

}
