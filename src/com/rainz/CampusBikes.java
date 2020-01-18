package com.rainz;

import java.util.*;

/*
 * On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker and bike is a 2D coordinate on this grid.
 * Our goal is to assign a bike to each worker. Among the available bikes and workers, we choose the (worker, bike) pair with the shortest Manhattan distance between each other, and assign the bike to that worker. (If there are multiple (worker, bike) pairs with the same shortest Manhattan distance, we choose the pair with the smallest worker index; if there are multiple ways to do that, we choose the pair with the smallest bike index). We repeat this process until there are no available workers.
 * The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.
 * Return a vector ans of length N, where ans[i] is the index (0-indexed) of the bike that the i-th worker is assigned to.
 */
public class CampusBikes {
    public static void test(String args[]) {
        int[][] workers1 = {{0,0},{2,1}}, bikes1 = {{1,2},{3,3}};
        Main.printArray(assignBikes(workers1, bikes1));
        int[][] workers2 = {{0,0},{1,1},{2,0}}, bikes2 = {{1,0},{2,2},{2,1}};
        Main.printArray(assignBikes(workers2, bikes2));
        int[][] workers3 = {{0,0},{1,0},{2,0},{3,0},{4,0},{5,0},{6,0},{7,0}};
        int[][] bikes3 = {{0,999},{1,999},{2,999},{3,999},{4,999},{5,999},{6,999},{7,999},{8,999},{9,999}};
        Main.printArray(assignBikes(workers3, bikes3));
    }
    public static int[] assignBikes(int[][] workers, int[][] bikes) {
        int N = workers.length;
        int[] ans = new int[N];
        Map<Integer, Set<int[]>> distTbl = new TreeMap<>();
        Set<Integer> visitedWorkers = new HashSet<>();
        Set<Integer> visitedBikes = new HashSet<>();
        for (int w = 0; w < N; ++w) {
            int[] worker = workers[w];
            for (int b = 0; b < bikes.length; ++b) {
                int[] bike = bikes[b];
                int dist = Math.abs(worker[0]-bike[0]) + Math.abs(worker[1]-bike[1]);
                Set<int[]> distSet = distTbl.get(dist);
                if (distSet == null) {
                    distSet = new TreeSet<>((x, y) -> x[0] != y[0] ? x[0] - y[0] : x[1] - y[1]);
                    distTbl.put(dist, distSet);
                }
                int[] pair = {w, b};
                distSet.add(pair);
            }
        }

        for (Map.Entry<Integer, Set<int[]>> entry: distTbl.entrySet()) {
            for (int[] pair: entry.getValue()) {
                int w = pair[0], b = pair[1];
                if (visitedWorkers.contains(w) || visitedBikes.contains(b))
                    continue;
                ans[w] = b;
                visitedWorkers.add(w);
                visitedBikes.add(b);
            }
        }

        return ans;
    }

}
