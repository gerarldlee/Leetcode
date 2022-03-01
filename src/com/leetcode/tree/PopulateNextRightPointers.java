package com.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

public class PopulateNextRightPointers {

    /*
    Intuition:
    We use the BFS approach where we put the root nodes to its stack
    We pop that node, and if the stack is empty, we point the poped node, to null. In general if the stack
    is not empty we put the poped next to the peek of the stack.
    We do this repeatedly level by level.

    Example: root = [1,2,3,4,5,6,7]

    Time: o(n) and space is o(n)
     */
    public Node connect(Node root) {
        if (root == null) return null;

        LinkedList<Node> nodes = new LinkedList<>();
        nodes.add(root);
        int size = 1;

        while (!nodes.isEmpty()) {
            size = nodes.size();
            while (size > 0) {
                Node n = nodes.pollFirst();
                size--;

                n.next = (size > 0 ? nodes.peek() : null);

                if (n.left != null) {
                    nodes.add(n.left);
                }
                if (n.right != null)  {
                    nodes.add(n.right);
                }
            }
        }
        return root;
    }

    // Time: O(N) space: O(1)
    public Node connect1(Node root) {

        if (root == null) {
            return root;
        }

        // Start with the root node. There are no next pointers
        // that need to be set up on the first level
        Node leftmost = root;

        // Once we reach the final level, we are done
        while (leftmost.left != null) {

            // Iterate the "linked list" starting from the head
            // node and using the next pointers, establish the
            // corresponding links for the next level
            Node head = leftmost;

            while (head != null) {

                // CONNECTION 1
                head.left.next = head.right;

                // CONNECTION 2
                if (head.next != null) {
                    head.right.next = head.next.left;
                }

                // Progress along the list (nodes on the current level)
                head = head.next;
            }

            // Move onto the next level
            leftmost = leftmost.left;
        }

        return root;
    }
}
