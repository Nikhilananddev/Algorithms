package com.rainz;

/*
 * Given the edges of a directed graph, and two nodes source and destination of this graph, determine whether or not all paths starting from source eventually end at destination, that is:
 * At least one path exists from the source node to the destination node
 * If a path exists from the source node to a node with no outgoing edges, then that node is equal to destination.
 * The number of possible paths from source to destination is a finite number.
 * Return true if and only if all roads from source lead to destination.
 * The given graph may have self loops and parallel edges.
 * The number of nodes n in the graph is between 1 and 10000
 * The number of edges in the graph is between 0 and 10000
 * 0 <= edges.length <= 10000
 * edges[i].length == 2
 * 0 <= source <= n - 1
 * 0 <= destination <= n - 1
 */

import java.util.*;

public class AllPathsfromSourceLeadtoDestination {
    public static void test(String args[]) {
        int n = 3, source = 0, destination = 2;
        int[][] edges = {{0,1},{0,2}};
        System.out.println(leadsToDestination(n, edges, source, destination));
        n = 4; source = 0; destination = 3;
        int[][] edges2 = {{0,1},{0,3},{1,2},{2,1}};
        System.out.println(leadsToDestination(n, edges2, source, destination));
        n = 4; source = 0; destination = 3;
        int[][] edges3 = {{0,1},{0,2},{1,3},{2,3}};
        System.out.println(leadsToDestination(n, edges3, source, destination));
        n = 3; source = 0; destination = 2;
        int[][] edges4 = {{0,1},{1,1},{1,2}};
        System.out.println(leadsToDestination(n, edges4, source, destination));
        n = 2; source = 0; destination = 1;
        int[][] edges5 = {{0,1},{1,1}};
        System.out.println(leadsToDestination(n, edges5, source, destination));
                
    }

    private static boolean dfs(List<Integer>[] graph, boolean[] visited, Set<Integer> path, int src, int dst) {
        if (src == dst)
            return true;
        if (graph[src].size() == 0)
            return false; // not dst but has no outgoing edges
        visited[src] = true;
        path.add(src);
        for (int nb: graph[src]) {
            if (path.contains(nb))
                return false; // loop detected
            if (visited[nb])
                continue;
            if (!dfs(graph, visited, path, nb, dst))
                return false;
        }
        path.remove(src);
        visited[src] = false;
        return true;
    }

    public static boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; ++i)
            graph[i] = new ArrayList<>();
        for (int[] e: edges)
            graph[e[0]].add(e[1]);
        if (graph[destination].size() > 0)
            return false; // destination node leads elsewhere
        boolean[] visited = new boolean[n];
        Set<Integer> path = new HashSet();
        return dfs(graph, visited, path, source, destination);
    }
}
