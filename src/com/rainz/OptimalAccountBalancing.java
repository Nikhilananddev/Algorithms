package com.rainz;

/*
 * A group of friends went on holiday and sometimes lent each other money. For example, Alice paid for Bill's lunch for $10. Then later Chris gave Alice $5 for a taxi ride. We can model each transaction as a tuple (x, y, z) which means person x gave person y $z. Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID), the transactions can be represented as [[0, 1, 10], [2, 0, 5]].
 * Given a list of transactions between a group of people, return the minimum number of transactions required to settle the debt.
 * Note:
 * A transaction will be given as a tuple (x, y, z). Note that x <> y and z > 0.
 * Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OptimalAccountBalancing {
    public static void test(String args[]) {
        int[][] input1 = {{0,1,10}, {2,0,5}};
        System.out.println(minTransfers(input1));
        int[][] input2 = {{0,1,10}, {1,0,1}, {1,2,5}, {2,0,5}};
        System.out.println(minTransfers(input2));
    }

    private static void dfs(List<Integer> balances, int start, int count, int[] ans) {
        int N = balances.size();
        while (start < N && balances.get(start) == 0)
            ++start;
        if (start >= N) {
            if (count < ans[0])
                ans[0] = count;
            return;
        }
        int sBal = balances.get(start);
        boolean sPositive = sBal > 0;
        for (int i = start + 1; i < N; ++i) {
            int iBal = balances.get(i);
            boolean iPositive = iBal > 0;
            if (sPositive == iPositive)
                continue;
            // Balances at start and i have opposite signs, so add start to i.
            // Balance at start is now cleared.
            balances.set(i, iBal + sBal);
            dfs(balances, start+1, count+1, ans);
            balances.set(i, iBal);
        }
    }

    public static int minTransfers(int[][] transactions) {
        Map<Integer, Integer> balanceTable = new HashMap<>();
        for (int[] trans: transactions) {
            int p1 = trans[0], p2 = trans[1], amt = trans[2];
            balanceTable.put(p1, balanceTable.getOrDefault(p1, 0) - amt);
            balanceTable.put(p2, balanceTable.getOrDefault(p2, 0) + amt);
        }
        List<Integer> balances = new ArrayList<>();
        for (int b: balanceTable.values()) {
            if (b != 0)
                balances.add(b);
        }
        int[] ans = { Integer.MAX_VALUE };
        dfs(balances, 0, 0, ans);
        return ans[0];
    }
}
