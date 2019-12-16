package com.rainz;

import java.util.*;

public class BeautifulArrangement {
    public static void test(String args[]) {
        System.out.println(countArrangement(2));
    }

    private static int permutation(int[] arrangement, int start, List<Set<Integer>> table, int result) {
        int N = arrangement.length - 1;
        if (start > N) {
            return result+1;
        }
        Set<Integer> divisors = table.get(start);
        for (int i = start; i <= N; ++i) {
            if (divisors.contains(arrangement[i])) {
                int tmp = arrangement[start];
                arrangement[start] = arrangement[i];
                arrangement[i] = tmp;
                result = permutation(arrangement, start+1, table, result);
                tmp = arrangement[start];
                arrangement[start] = arrangement[i];
                arrangement[i] = tmp;
            }
        }
        return result;
    }

    public static int countArrangement(int N) {
        List<Set<Integer>> divisorTable = new ArrayList<>();
        int[] arrangement = new int[N+1];
        for (int i = 0; i <= N; ++i) {
            arrangement[i] = i;
            Set<Integer> table = new HashSet<>();
            divisorTable.add(table);
        }
        for (int i = 1; i <= N; ++i) {
            for (int j = i; j <= N; ++j) {
                if (i % j == 0 || j % i == 0) {
                    divisorTable.get(i).add(j);
                    if (i != j)
                        divisorTable.get(j).add(i);
                }
            }
        }
        return permutation(arrangement, 1, divisorTable, 0);
    }
}
