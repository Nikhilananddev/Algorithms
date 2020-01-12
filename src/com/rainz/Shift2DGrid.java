package com.rainz;

import java.util.ArrayList;
import java.util.List;

public class Shift2DGrid {
    public static void test(String args[]) {
        int[][] grid1 = {{1,2,3},{4,5,6},{7,8,9}};
        Main.printList2D(shiftGrid(grid1, 1));
        int[][] grid2 = {{3,8,1,9},{19,7,2,5},{4,6,11,10},{12,0,21,13}};
        Main.printList2D(shiftGrid(grid2, 4));
    }


    public static List<List<Integer>> shiftGrid(int[][] grid, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        int R = grid.length;
        if (R == 0)
            return ans;
        int C = grid[0].length;
        int RC = R*C;
        for (int r = 0; r < R; ++r) {
            ans.add(new ArrayList<>());
        }

        k %= RC;
        for (int i = 0; i < RC; ++i) {
            int r = i / C;
            int gridIdx = (i + RC - k) % RC;
            int gridR = gridIdx / C, gridC = gridIdx % C;
            ans.get(r).add(grid[gridR][gridC]);
        }
        return ans;
    }
}
