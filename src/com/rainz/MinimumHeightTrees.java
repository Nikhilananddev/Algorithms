package com.rainz;

import java.util.*;

public class MinimumHeightTrees {
    public static void test(String args[]) {
        int[][] edges = {{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}};
        System.out.println(minimumHeightTrees(6, edges));
    }

    /*
    - By specifying a root in the graph, the entire tree is determined
    - This problem boils down to finding longest path between two nodes.
    - This solution works by stripping off leave nodes level by level.
    - At the end you'll leave with either 1 or 2 node(s) as solution.
     */
    public static List<Integer> minimumHeightTrees(int n, int[][] edges) {
        Map<Integer, Set<Integer>> adjList = new HashMap<>();
        for (int i = 0; i < n; ++i)
            adjList.put(i, new HashSet<>());
        for (int[] e: edges) {
            adjList.get(e[0]).add(e[1]);
            adjList.get(e[1]).add(e[0]);
        }
        List<Integer> leaves = new ArrayList<>();
        while (adjList.size() > 2){
            for (Map.Entry<Integer, Set<Integer>> entry: adjList.entrySet()) {
                Set<Integer> neighbors = entry.getValue();
                if (neighbors.size() <= 1) {
                    // This is a leaf.
                    Integer leaf = entry.getKey();
                    leaves.add(leaf);
                }
            }
            for (Integer leaf: leaves) {
                // Remove links to this leaf from other node
                Set<Integer> neighbors = adjList.get(leaf);
                for (Integer neighbor: neighbors)
                    adjList.get(neighbor).remove(leaf);
                // Remove this node
                adjList.remove(leaf);
            }
            leaves.clear();
        }
        return new ArrayList<>(adjList.keySet());
    }
}
