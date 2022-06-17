package com.patterns.structural;

/**
 * Motivation
 * Use sharing to support large numbers of fine-grained objects efficiently.
 * Lets you fit more objects into the available amount of RAM/memory by sharing common parts of state between multiple objects instead of keeping all of the data private
 * The Motif GUI strategy of replacing heavy-weight widgets with light-weight gadgets
 *
 * References:
 *  https://sourcemaking.com/design_patterns/flyweight
 *  https://refactoring.guru/design-patterns/flyweight
 */
class Gazillion {
    private static int num = 0;
    private int row, col;

    public Gazillion(int maxPerRow) {
        row = num / maxPerRow;
        col = num % maxPerRow;
        num++;
    }

    void report() {
        System.out.print(" " + row + col);
    }
}

public class Flyweight {
    public static final int ROWS = 6, COLS = 10;

    public static void main( String[] args ) {
        Gazillion[][] matrix = new Gazillion[ROWS][COLS];
        for (int i=0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                matrix[i][j] = new Gazillion(COLS);
            }
        }
        for (int i=0; i < ROWS; i++) {
            for (int j=0; j < COLS; j++) {
                matrix[i][j].report();
            }
            System.out.println();
        }
    }
}
