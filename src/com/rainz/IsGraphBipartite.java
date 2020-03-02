package com.rainz;

/*
 * Given an undirected graph, return true if and only if it is bipartite.
 * Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B.
 * The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.  Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.
 */
public class IsGraphBipartite {
    public static void test(String args[]) {
        int[][] input1 = {{1,3}, {0,2}, {1,3}, {0,2}};
        System.out.println(isBipartite(input1));
        int[][] input2 = {{1,2,3}, {0,2}, {0,1,3}, {0,2}};
        System.out.println(isBipartite(input2));
    }
    /*
     * Main idea is if two nodes are connected, they must be in opposite sides
     * So we use dfs to put each node into either set 1 or set 2.
     * If there is a conflict (a node was put into set 1 but now also set 2), the graph is not bipartite.
     */
    private static boolean dfs(int[][] graph, int node, int set, int[] sets) {
        if (sets[node] == 0)
            sets[node] = set;
        else
            return sets[node] == set;
        for (int nb: graph[node]) {
            if (!dfs(graph, nb, 3-set, sets))
                return false;
        }
        return true;
    }
    public static boolean isBipartite(int[][] graph) {
        int N = graph.length;
        int[] sets = new int[N]; // each node belongs to either set 1 or set 2, 0 means not visited yet
        for (int i = 0; i < N; ++i) {
            if (sets[i] == 0) {
                if (!dfs(graph, i, 1, sets))
                    return false;
            }
        }
        return true;
    }

}
