package com.rainz;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
 */
public class PascalsTriangle {
    public static void test(String args[]) {
        System.out.println(generate(5));
    }
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> answer = new ArrayList<>();
        if (numRows <= 0)
            return answer;

        List<Integer> prev = new ArrayList<>();
        prev.add(1);
        answer.add(prev);
        for (int row = 2; row <= numRows; ++row) {
            ArrayList<Integer> curr = new ArrayList<>();
            for (int i = 0; i < row; ++i) {
                int val1 = (i <= 0 ? 0 : prev.get(i-1));
                int val2 = (i >= row-1 ? 0 : prev.get(i));
                curr.add(val1+val2);
            }
            answer.add(curr);
            prev = curr;
        }

        return answer;
    }
}
