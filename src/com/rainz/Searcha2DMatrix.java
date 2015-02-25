package com.rainz;

/**
 * Created by Yu on 2/24/2015.
 */
public class Searcha2DMatrix {
    public static void test(String args[]) {
        int[][] test = {
                {1, 3,  5,  7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        };
        System.out.println(searchMatrix(test, 23));
        System.out.println(searchMatrix(test, 13));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int numRows = matrix.length;
        if (numRows == 0)
            return false;
        int numCols = matrix[0].length;
        int lower = 0, upper = numRows*numCols - 1;
        while (lower < upper - 1) {
            int mid = (lower + upper) / 2;
            int curr = matrix[mid/numCols][mid%numCols];
            if (curr == target)
                return true;
            if (curr < target)
                lower = mid;
            else
                upper = mid;
        }

        return (target == matrix[lower/numCols][lower%numCols] || target == matrix[upper/numCols][upper%numCols]);
    }
}
