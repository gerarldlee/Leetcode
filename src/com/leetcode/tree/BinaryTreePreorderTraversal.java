package com.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class BinaryTreePreorderTraversal {

    public List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> preorder = new ArrayList<>();

        // visit the root
        // check for the left subtree
        // finally check for the right subtree
        if (root != null) {
            recursive(root, preorder);
            iterative(root, preorder);
            morris_preorder(root, preorder);
        }

        return preorder;
    }

    // visit middle, left, right
    // recursion approach, space: O(N) - the recursive stack, time: O(N) - iterate nodes 1 time
    public void recursive(TreeNode tree, List<Integer> preorder) {
        preorder.add(tree.val);
        // visit the left
        if (tree.left != null) {
            recursive(tree.left, preorder);
        }
        // visit the right
        if (tree.right != null) {
            recursive(tree.right, preorder);
        }
    }

    // iterative approach, space: o(N) - stack of N nodes, time: o(N) - iterate over nodes 1 time
    public void iterative(TreeNode tree, List<Integer> preorder) {
        Stack<TreeNode> treeNodeStack = new Stack<>();
        treeNodeStack.push(tree);

        while (!treeNodeStack.isEmpty()) {
            tree = treeNodeStack.pop();
            preorder.add(tree.val);
            // right first, before left, so that it becomes left first when it pops from the stack
            if (tree.right != null) {
                treeNodeStack.push(tree.right);
            }
            if (tree.left != null) {
                treeNodeStack.push(tree.left);
            }
        }
    }

    // morris, time: o(2n) or N - we visit each node 2x, space: o(n)
    public void morris_preorder(TreeNode node, List<Integer> result) {
        TreeNode curr = node;
        while (curr != null) {

            // if left child = null, print the current node and move to the right child
            if (curr.left == null) {
                result.add(curr.val);
                curr = curr.right;
            }
            else {
                // find the previous node / predecessor
                TreeNode previousNode = curr.left;
                while ((previousNode.right != null) && (previousNode.right != curr)) {
                    previousNode = previousNode.right;
                }

                // if the previous node's right does not point to anything
                // point it to the current node, while point the current to its left
                if (previousNode.right == null) {
                    result.add(curr.val);
                    previousNode.right = curr;
                    curr = curr.left;
                }

                // otherwise the right child of predecessor is already pointing to this node
                else {
                    previousNode.right = null;
                    curr = curr.right;
                }
            }
        }

    }

    public static void main(String[] a) {
        BinaryTreePreorderTraversal t = new BinaryTreePreorderTraversal();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        t.preorderTraversal(root);
    }
}
