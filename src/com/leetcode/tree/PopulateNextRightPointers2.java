package com.leetcode.tree;

public class PopulateNextRightPointers2 {

    /*
    Intuition:

    We begin by getting the leftmost of a level, and incrementing it level by level through the its child's left
    leftmost is either the leftmost, the right child, when theres no left child.

    If theres a right sibling, we point the left;s next to it. We point the right child to the next left child
    Determining the next node is dynamic, so we need to define a function that returns which nodes are present.

     */
    public Node connect(Node root) {
        if (root == null) return null;

        Node leftmost = root;
        while (leftmost !=null) {

            Node prev = null;
            Node curr = leftmost;
            leftmost = null;
            while (curr != null) {

                if (curr.left != null) {
                    if (prev == null) {
                        leftmost = curr.left;
                    }
                    else {
                        prev.next = curr.left;
                    }
                    prev = curr.left;
                }

                if (curr.right != null) {
                    if (prev == null) {
                        leftmost = curr.right;
                    }
                    else {
                        prev.next = curr.right;
                    }
                    prev = curr.right;
                }

                curr = curr.next;
            }

        }
        return root;
    }

    public Node getNextLevel(Node leftmost) {
        return leftmost.left != null ? leftmost.left : leftmost.right;
    }


}
