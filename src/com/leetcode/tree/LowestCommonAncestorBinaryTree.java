package com.leetcode.tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class LowestCommonAncestorBinaryTree {

    /*
    Intuition

    - Navigate the TreeNode until p or q is reached, while storing everything in a hashmap
    - Create a new hashset that contains the ancestors of P
    - Check for Q's ancestors from P ancestors, return the latest common
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        HashMap<TreeNode, TreeNode> parents = new HashMap<>();
        LinkedList<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        parents.put(root, null); // root has no parent

        // if it does not contain either of them, search continually
        while (!parents.containsKey(p) || !parents.containsKey(q)) {
            TreeNode n = nodes.pop();
            if (n.left != null) {
                nodes.add(n.left);
                parents.put(n.left, n); // will create a node.child -> node parent link
            }
            if (n.right != null) {
                nodes.add(n.right);
                parents.put(n.right, n);
            }
        }

        HashSet<TreeNode> ancestor = new HashSet<>();
        while (p != null) {
            ancestor.add(p);
            p = parents.get(p); // will create a p hierarchy
        }

        while (!ancestor.contains(q)) {
            q = parents.get(q);
        }

        return q;
    }


}
