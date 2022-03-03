package com.leetcode.binarysearchtree;

public class UniqueBinarySearchTrees {

    /*
    Intuition

    The idea is that unique BST is a catalan number. Therefore we apply the catalan formula

    C(0) = 1

    C(n+1) = ((2 (2 n + 1)) / (n + 2)) * C(n)

    Time: O(N)
    Space: O(1)
    */
    public int uniqueBst(int n) {
        long c = 1;
        for(int i=0; i< n; i++) {
            c = ((2 * (2 * i + 1)) / (i + 2)) * c;
        }
        return (int) c;
    }

    public static void main(String[] a) {
        UniqueBinarySearchTrees n = new UniqueBinarySearchTrees();
        System.out.println(n.uniqueBst(8));
    }
}