package com.leetcode.binarysearchtree;

import com.leetcode.tree.TreeNode;

public class InorderSuccessor {

    /*
    Brute:
    The idea is to perform inorder traversal, left, node, right; while searching for p
    Once p is found, look for another node.

    Further analysis:
    We can discard any node to the left of p, because its a BST property.  p.val >= node.val for all left of p,
    we can search for it in the node's right.
    if p < node.val that means p resides in the left node of node, and the node is a potencial candidate for successor.
    So we search down its node.left.
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) return null;

        TreeNode successor = null;
        while (root != null) {
            if (p.val >= root.val) {
                root = root.right;
            }
            else {
                successor = root;
                root = root.left;
            }
        }

        return successor;
    }
}
