package com.rainz;

/*
 * Given an undirected tree, return its diameter: the number of edges in a longest path in that tree.
 * The tree is given as an array of edges where edges[i] = [u, v] is a bidirectional edge between nodes u and v.  Each node has labels in the set {0, 1, ..., edges.length}.
 */

import java.util.ArrayList;
import java.util.List;

public class TreeDiameter {
    public static void test(String args[]) {
        int[][] edges1 = {{0,1},{0,2}};
        System.out.println(treeDiameter(edges1));
        int[][] edges2 = {{0,1},{1,2},{2,3},{1,4},{4,5}};
        System.out.println(treeDiameter(edges2));
    }

    /*
     * Since all nodes are connected, we start DFS with one node.
     * For each node, compute longest and second longest from this node, their sum is max path through this node.
     * Returns both the max path which goes through this node, and overall max.
     */

    // Returns {maxThruNode, maxSoFar}
    private static int[] dfs(int node, List<Integer>[] graph, boolean[] visited, int maxSoFar) {
        List<Integer> nbs = graph[node];
        int max1 = 0, max2 = 0;
        visited[node] = true;
        for (int nb: nbs) {
            if (visited[nb])
                continue;
            int[] ret = dfs(nb, graph, visited, maxSoFar);
            maxSoFar = Math.max(maxSoFar, ret[1]);
            int len = 1 + ret[0];
            if (len > max1) {
                max2 = max1;
                max1 = len;
            } else if (len > max2)
                max2 = len;
        }
        maxSoFar = Math.max(maxSoFar, max1 + max2);
        int[] ret = {max1, maxSoFar};
        return ret;
    }

    public static int treeDiameter(int[][] edges) {
        int N = edges.length + 1; // node count = edge count + 1
        List<Integer>[] graph = new List[N];
        for (int i = 0; i < N; ++i)
            graph[i] = new ArrayList<>();
        for (int[] e: edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        boolean[] visited = new boolean[N];
        return dfs(0, graph, visited, 0)[1];
    }
}
