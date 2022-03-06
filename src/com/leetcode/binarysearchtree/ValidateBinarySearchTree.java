package com.leetcode.binarysearchtree;

import com.leetcode.tree.TreeNode;

public class ValidateBinarySearchTree {

    /*
    A BST is a special case of binary tree where all nodes in the left, has values less than all nodes of
    its right. The left subtree contain nodes less than the right subtree and the root node. The right
    subtree contain nodes higher than the left subtree and the root nodes.
     */
    public boolean isValidBST(TreeNode root) {
        // return validate(root, null, null);
        previousValue = null;
        return inorder(root);
    }

    /*
    The idea is that the root.val should be between low and high. If this is not the case, return false

    Otherwise, continue validating the right of the BST, which should now be between high and the
    root's val which is the new low.  In the same way, the root.left of the BST, should be between the
    low and the root.val, which is the new high.
     */
    public boolean validate(TreeNode root, Integer high, Integer low) {
        if (root == null) return true;
        if ((low != null && root.val <= low) || (high != null && root.val >= high))
            return false;
        return validate(root.right, high, root.val) && validate(root.left, root.val, low);
    }

    /*
    This is easier to conceptualize, however the use of a global variable is undesirable.

    The idea is to use the inorder traversal: left, node, right.  lowest value to the highest value.
    The previousValue is set to the leftmost possible value, which is the lowest.
    And then compared from the node.val, which should be incrementally higher than the prevValue
    And then compared to the node.right subtree values.
     */
    private Integer previousValue;

    private boolean inorder(TreeNode node) {
        if (node == null) return true;

        if (!inorder(node.left)) return false;
        if (previousValue != null && node.val <= previousValue) return false;
        previousValue = node.val;
        return inorder(node.right);

    }
}
