package com.rainz;

/*
 * In this problem, a tree is an undirected graph that is connected and has no cycles.
 * The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.
 * The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v, that represents an undirected edge connecting nodes u and v.
 * Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v.
 */

import java.util.Stack;

public class RedundantConnection {
    public static void test(String args[]) {
        int[][] input1 = {{1,2}, {1,3}, {2,3}};
        Main.printArray(findRedundantConnection(input1));
        int[][] input2 = {{1,2}, {2,3}, {3,4}, {1,4}, {1,5}};
        Main.printArray(findRedundantConnection(input2));
    }

    private static int findRootPathCompression(int[] parents, int n) {
        Stack<Integer> stk = new Stack<>();
        while (n != parents[n]) {
            stk.push(n);
            n = parents[n];
        }
        // Now n is root
        while (!stk.isEmpty()) {
            parents[stk.pop()] = n;
        }
        return n;
    }

    public static int[] findRedundantConnection(int[][] edges) {
        int N = edges.length;
        int[] parents = new int[N+1]; // should be enough for 1-based nodes
        for (int i = 0; i < parents.length; ++i)
            parents[i] = i; // self-pointing nodes are roots
        for (int[] e: edges) {
            int p = e[0];
            int n = e[1];
            int pRoot = findRootPathCompression(parents, p);
            int nRoot = findRootPathCompression(parents, n);
            if (pRoot == nRoot)
                return e;
            parents[nRoot] = pRoot;
        }
        return null;
    }

}
