package com.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeInorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> res = new ArrayList<>();
        if (root != null) {
//            recursive(root, res);
            iterative(root, res);
//            morris(root, res);
        }

        return res;
    }

    public void iterative(TreeNode node, List<Integer> result) {
        Stack<TreeNode> s = new Stack<>();
        TreeNode n = node;
        // sometimes the stack gets emptied, so need to check current node if non null as well.
        while (!s.isEmpty() || n != null) {
            // traverse all the left first and push them to the stack
            while (n != null) {
                s.push(n);
                n = n.left;
            }
            // we need to pop the stack because the last n.left = null
            n = s.pop();
            result.add(n.val);  // add its val
            // if that node has right node, make it the current running node
            n = n.right;
        }
    }

    // visit left, print middle, visit right
    // space: o(n), time o(n)
    public void recursive(TreeNode node, List<Integer> result) {
        if (node.left != null) {
            recursive(node.left, result);
        }
        result.add(node.val);
        if (node.right != null) {
            recursive(node.right, result);
        }
    }

    public void morris(TreeNode node, List<Integer> result) {

    }

    public static void main(String[] a) {
        BinaryTreeInorderTraversal t = new BinaryTreeInorderTraversal();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        t.inorderTraversal(root);
    }
}
