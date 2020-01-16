package com.rainz;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

/*
 * There are N cities numbered from 1 to N.
 * You are given connections, where each connections[i] = [city1, city2, cost] represents the cost to connect city1 and city2 together.  (A connection is bidirectional: connecting city1 and city2 is the same as connecting city2 and city1.)
 * Return the minimum cost so that for every pair of cities, there exists a path of connections (possibly of length 1) that connects those two cities together.  The cost is the sum of the connection costs used. If the task is impossible, return -1.
 */

public class ConnectingCitiesWithMinimumCost {
    public static void test(String args[]) {
        int[][] input1 = {{1,2,5},{1,3,6},{2,3,1}};
        System.out.println(minimumCost(3, input1));
        int[][] input2 = {{1,2,3},{3,4,4}};
        System.out.println(minimumCost(4, input2));
    }

    private static int findRootWithPathCompression(int[] parents, int n)
    {
        Stack<Integer> stk = new Stack<>();
        while (n != -1) {
            stk.push(n);
            n = parents[n];
        }
        int root = stk.pop();
        while (!stk.isEmpty()) {
            int node = stk.pop();
            parents[node] = root;
        }
        return root;
    }

    public static int minimumCost(int N, int[][] connections) {
        Arrays.sort(connections, Comparator.comparingInt(x -> x[2]));
        int[] parents = new int[N+1];
        Arrays.fill(parents, -1);
        int graphs = N;
        int ans = 0;
        for (int[] conn: connections) {
            int node = conn[1];
            int parent = conn[0];
            int nRoot = findRootWithPathCompression(parents, node);
            int pRoot = findRootWithPathCompression(parents, parent);
            if (nRoot != pRoot) {
                // Use this connection to merge two graphs
                ans += conn[2];
                --graphs;
                parents[nRoot] = pRoot;
            }
        }
        if (graphs == 1)
            return ans;
        else
            return -1;
    }
}
