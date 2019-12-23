package com.rainz;

import java.util.List;

/*
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
 */

public class Triangle {
    public static void test(String args[]) {
        int[][] input = {
                         {2},
                        {3,4},
                       {6,5,7},
                      {4,1,8,3}
                    };
        System.out.println(minimumTotal(Main.buildList2D(input)));
    }

    public static int minimumTotal(List<List<Integer>> triangle) {
        int N = triangle.size();
        if (N <= 0)
            return 0;
        int[] dpRow = null;
        for (int i = N-1; i >= 0; --i) {
            List<Integer> row = triangle.get(i);
            int[] newDpRow = new int[i+1];
            for (int j = 0; j < newDpRow.length; ++j) {
                int min = 0;
                if (dpRow != null)
                    min = dpRow[j] < dpRow[j+1] ? dpRow[j] : dpRow[j+1];
                newDpRow[j] = min + row.get(j);
            }
            dpRow = newDpRow;
        }
        return dpRow[0];
    }
}
