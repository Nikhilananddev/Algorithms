package com.rainz;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class FindRightInterval {
    public static void test(String args[]) {
        int[][] input1 = { {1,2} };
        Main.printArray(findRightInterval(input1));
        int[][] input2 = { {3,4}, {2,3}, {1,2} };
        Main.printArray(findRightInterval(input2));
        int[][] input3 = { {1,4}, {2,3}, {3,4} };
        Main.printArray(findRightInterval(input3));
    }

    public static int[] findRightInterval(int[][] intervals) {
        int N = intervals.length;
        int[] ans = new int[N];
        // Create a priority queue sorted by left edge of interval
        Queue<int[]> pqMinLeft = new PriorityQueue<>((x, y) -> Integer.compare(x[0], y[0]));
        // rightSorted will be sorted by right edge of interval
        int[][] rightSorted = new int[N][3];
        // Both pqMinLeft and rightSorted also stores original index of the interval
        for (int i = 0; i < N; ++i) {
            rightSorted[i][0] = intervals[i][0];
            rightSorted[i][1] = intervals[i][1];
            rightSorted[i][2] = i;
            int[] interval = {intervals[i][0], intervals[i][1], i};
            pqMinLeft.add(interval);
        }
        Arrays.sort( rightSorted, (x, y) -> Integer.compare(x[1], y[1]) );
        for (int[] interval: rightSorted) {
            int rightIndex = -1;
            while (!pqMinLeft.isEmpty()) {
                int[] minLeft = pqMinLeft.remove();
                if (minLeft[0] >= interval[1]) {
                    rightIndex = minLeft[2];
                    pqMinLeft.add(minLeft); // it might be "right" of more intervals
                    break;
                }
                // Smallest left < remaining smallest right,
                // so drop it since it won't be "right" for any remaining intervals
            }
            ans[interval[2]] = rightIndex;
        }
        return ans;
    }
}
