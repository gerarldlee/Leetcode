package com.leetcode.tree;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromInorderPostorder {

    /*
    Intuition:

    The problem clearly states that we should use Inorder and Postorder

    We have the Postorder traversal. i.e. make a recursive function that does left, right, node/root.
    Inorder traversal is left, node/root, right.

    That means in the example [9, 15, 7, 20, 3] the very last element is the root.
    We can verify this with example of the Inorder: [9,3,15,20,7] Anything on the left of 3, is left,
    right of 3 is right subtree.
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {

        for (int i=0; i<inorder.length; i++ ) {
            inorderMap.put(inorder[i], i);
        }
        postorderRootIndex = postorder.length-1;
        return build(postorder, 0, inorder.length-1);
    }

    Map<Integer, Integer> inorderMap = new HashMap<>();
    int postorderRootIndex = 0;

    public TreeNode build(int[] postorder, int from, int to) {
        if (from > to) return null;
        // if (from == to) return new TreeNode(inorder[from]);

        // get the root, usually is the last element of a given range
        int rootVal = postorder[postorderRootIndex];
        int inorderRootIndex = inorderMap.get(rootVal);
        TreeNode root = new TreeNode(rootVal);

        postorderRootIndex--;

        // anything to the right of the root in inorder is the right
        // inorder: [15,20,7], postorder: [15,7,20]
        root.right = build(postorder, inorderRootIndex+1, to);

        // anything to the left of the root in inorder
        // [9]
        root.left = build(postorder, from, inorderRootIndex-1);

        return root;
    }

}
