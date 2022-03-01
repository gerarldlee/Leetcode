package com.leetcode.narytree;

import java.util.LinkedList;
import java.util.List;

public class LevelOrderTraversal {

    /*
    Intuition:

    Add everything, all children to queue. This represents a level. Then deplete the queue, forming 1 List<Integer>
    Add this to the result.

     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) return result;

        LinkedList<Node> nodes = new LinkedList<>();
        nodes.add(root);

        while(!nodes.isEmpty()) {
            List<Integer> level = new LinkedList<>();

            int size = nodes.size();
            for (int i=0; i < size; i++) {
                Node n = nodes.pop();
                level.add(n.val);

                if (n.children != null) {
                    nodes.addAll(n.children);
                }
            }
            result.add(level);
        }

        return result;
    }
}
