package com.leetcode.backtrack;

import java.util.HashSet;
import java.util.Set;

public class NQueenOptimal {
    public int solution(int n) {
        Set<Integer> diagonals = new HashSet<>();
        Set<Integer> reverseDiagonals = new HashSet<>();
        Set<Integer> col = new HashSet<>();
        return backtrack_nqueen(0, n, col, diagonals, reverseDiagonals);
    }

    public int backtrack_nqueen(int row, int n, Set<Integer> cols, Set<Integer> diag, Set<Integer> revDiag) {

        if (row == n) return 1;

        int count = 0;
        for (int col = 0; col < n; col++) {
            if (!checkBoard(row, col, cols, diag, revDiag)) {
                placeQueen(row, col, cols, diag, revDiag);
                count += backtrack_nqueen(row + 1, n, cols, diag, revDiag);
                removeQueen(row, col, cols, diag, revDiag);
            }
        }
        return count;
    }

    public void removeQueen(int row, int col, Set<Integer> cols, Set<Integer> diag, Set<Integer> revDiag) {
        cols.remove(col);
        diag.remove(row-col);
        revDiag.remove(row+col);
    }

    public boolean checkBoard(int r, int c, Set<Integer> cols, Set<Integer> diag, Set<Integer> revDiag) {
        return cols.contains(c) || diag.contains(r-c) || revDiag.contains(r+c);
    }

    public void placeQueen(int r, int c, Set<Integer> cols, Set<Integer> diag, Set<Integer> revDiag) {
        cols.add(c);
        diag.add(r-c);
        revDiag.add(r+c);
    }


    public static void main(String[] argv) {
        NQueenOptimal n = new NQueenOptimal();
        System.out.println(n.solution(4));
        //== 2) System.out.println("Correct");
    }
}
