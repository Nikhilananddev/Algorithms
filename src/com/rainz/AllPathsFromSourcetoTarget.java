package com.rainz;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a directed, acyclic graph of N nodes.  Find all possible paths from node 0 to node N-1, and return them in any order.
 * The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.  graph[i] is a list of all nodes j for which the edge (i, j) exists.
 */

public class AllPathsFromSourcetoTarget {
    public static void test(String args[]) {
        int[][] input1 = {{1,2}, {3}, {3}, {}};
        Main.printList2D(allPathsSourceTarget(input1));
    }

    private static void dfs(int[][] graph, int src, boolean[] visited, List<Integer> path, List<List<Integer>> ans) {
        int dst = graph.length - 1;
        if (src == dst) {
            // Don't forget to make a copy of the path!!!
            List<Integer> sol = new ArrayList<>();
            sol.addAll(path);
            sol.add(src);
            ans.add(sol);
            return;
        }
        if (visited[src])
            return;
        path.add(src);
        visited[src] = true;
        for (int nb: graph[src]) {
            dfs(graph, nb, visited, path, ans);
        }
        path.remove(path.size()-1);
        visited[src] = false;
    }

    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[graph.length];
        dfs(graph, 0, visited, path, ans);
        return ans;
    }
}
