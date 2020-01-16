package com.rainz;

/*
 * Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
 * The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
 * Note: You can only put the bomb at an empty cell.
 */
public class BombEnemy {
    public static void test(String args[]) {
        char[][] input1 = {{'0','E','0','0'},{'E','0','W','E'},{'0','E','0','0'}};
        System.out.println(maxKilledEnemies(input1));
    }

    /*
     * First, scan row by rwo to find  horizontal kills for each empty cell
     * Then, scan col by col to find vertical kills and add that to horizontal kills to get total kills for each cell.
     * Finally find the max kill.
     */
    public static int maxKilledEnemies(char[][] grid) {
        int R = grid.length;
        if (R == 0)
            return 0;
        int C = grid[0].length;
        int[][] kills = new int[R][C];
        int ans = 0;
        for (int r = 0; r < R; ++r) {
            int enemies = 0;
            int start = 0;
            for (int c = 0; c <= C; ++c) {
                char cell = c < C ? grid[r][c] : 'W';
                switch (cell) {
                    case '0':
                        break;
                    case 'E':
                        ++enemies;
                        break;
                    case 'W':
                        for (int i = start; i < c; ++i) {
                            if (grid[r][i] == '0') {
                                kills[r][i] += enemies;
                                if (ans < kills[r][i])
                                    ans = kills[r][i];
                            }
                        }
                        enemies = 0;
                        start = c + 1;
                        break;
                }
            }
        }
        for (int c = 0; c < C; ++c) {
            int enemies = 0;
            int start = 0;
            for (int r = 0; r <= R; ++r) {
                char cell = r < R ? grid[r][c] : 'W';
                switch (cell) {
                    case '0':
                        break;
                    case 'E':
                        ++enemies;
                        break;
                    case 'W':
                        for (int i = start; i < r; ++i) {
                            if (grid[i][c] == '0') {
                                kills[i][c] += enemies;
                                if (ans < kills[i][c])
                                    ans = kills[i][c];
                            }
                        }
                        enemies = 0;
                        start = r + 1;
                        break;
                }
            }
        }
        return ans;
    }

}
