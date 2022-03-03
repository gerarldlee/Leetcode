package com.leetcode.narytree;

import com.leetcode.tree.TreeNode;

import java.util.LinkedList;

public class EncodeNaryTreeBinaryTree {

    /*
    Intuition:
    The left child of each node in a binary tree, is the next sibling it has on the Nary tree
    The right child of each node in a binary tree, is the first child of that node in the Nary tree
     */
    public TreeNode binaryTree(Node naryTree) {
        if (naryTree == null) return null;

        TreeNode binaryTree = new TreeNode(naryTree.val);
        // if it has children, then put it to the right of the binary tree
        if (!naryTree.children.isEmpty()) {
            binaryTree.right = binaryTree(naryTree.children.get(0));
        }

        // the siblings will be the left child of the binaryTree.right
        TreeNode child = binaryTree.right;
        for (int i=1; i < naryTree.children.size(); i++) {
            child.left = binaryTree(naryTree.children.get(i));
            child = child.left;
        }

        return binaryTree;
    }

    /*
    Intiution:
    The right child is the first child of that node
    The left child is the next sibling of that node
     */
    public Node naryTree(TreeNode binaryTree) {
        if (binaryTree == null) return null;

        Node naryTree = new Node(binaryTree.val, new LinkedList<>());
        TreeNode bTree = binaryTree.right;
        while (bTree != null) {
            naryTree.children.add(naryTree(bTree));
            bTree = bTree.left;
        }

        return naryTree;
    }
}
