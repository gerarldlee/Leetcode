package com.algomonster.g;

import java.util.*;
import java.util.stream.Collectors;

public class NumberOfIslands {
    public static class Point {
        int row, col;
        public Point(int x, int y) {
            this.row = x;
            this.col = y;
        }
    }
    // returns the list of neighbors of the same color
    private static List<Point> getNeighbors(List<List<Integer>> grid, int r, int c) {
        int[] deltaRow = new int[] {-1, 0, 1, 0};
        int[] deltaCol = new int[] {0, 1, 0, -1};
        ArrayList<Point> neighbors = new ArrayList();
        for (int i=0; i<4; i++) {
            int neighborRow = r - deltaRow[i];
            int neighborCol = c - deltaCol[i];
            if (neighborRow >= 0 && neighborRow < grid.size() &&
                    neighborCol >= 0 && neighborCol < grid.get(0).size()) {
                int neighborColor = grid.get(neighborRow).get(neighborCol);
                if (neighborColor == 1) {
                    neighbors.add(new Point(neighborRow, neighborCol));
                }
            }
        }
        return neighbors;
    }

    private static void bfs(List<List<Integer>> grid, int r, int c) {
        ArrayDeque<Point> queue = new ArrayDeque();
        Point p = new Point(r, c);
        queue.offer(p);

        // the key here is to assign 0 for the islands that have been visited
        // and the islands that are "connected"
        while (queue.size() > 0) {
            Point pixel = queue.pop();
            for (Point neighbor : getNeighbors(grid, pixel.row, pixel.col)) {
                if (grid.get(neighbor.row).get(neighbor.col) == 1) {
                    queue.offer(neighbor);
                    grid.get(neighbor.row).set(neighbor.col, 0);
                }
            }
        }
    }

    public static int countNumberOfIslands(List<List<Integer>> grid) {
        // WRITE YOUR BRILLIANT CODE HERE

        int count = 0;
        for (int row=0; row<grid.size(); row++) {
            for(int col=0; col<grid.get(0).size(); col++) {
                // only increase count of island when it is 1
                if (grid.get(row).get(col) != 0) {
                    bfs(grid, row, col);
                    count++;
                }
            }
        }

        return count;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int gridLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> grid = new ArrayList<>();
        for (int i = 0; i < gridLength; i++) {
            grid.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        scanner.close();
        int res = countNumberOfIslands(grid);
        System.out.println(res);
    }
}
