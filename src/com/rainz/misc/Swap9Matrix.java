package com.rainz.misc;

/*
 * (From Addepar)
 * Given a 3*3 matrix filling by unique numbers from 1 to 9.
 * You can only switch 9 with 9's up/down/left/right neighbours.
 * Each switch is counted as a step.
 * Question: what's the minimum number of steps to reach the state that the matrix is like
 * 1 2 3
 * 4 5 6
 * 7 8 9
 */

import java.util.*;

public class Swap9Matrix {
    public static void test(String args[]) {
        /*int[][] input1 = {
                {5, 2, 3},
                {9, 8, 4},
                {1, 6, 7},
        };
        System.out.println(minSwapSteps(input1));*/
        randomSwapTest();
    }

    private static void randomSwapTest() {
        int[][] mtx = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
        };
        int r9 = 2, c9 = 2;
        final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int swapTimes = 250;
        Random rand = new Random(System.currentTimeMillis());
        while (swapTimes > 0) {
            int[] d = dirs[rand.nextInt(4)];
            int r = r9 + d[0], c = c9 + d[1];
            if (r < 0 || r >= 3 || c < 0 || c >= 3)
                continue;
            mtx[r9][c9] = mtx[r][c];
            mtx[r][c] = 9;
            r9 = r;
            c9 = c;
            --swapTimes;
        }
        System.out.println(minSwapSteps(mtx));
    }

    private static int idx2To1(int r, int c) {
        return r*3+c;
    }

    private static int[] idx1To2(int idx) {
        int[] res = {idx/3, idx%3};
        return res;
    }

    private static class BFSNode {
        String state;
        BFSNode parent;
        BFSNode(String st, BFSNode p) { state = st; parent = p; };
    }
    public static int minSwapSteps(int[][] matrix) {
        final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        Set<String> visited = new HashSet<>();
        String target = "123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                sb.append((char)(matrix[i][j] + '0'));
            }
        }
        List<BFSNode> currLevel = new ArrayList<>();
        currLevel.add(new BFSNode(sb.toString(), null));
        int level = 0;
        while (!currLevel.isEmpty()) {
            List<BFSNode> nextLevel = new ArrayList<>();
            for (BFSNode n: currLevel) {
                String s = n.state;
                if (s.equals(target)) {
                    for (BFSNode node = n; node != null; node = node.parent) {
                        System.out.println(node.state);
                    }
                    return level;
                }
                if (visited.contains(s))
                    continue;
                visited.add(s);
                int idx9 = s.indexOf('9');
                int[] coord9 = idx1To2(idx9);
                int r9 = coord9[0], c9 = coord9[1];
                for (int[] d : dirs) {
                    int r = r9 + d[0], c = c9 + d[1];
                    if (r < 0 || r >= 3 || c < 0 || c >= 3)
                        continue;
                    int idxSwap = idx2To1(r, c);
                    char ch = s.charAt(idxSwap);
                    StringBuilder csb = new StringBuilder(s);
                    csb.setCharAt(idx9, ch);
                    csb.setCharAt(idxSwap, '9');
                    nextLevel.add(new BFSNode(csb.toString(), n));
                }
            }
            currLevel = nextLevel;
            ++level;
        }
        return -1;
    }
}
