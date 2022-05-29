package com.algomonster.g;

import java.util.*;

public class BFSForMatrix {

    class Coordinate {
        int row, col;
        public Coordinate(int x, int y) {
            this.row = x;
            this.col = y;
        }
    }

    int[][] matrix = new int[3][3];     // 2D 3x3 matrix

    public int numRows = matrix.length;
    public int numCols = matrix[0].length;

    public List<Coordinate> getNeighbors(Coordinate coord) {
        int row = coord.row;
        int col = coord.col;
        int[] deltaRow = {-1, 0, 1, 0};     // clockwise, impt for getting neighbors of the coord from graph matrix
        int[] deltaCol = {0, 1, 0, -1};
        List<Coordinate> res = new ArrayList<>();
        for (int i = 0; i < deltaRow.length; i++) {
            int neighborRow = row + deltaRow[i];
            int neighborCol = col + deltaCol[i];
            if (0 <= neighborRow && neighborRow < numRows &&
                    0 <= neighborCol && neighborCol < numCols) {
                res.add(new Coordinate(neighborRow, neighborCol));
            }
        }
        return res; // adjacent list from 2D graph matrix
    }

    public void bfs(Coordinate startingNode) {
        Deque<Coordinate> queue = new ArrayDeque<>();
        queue.add(startingNode);
        Set<Coordinate> visited = new HashSet<>();
        visited.add(startingNode);

        while (queue.size() > 0) {
            Coordinate node = queue.pop();
            for (Coordinate neighbor : getNeighbors(node)) {        // very convenient!
                if (visited.contains(neighbor)) continue;
                // Do stuff with the node if required
                // ...
                queue.add(neighbor);
                visited.add(neighbor);
            }
        }
    }
}
