package com.rainz;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangleII {
    public static void test(String args[]) {
        Main.printList(getRow(3));
    }

    public static List<Integer> getRow(int rowIndex) {
        List<Integer> prev = new ArrayList<>();
        if (rowIndex < 0)
            return prev;

        List<Integer> curr = new ArrayList<>();
        for (int i = 0; i <= rowIndex; ++i) {
            prev.add(0);
            curr.add(0);
        }
        prev.set(0, 1);

        for (int row = 1; row <= rowIndex; ++row) {
            for (int i = 0; i <= row; ++i) {
                int val1 = (i <= 0 ? 0 : prev.get(i-1));
                int val2 = (i >= row ? 0 : prev.get(i));
                curr.set(i, val1+val2);
            }
            List<Integer> tmp = prev;
            prev = curr;
            curr = tmp;
        }

        return prev;
    }
}
