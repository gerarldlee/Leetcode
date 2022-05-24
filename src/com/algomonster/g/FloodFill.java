package com.algomonster.g;

import java.util.*;
import java.util.stream.Collectors;

public class FloodFill {

    public static class Point {
        int row, col;
        public Point(int x, int y) {
            this.row = x;
            this.col = y;
        }
    }

    // returns the list of neighbors of the same color
    private static List<Point> getNeighbors(List<List<Integer>> image, int r, int c) {
        int[] deltaRow = new int[] {-1, 0, 1, 0};
        int[] deltaCol = new int[] {0, 1, 0, -1};

        ArrayList<Point> neighbors = new ArrayList();

        // get the color of the r, c
        int color = image.get(r).get(c);

        for (int i=0; i<4; i++) {
            int neighborRow = r - deltaRow[i];
            int neighborCol = c - deltaCol[i];

            // check border
            if (neighborRow >= 0 && neighborRow < image.size() &&
                    neighborCol >= 0 && neighborCol < image.size()) {

                int neighborColor = image.get(neighborRow).get(neighborCol);

                if (neighborColor == color) {
                    // add to neighbors
                    neighbors.add(new Point(neighborRow, neighborCol));
                }
            }

        }

        return neighbors;
    }

    public static List<List<Integer>> floodFill(int r, int c, int replacement, List<List<Integer>> image) {
        // WRITE YOUR BRILLIANT CODE HERE

        ArrayDeque<Point> queue = new ArrayDeque();
        Point p = new Point(r, c);
        queue.offer(p);
        boolean[][] visited = new boolean[image.size()][image.size()];
        visited[r][c] = true;

        while (queue.size() > 0) {
            Point pixel = queue.pop();
            // get its neighbor
            for (Point neighbor : getNeighbors(image, pixel.row, pixel.col)) {
                if (!visited[neighbor.row][neighbor.col]) {
                    queue.offer(neighbor);
                    visited[neighbor.row][neighbor.col] = true;
                }
            }

            // replace the color of c
            image.get(pixel.row).set(pixel.col, replacement);

        }

        return image;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int r = Integer.parseInt(scanner.nextLine());
        int c = Integer.parseInt(scanner.nextLine());
        int replacement = Integer.parseInt(scanner.nextLine());
        int imageLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> image = new ArrayList<>();
        for (int i = 0; i < imageLength; i++) {
            image.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        scanner.close();
        List<List<Integer>> res = floodFill(r, c, replacement, image);
        for (List<Integer> row : res) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }
}
