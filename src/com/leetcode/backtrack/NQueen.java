package com.leetcode.backtrack;

public class NQueen {

    public int solution(int n) {
        int[][] queens = new int[n][n];
        return backtrack_nqueen(0, n, queens);
    }

    public int backtrack_nqueen(int row, int n_queen, int[][] queens_map) {
        // n x n board
        if (row == n_queen) {
            return 1;
        }

        int count = 0;
        for (int col=0; col<n_queen; col++) {
            // check if this row and column is not under attack
            if (!check_attack(row, col, queens_map)) {
                // if its not, place the queen
                place_queen(row, col, queens_map);
                count += backtrack_nqueen(row + 1, n_queen, queens_map);
                // remove the queens, and
                remove_queen(row, col, queens_map);
            }
        }
        return count;
    }

    public int queen_or_attack(int r, int c, int[][] map) {
        return (map[r][c] == 1 ? 1 : 2);
    }

    public void populate_attacks(int r, int c, int[][] map) {
        // flag all the rows to 2, for attacking
        for (int i=0; i<map.length; i++) {
            map[i][c] = queen_or_attack(i,c,map);
        }
        // flag all the cols to 2 for attacking
        for (int i=0; i<map[0].length; i++) {
            map[r][i] = queen_or_attack(r,i,map);
        }
        // flag all teh hill diagonal as 2
        int r_temp = r, c_temp = c;
        while (r_temp < map.length && c_temp >= 0) {
            map[r_temp][c_temp] = queen_or_attack(r_temp, c_temp, map);
            r_temp++;
            c_temp--;
        }
        r_temp = r;
        c_temp = c;
        while (r_temp >= 0 && c_temp < map.length) {
            map[r_temp][c_temp] = queen_or_attack(r_temp, c_temp, map);
            r_temp--;
            c_temp++;
        }

        // flag all the dale diagonal as 2
        r_temp = r;
        c_temp = c;
        while (r_temp >= 0 && c_temp >= 0) {
            map[r_temp][c_temp] = queen_or_attack(r_temp, c_temp, map);
            r_temp--;
            c_temp--;
        }
        r_temp = r;
        c_temp = c;
        while (r_temp < map.length && c_temp < map.length) {
            map[r_temp][c_temp] = queen_or_attack(r_temp, c_temp, map);
            r_temp++;
            c_temp++;
        }
    }

    public void populate_attacks(int[][] map) {
        // resets the board of attacks
        for (int i=0; i<map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == 2)
                    map[i][j] = 0;
            }
        }

        for (int i=0; i<map.length; i++) {
            for (int j=0; j<map.length; j++) {
                if (map[i][j] == 1) {
                    populate_attacks(i, j, map);
                }
            }
        }
    }

    // resets the board
    public void remove_queen(int r, int c, int[][] map) {
        map[r][c] = 0;
        populate_attacks(map);
    }

    // places the queen in the board
    public void place_queen(int r, int c, int[][] map) {
        // finally, tag the queen location as 1
        map[r][c] = 1;
        populate_attacks(map);
        map[r][c] = 1;
    }

    // checks the board for a queen attach for r and c
    public boolean check_attack(int r, int c, int[][] queens_map) {
        // scan the board for queens
        if (queens_map[r][c] == 1 || queens_map[r][c] == 2)
            return true;
        return false;
    }

    public static void main(String[] argv) {
        NQueen n = new NQueen();
        System.out.println(n.solution(4));
        //== 2) System.out.println("Correct");
    }
}
