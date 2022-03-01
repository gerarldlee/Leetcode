package com.leetcode.tree;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromPreorderInorder {

    /*
    Intuition:

    The left most preorder is the root node, and anything to the left of this node in the inorder array
    is the left subtree, and anything to the right of this array in the inorder is the right subtree.

    We begin by getting the root node, populating the left, and then the right subtree
    Populate the left by creating a helper method for the remaining left array from the inorder,
    root index decrementing by one. If the from > to, returning null means there are no children nodes
    Same goes for the right subtree.

    Example: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
    Output: [3,9,20,null,null,15,7]
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inorderMap = new HashMap<>();

        int inc = 0;
        for (int i : inorder) {
            inorderMap.put(i, inc++);
        }

        return build(preorder, 0, inorder.length-1, inorderMap);
    }

    int preorderRootIndex = 0;

    public TreeNode build(int[] preorder, int from, int to, Map<Integer, Integer> inorderMap) {
        if (from > to) return null;

        int rootValue = preorder[preorderRootIndex++];
        int inorderIndex = inorderMap.get(rootValue);
        TreeNode node = new TreeNode(rootValue);

        node.left = build(preorder, from, inorderIndex-1, inorderMap);
        node.right = build(preorder, inorderIndex+1, to, inorderMap);
        return node;
    }
}
