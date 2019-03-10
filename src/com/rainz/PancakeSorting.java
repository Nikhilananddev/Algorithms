package com.rainz;

import java.util.ArrayList;
import java.util.List;

public class PancakeSorting {
    public static void test(String args[]) {
        int[] input = {3,2,4,1};
        System.out.println(pancakeSorting(input));
    }

    private static void reverseArray(int[] A, int end, List<Integer> result) {
        result.add(end+1); // 1-based
        int start = 0;
        while (start < end) {
            int tmp = A[start];
            A[start] = A[end];
            A[end] = tmp;
            ++start;
            --end;
        }
    }

    public static List<Integer> pancakeSorting(int[] A) {
        int endIdx = A.length - 1;
        List<Integer> result = new ArrayList<>();

        while (endIdx > 0) {
            int maxIdx = 0;
            for (int i = 1; i <= endIdx; ++i) {
                if (A[maxIdx] < A[i])
                    maxIdx = i;
            }
            if (maxIdx != endIdx) {
                if (maxIdx != 0) {
                    reverseArray(A, maxIdx, result);
                }
                reverseArray(A, endIdx, result);
            }
            --endIdx;
        }
        return result;
    }
}
