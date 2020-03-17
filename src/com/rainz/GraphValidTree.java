package com.rainz;

/*
 * Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphValidTree {
    public static void test(String args[]) {
        int[][] edges1 = {{0,1}, {0,2}, {0,3}, {1,4}};
        System.out.println(validTree(5, edges1));
        int[][] edges2 = {{0,1}, {1,2}, {2,3}, {1,3}, {1,4}};
        System.out.println(validTree(5, edges2));
    }

    public static boolean validTree(int n, int[][] edges) {
        boolean[] visited = new boolean[n];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; ++i)
            graph.add(new ArrayList<>());
        for (int[] e: edges) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }
        Queue<int[]> workQ = new LinkedList<>(); // {from, node}
        int remain = n;
        int[] start = {-1, 0};
        workQ.add(start);
        while (!workQ.isEmpty()) {
            int[] rec = workQ.poll();
            int from = rec[0];
            int node = rec[1];
            if (visited[node])
                return false;
            --remain;
            visited[node] = true;
            List<Integer> nbs = graph.get(node);
            for (int nb: nbs) {
                /*
                 * Note: one trick to avoid using "from" is:
                 * Once you go from "a" to "b", just remove the back link (b->a) from graph.
                 */
                if (nb == from)
                    continue; // don't go back
                int[] recNext = {node, nb};
                workQ.add(recNext);
            }
        }
        return remain == 0;
    }
}
