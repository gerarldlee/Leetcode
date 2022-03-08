package com.leetcode.arraystring;

import javax.management.DescriptorRead;
import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

    /*
    Direction are always, right, down, left, up.

    STart with row = 0, col = 0, then move right (col+1) until col = matrix[row].length
    Move down, i.e. row ++, preserving the col, until it hits matrix.length

Time: O(row x col), Space: O(row x col) - for the resulting list
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();

        int size = matrix.length * matrix[0].length;
        int row_max = matrix.length-1;
        int col_max = matrix[0].length-1;
        int row_min = 0;
        int col_min = 0;

        int r = 0, c = 0;
        Direction d = Direction.RIGHT;

        while (list.size() < size) {
            list.add(matrix[r][c]);

            switch (d) {
                case RIGHT: {
                    c++;
                    if (c > col_max) {
                        c--;
                        d = Direction.DOWN;
                        row_min++;
                        r = row_min;
                    }
                } break;
                case DOWN: {
                    r++;
                    if (r > row_max) {
                        r--;
                        d = Direction.LEFT;
                        col_max--;
                        c = col_max;
                    }
                } break;
                case LEFT: {
                    c--;
                    if (c < col_min) {
                        c++;
                        d = Direction.UP;
                        row_max--;
                        r = row_max;
                    }
                } break;
                case UP: {
                    r--;
                    if (r < row_min) {
                        r++;
                        d = Direction.RIGHT;
                        col_min++;
                        c = col_min;
                    }
                } break;
            }

        }
        return list;
    }
}

enum Direction {
    RIGHT, DOWN, LEFT, UP
}