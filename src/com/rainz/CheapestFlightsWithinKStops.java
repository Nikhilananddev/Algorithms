package com.rainz;

/*
 * There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.
 * Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.
 */

import java.util.ArrayList;
import java.util.List;

public class CheapestFlightsWithinKStops {
    public static void test(String args[]) {
        int[][] edges1 = {{0,1,100},{1,2,100},{0,2,500}};
        System.out.println(findCheapestPrice(3, edges1, 0, 2, 1));
        int[][] edges2 = {{0,1,100},{1,2,100},{0,2,500}};
        System.out.println(findCheapestPrice(3, edges2, 0, 2, 0));
    }

    private static int dfs(List<int[]>[] graph, boolean[] visited, int src, int dst, int k, int cost, int minCost) {
        if (k <= 0 || cost > minCost || visited[src])
            return minCost;
        if (src == dst) {
            if (cost < minCost)
                minCost = cost;
            return minCost;
        }
        visited[src] = true;
        List<int[]> neighbors = graph[src];
        for (int[] nb: neighbors) {
            minCost = dfs(graph, visited, nb[0], dst, k-1, cost+nb[1], minCost);
        }
        visited[src] = false;
        return minCost;
    }

    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        List<int[]>[] graph = new List[n];
        for (int i = 0; i < n; ++i)
            graph[i] = new ArrayList<>();
        for (int[] fl: flights) {
            List<int[]> neighbors = graph[fl[0]];
            int[] nb = {fl[1], fl[2]}; // dest, price
            neighbors.add(nb);
        }
        boolean[] visited = new boolean[n];
        // K is # stops, total cities is K+2
        int ans = dfs(graph, visited, src, dst, K+2, 0, Integer.MAX_VALUE);
        if (ans == Integer.MAX_VALUE)
            ans = -1;
        return ans;
    }
}
