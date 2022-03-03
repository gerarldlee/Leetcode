package com.leetcode.recursion;

import com.leetcode.tree.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class UniqueBinarySearchTrees2 {

    /*
    Intuition:

    Given 1..n, every number is a unique combination of the left of that number, and the right combination of that number
    Example: [1,2,3,4,5] lets say root = 3. left subtree of 3, will be combination of [1,2] and right subtree will be [4,5]
    The left subtree [1,2] is also a unique combination of its left and right subtree.

    */
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new LinkedList<>();

        return generateTrees(1, n);
    }

    /*
    [1,2,3]

    root = 1
    left = null, start = 1, end = 0
    right = [2,3], start = 2, end = 3
        i = 2
        left = null, start = 2, end = 1
        right = , start = 3, end = 3
            i = 3
            left = null, start = 3, end = 2
            right = null, start = 4, end = 2
        i = 3
        left: null, start = 3, end = 2
        right: null, start = 4, end = 3
      
    [1,null,2,null,3],
    [1,null,3,2],
    [2,1,3],
    [3,1,null,null,2],
    [3,2,null,1]

    */
    public LinkedList<TreeNode> generateTrees(int start, int end) {
        LinkedList<TreeNode> trees = new LinkedList<>();
        if (start > end) {
            trees.add(null);    // add null so that leaf nodes can be null children
            return trees;
        }

        for (int i=start; i<= end; i++) {

            LinkedList<TreeNode> left = generateTrees(start, i - 1);
            LinkedList<TreeNode> right = generateTrees(i + 1, end);

            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode t = new TreeNode(i);
                    t.left = l;
                    t.right = r;
                    trees.add(t);
                }
            }
        }

        return trees;
    }

    public static void main(String[] a) {
        UniqueBinarySearchTrees2 n = new UniqueBinarySearchTrees2();
        System.out.println((n.generateTrees(2)));
    }
}
