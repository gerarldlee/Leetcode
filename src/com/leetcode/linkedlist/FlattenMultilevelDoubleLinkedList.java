package com.leetcode.linkedlist;

import java.util.Stack;

public class FlattenMultilevelDoubleLinkedList {

    // Intuition
    // We would iterate over the linked list, and look for a non-null child.
    // If its found, we temporarily store the 'next' of the current node with child to a variable,
    // we put this variable to a Stack for later retrieval
    // and access its child node. We put the 'child' to null, and assign the 'next' to its child address
    // we also assign the 'prev' of the child to this node. Then we continue to loop over the list again
    // Until we encounter the node who have a child. Repeat!
    // Once we encountered the terminal of the nodes, we pop the Stack and attach them to it.

    // space: o(n), worse case when all nodes are child nodes
    // time: o(n) - just the number of Nodes
    public Node flatten(Node head) {
        if (head == null) return null;

        Stack<Node> nodes = new Stack<>();
        nodes.push(head);

        Node curr = null;
        while(!nodes.isEmpty()) {
            Node n = nodes.pop();
            if (curr != null && n != null) {
                curr.next = n;
                n.prev = curr;
            }
            while (n != null) {
                if (n.child != null) {
                    nodes.push(n.next);
                    n.next = n.child;
                    n.child.prev = n;
                    n.child = null;
                }
                curr = n;
                n = n.next;
            }
        }
        return head;
    }
}
