package com.rainz;

/*
 * There are n computers numbered from 0 to n-1 connected by ethernet cables connections forming a network where connections[i] = [a, b] represents a connection between computers a and b. Any computer can reach any other computer directly or indirectly through the network.
 * Given an initial computer network connections. You can extract certain cables between two directly connected computers, and place them between any pair of disconnected computers to make them directly connected. Return the minimum number of times you need to do this in order to make all the computers connected. If it's not possible, return -1.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberofOperationstoMakeNetworkConnected {
    public static void test(String args[]) {
        int[][] connections1 = {{0,1},{0,2},{1,2}};
        System.out.println(makeConnected(4, connections1));
        int[][] connections2 = {{0,1},{0,2},{0,3},{1,2},{1,3}};
        System.out.println(makeConnected(6, connections2));
        int[][] connections3 = {{0,1},{0,2},{0,3},{1,2}};
        System.out.println(makeConnected(6, connections3));
        int[][] connections4 = {{0,1},{0,2},{3,4},{2,3}};
        System.out.println(makeConnected(5, connections4));
    }

    private static void dfs(int node, int root, List[] graph, int[] roots) {
        if (roots[node] >= 0)
            return;
        roots[node] = root;
        List<Integer> nbs = graph[node];
        for (int nb: nbs)
            dfs(nb, root, graph, roots);
    }
    public static int makeConnected(int n, int[][] connections) {
        int[] inDegrees = new int[n];
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; ++i)
            graph[i] = new ArrayList<>();
        int spares = 0;
        for (int[] conn: connections) {
            int n1 = conn[0], n2 = conn[1];
            graph[n1].add(n2);
            ++inDegrees[n1];
            graph[n2].add(n1);
            ++inDegrees[n2];
            if (inDegrees[n1] > 1 && inDegrees[n2] > 1)
                ++spares;
        }
        int[] roots = new int[n];
        Arrays.fill(roots, -1);
        int groups = 0;
        for (int node = 0; node < n; ++node) {
            if (roots[node] >= 0)
                continue;
            ++groups;
            dfs(node, node, graph, roots);
        }
        if (groups - 1 <= spares)
            return groups - 1;
        return -1;
    }
}
