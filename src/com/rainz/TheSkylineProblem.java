package com.rainz;

import java.util.*;

public class TheSkylineProblem {
    public static void test(String args[]) {
        int[][] input1 = { {2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8} };
        Main.printList2D(getSkyline(input1));
        int[][] input2 = { {0, 2, 3}, {2, 5, 3} };
        Main.printList2D(getSkyline(input2));
    }

    public static List<List<Integer>> getSkyline(int[][] buildings) {
        List<int[]> heights = new ArrayList<>();
        for (int[] b: buildings) {
            int[] h1 = {b[0], b[2]};
            int[] h2 = {b[1], -b[2]};
            heights.add(h1);
            heights.add(h2);
        }
        // Sort by coordinate.
        // Note: if two coordinates equal, make sure the tallest show ups first. See test case 2
        Collections.sort(heights,(x, y) -> x[0]!=y[0] ? x[0]-y[0] : y[1]-x[1]);
        List<List<Integer>> ans = new ArrayList<>();
        TreeMap<Integer, Integer> hMap = new TreeMap<>();
        int currH = 0, prevH = 0;
        for (int[] height: heights) {
            int h = Math.abs(height[1]);
            Integer freq = hMap.get(h);
            if (height[1] < 0) {
                // End of a building
                if (freq == 1)
                    hMap.remove(h);
                else
                    hMap.put(h, freq-1);
            } else {
                // Start of a building
                if (freq == null)
                    freq = 0;
                hMap.put(h, freq+1);
            }
            // Get the tallest building
            currH = hMap.size() > 0 ? hMap.lastKey() : 0;
            if (currH != prevH) {
                List<Integer> rec = new ArrayList<>();
                rec.add(height[0]);
                rec.add(currH);
                ans.add(rec);
            }
            prevH = currH;
        }
        return ans;
    }
}
