package com.rainz;

import java.util.Stack;

public class NumberofConnectedComponentsinanUndirectedGraph {
    public static void test(String args[]) {
        int[][] input1 = {{0, 1}, {1, 2}, {3, 4}};
        System.out.println(countComponents(5, input1));
        int[][] input2 = {{0, 1}, {1, 2}, {2, 3}, {3, 4}};
        System.out.println(countComponents(5, input2));
    }

    private static int getRootPathCompression(int[] parents, int node) {
        Stack<Integer> stk = new Stack<>();
        int curr = node;
        while (parents[curr] != curr) {
            stk.push(curr);
            curr = parents[curr];
        }
        while (!stk.isEmpty()) {
            int a = stk.pop();
            parents[a] = curr;
        }
        return curr;
    }

    public static int countComponents(int n, int[][] edges) {
        int[] parents = new int[n];
        for (int i = 0; i < n; ++i)
            parents[i] = i;
        int ans = n;
        for (int[] e: edges) {
            int root0 = getRootPathCompression(parents, e[0]);
            int root1 = getRootPathCompression(parents, e[1]);
            if (root0 != root1) {
                parents[root1] = root0;
                --ans;
            }
        }
        return ans;
    }
}
