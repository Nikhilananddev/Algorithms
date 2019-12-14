package com.rainz;

import java.util.*;

public class SmallestStringWithSwaps {
    public static void test(String args[]) {
        List<List<Integer>> input1 = new ArrayList<>();
        input1.add(new ArrayList<>(List.of(0, 3)));
        input1.add(new ArrayList<>(List.of(1, 2)));

        List<List<Integer>> input2 = new ArrayList<>();
        input2.add(new ArrayList<>(List.of(0, 3)));
        input2.add(new ArrayList<>(List.of(1, 2)));
        input2.add(new ArrayList<>(List.of(0, 2)));

        List<List<Integer>> input3 = new ArrayList<>();
        input3.add(new ArrayList<>(List.of(0, 1)));
        input3.add(new ArrayList<>(List.of(1, 2)));

        System.out.println(smallestStringWithSwaps("dcab", input1));
        System.out.println(smallestStringWithSwaps("dcab", input2));
        System.out.println(smallestStringWithSwaps("cba", input3));
    }

    public static String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        /*
         * Each pair is a link. Build a graph based on it.
         * Find all connected sub-graphs. Letters within a sub-graph can be freely swapped.
         * So we just need to sort letters within the same sub-graph and assign them back.
         */
        char[] result = s.toCharArray();
        // Build adjacency list graph
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (List<Integer> p : pairs) {
            int a = p.get(0);
            int b = p.get(1);
            Set<Integer> neighbors = graph.get(a);
            if (neighbors == null) {
                neighbors = new HashSet<>();
                graph.put(a, neighbors);
            }
            neighbors.add(b);
            neighbors = graph.get(b);
            if (neighbors == null) {
                neighbors = new HashSet<>();
                graph.put(b, neighbors);
            }
            neighbors.add(a);
        }

        // Discover groups of connected nodes and swap them by sorting
        for (int node : graph.keySet()) {
            // BFS
            Queue<Integer> workQ = new LinkedList<>();
            workQ.add(node);
            Set<Integer> connected = new TreeSet<>(); // stores connected nodes; sorted
            while (!workQ.isEmpty()) {
                int n = workQ.remove();
                Set<Integer> neighbors = graph.get(n);
                if (neighbors == null)
                    continue; // n was visited
                graph.put(n, null); // mark n as visited
                connected.add(n);
                workQ.addAll(neighbors);
            }
            // Get all chars for connected nodes
            char[] swapGroup = new char[connected.size()];
            int i = 0;
            for (int idx : connected) {
                swapGroup[i++] = s.charAt(idx);
            }
            // Sort all chars to produce lexicographically smallest result
            Arrays.sort(swapGroup);
            i = 0;
            // Now assign them back.
            for (int idx : connected) {
                result[idx] = swapGroup[i++];
            }
        }
        return new String(result);
    }
}
