package com.leetcode.tree;

/**
 * A univalue subtree is a subtree whos values are the same from the node, to the childs.
 * 1. If the node is a child i.e. does not have children, its a univalue.
 * 2. If the node's value is the same as the childrens value, and their childrens children.
 *
 */
public class CountUnivalueSubtrees {

    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) return 0;
        univalRecursive(root);
        return univalue;
    }

    // intuition:
    // we use dfs search to navigate post order all of the nodes
    // for each nodes (with child) we compare its values if they are the same.
    // if they are, then we would increament a counter by 1 to count our univalue
    // a node that does not have a branch, that means the node is also univalue in itself.
    // so it would be counted as well, as shown in the example
    //
    // time: O(N) - Will traverse every node
    // space: O(K) - because of the height / depth traversal

    int univalue = 0;
    int MAX_VALUE = 10000;
    public int univalRecursive(TreeNode node) {
        // case 1: child node = univalue
        if (node.left == null && node.right == null) {
            univalue++;
            System.out.println("+1 child univalue");
            return node.val;
        }

        int val = node.val;
        int leftVal = 0;
        int rightVal = 0;
        int divisor = 1;
        // case 2: it has branches and all its leaf nodes are the same
        if (node.left != null) {
            leftVal = univalRecursive(node.left);
            divisor++;
        }
        if (node.right != null) {
            rightVal = univalRecursive(node.right);
            divisor++;
        }
        int sum = val + leftVal + rightVal;
        if ((sum / divisor == val) && (sum % divisor == 0)) {
            univalue++;
        }
        else {
            return MAX_VALUE;
        }
        System.out.printf("%d = %d + %d + %d / %d [%d]\n", sum, val, leftVal, rightVal, divisor, univalue);
        return node.val;
    }

}
/**
 * [5,1,5,5,5,null,5]
 * [5,1,3,1,1,1]
 * [1,1,1,5,5,null,5]
 * [0,95,95,null,59,59,null,-56,-82,-82,-56]
 */
