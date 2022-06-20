package com.algomonster.g;

import java.util.*;
import java.util.stream.Collectors;

// TODO incomplete / how to save the state?
public class SlidingPuzzle {

    private static boolean evaluate(List<List<Integer>> board) {
        int arrangement = 0;
        for (int row=0; row<board.size(); row++) {
            for(int col=0; col<board.get(row).size(); col++) {
                if (board.get(row).get(col) != arrangement) return false;
                arrangement++;
            }
        }
        return true;
    }

    public static class Point {
        int row, col;
        public Point(int row, int col) {this.row = row; this.col = col;}
    }

    private static Point getZero(List<List<Integer>> board) {
        // determine where the 0 is
        Point zero = new Point(0,0);
        for (int row=0; row<board.size(); row++) {
            for(int col=0; col<board.get(row).size(); col++) {
                if (board.get(row).get(col) == 0) {
                    zero = new Point(row, col);
                }
            }
        }
        return zero;
    }

    private static List<Point> getNeighbors(List<List<Integer>> board) {

        List<Point> results = new ArrayList<>();
        // there can only be maximum of 4 neighbors
//        int
        return results;
    }

    public static int numSteps(List<List<Integer>> initPos) {
        // WRITE YOUR BRILLIANT CODE HERE

        ArrayDeque<Point> queue = new ArrayDeque<>();

        Point zero = getZero(initPos);
        queue.offer(zero);

        int moves = 0;
        while (queue.size() > 0) {
            int size = queue.size();
            while (size > 0) {
                Point current = queue.pop();
//                for (Point child : getNeighbors()) {
//
//                }
                size--;
            }
            moves++;
        }

        return moves;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int initPosLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> initPos = new ArrayList<>();
        for (int i = 0; i < initPosLength; i++) {
            initPos.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        scanner.close();
        int res = numSteps(initPos);
        System.out.println(res);
    }
}
