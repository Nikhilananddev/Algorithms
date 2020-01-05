package com.rainz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueensThatCanAttacktheKing {
    public static void test(String args[]) {
        int[][] queens1 = {{0,1},{1,0},{4,0},{0,4},{3,3},{2,4}};
        int[] king1 = {0,0};
        Main.printList2D(queensAttacktheKing(queens1, king1));
        int[][] queens2 = {{0,0},{1,1},{2,2},{3,4},{3,5},{4,4},{4,5}};
        int[] king2 = {3,3};
        Main.printList2D(queensAttacktheKing(queens2, king2));
        int[][] queens3 = {{5,6},{7,7},{2,1},{0,7},{1,6},{5,1},{3,7},{0,3},{4,0},{1,2},{6,3},{5,0},{0,4},{2,2},{1,1},{6,4},{5,4},{0,0},{2,6},{4,5},{5,2},{1,4},{7,5},{2,3},{0,5},{4,2},{1,0},{2,7},{0,1},{4,6},{6,1},{0,6},{4,3},{1,7}};
        int[] king3 = {3,4};
        Main.printList2D(queensAttacktheKing(queens3, king3));
    }

    /* We only need to compare a non-zero axis to determine the closer queen */
    private static int distSingleAxis(int[] queen, int[] king) {
        int dr = queen[0] - king[0];
        if (dr != 0)
            return Math.abs(dr);
        return Math.abs(queen[1] - king[1]);
    }

    public static List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        final int[][] dirs8 = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}, {-1, -1}, {-1, 1}, {1, 1}, {1, -1}};
        int[] closest = new int[8]; // closest queen in each of the 8 dirs
        Arrays.fill(closest, -1);

        for (int qIdx = 0; qIdx < queens.length; ++qIdx) {
            int[] q = queens[qIdx];
            int dr = q[0] - king[0];
            int dc = q[1] - king[1];
            if (dr != 0 && dc != 0 && Math.abs(dr) != Math.abs(dc))
                continue; // not one of the 8 directions
            int dist = distSingleAxis(q, king);
            for (int dIdx = 0; dIdx < dirs8.length; ++dIdx) {
                int[] dir = dirs8[dIdx];
                if (dir[0] == (int)Math.signum(dr) && dir[1] == (int)Math.signum(dc)) {
                    // Found direction, now compare distances from king
                    int closeIdx = closest[dIdx];
                    if (closeIdx < 0 || dist < distSingleAxis(queens[closeIdx], king))
                        closest[dIdx] = qIdx;
                    break;
                }
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int idx: closest) {
            if (idx < 0)
                continue;
            int[] q = queens[idx];
            List<Integer> queen = new ArrayList<>();
            queen.add(q[0]);
            queen.add(q[1]);
            ans.add(queen);
        }
        return ans;
    }
}
