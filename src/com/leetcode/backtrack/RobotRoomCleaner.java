package com.leetcode.backtrack;


import java.util.*;

interface Robot {
    // returns true if next cell is open and robot moves into the cell.
    // returns false if next cell is obstacle and robot stays on the current cell.
    boolean move();

    // Robot will stay on the same cell after calling turnLeft/turnRight.
    // Each turn will be 90 degrees.
    void turnLeft();
    void turnRight();

    // Clean the current cell.
    void clean();
}

class Position {
    Integer row;
    Integer col;

    public Position(Integer row, Integer col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position that = (Position) o;
        return row.equals(that.row) && col.equals(that.col);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public String toString() {
        return "["+this.row+","+this.col+"]";
    }
}

public class RobotRoomCleaner {

    Robot robot;

    public void cleanRoom(Robot robot) {
        this.robot = robot;
        backtrack(0,0,0, new ArrayDeque<>());
    }

    public void backtrack(int r, int c, int d, Deque<Position> visited) {
        // add and clean the position at row and col
        visited.add(new Position(r,c));
        robot.clean();

        // explore additional directions, which can be, up, left, right, down
        // use the always right rule
        int direction = d;
        while (direction < 4) {
            int row = 0;
            int col = 0;
            // up
            if (direction == 0) {
                row = r - 1;
                col = c;
            }
            // right
            else if (direction == 1) {
                row = r;
                col = c + 1;
            }
            // down
            else if (direction == 2) {
                row = r + 1;
                col = c;
            }
            // left
            else {
                row = r;
                col = c - 1;
            }

            // is the next cell already visited?
            // is the next cell a wall?
            if (!visited.contains(new Position(row, col)) && robot.move()) {
                backtrack(row, col, direction, visited);
                revertToPreviousPositionAndDirection();
            }

            // explore other direction
            robot.turnRight();
            direction++;
            if (direction == 4) direction = 0;
        }
    }

    public void revertToPreviousPositionAndDirection() {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }

    public static void main(String[] a) {

    }
}
