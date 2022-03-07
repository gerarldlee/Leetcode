package com.leetcode.queuestack;

import java.util.LinkedList;
import java.util.Queue;

public class WallGates {

    public int GATE = 0;
    public int WALL = -1;
    public int VACANT = Integer.MAX_VALUE;

    /*
    Intuition:
    The shortest distance of a room to a gate is another room that is closest to a gate. A room is closest to a gate
    when it is just besides the gate. Gates will mark the rooms closest to it. One the room has been marked, it will not
    be re-visited. The loop continues.

    Search from the gates.
        - We loop through the 2D board, and look for the gates
        - We add them to the queue of gates

    Look for vacant rooms closest to the gates
        - For each gates that we have 4 directions to look from, up, down, left, right.
        - If the directions are valid, i.e. not a wall, not a gate, and not border or the board, and is unvisited
            - border of board is when r < 0, r > rooms.length, c < 0, or c > rooms[row].length
        - We mark that room by 1
        - We add that room to the queue
    */



    public void wallsAndGates(int[][] rooms) {
        Queue<Location> queue = new LinkedList<>();
        // time: o(row x col), space: k gates
        for (int row=0; row < rooms.length; row++) {
            for (int col=0; col < rooms[row].length; col++) {
                if (rooms[row][col] == GATE) {
                    queue.add(new Location(row, col, 0));
                }
            }
        }

        // time: o(row x col), space: k rooms which is row x col at worst, so o(row x col)
        while (!queue.isEmpty()) {
            Location p = queue.poll();
            for (Direction d : Direction.values()) {
                Location newLocation = p.goTo(d);
                if (isValid(rooms, newLocation)) {
                    // mark the room
                    rooms[newLocation.row][newLocation.col] = newLocation.distance;
                    queue.add(newLocation);
                }
            }
        }
    }

    // time: o(1), space: o(1)
    public boolean isValid(int[][] rooms, Location location) {
        return location.row >= 0 && location.row < rooms.length &&
                location.col >= 0 && location.col < rooms[0].length &&
                rooms[location.row][location.col] == VACANT;
    }

}

class Location {
    public int row, col, distance = 0;
    public Location(int row, int col, int distance) {
        this.row = row;
        this.col = col;
        this.distance = distance;
    }
    public Location goTo(Direction d) {
        return new Location(this.row + d.row, this.col + d.col, this.distance + 1);
    }
}

enum Direction {
    UP (-1, 0),
    DOWN (1, 0),
    LEFT (0, -1),
    RIGHT (0, 1);

    int row, col;
    Direction(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
