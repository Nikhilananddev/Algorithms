package com.rainz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * There are n cities numbered from 0 to n-1. Given the array edges where edges[i] = [fromi, toi, weighti] represents a bidirectional and weighted edge between cities fromi and toi, and given the integer distanceThreshold.
 * Return the city with the smallest number of cities that are reachable through some path and whose distance is at most distanceThreshold, If there are multiple such cities, return the city with the greatest number.
 */
public class FindtheCityWiththeSmallestNumberofNeighborsataThresholdDistance {
    public static void test(String args[]) {
        int[][] edges1 = {{0,1,3},{1,2,1},{1,3,4},{2,3,1}};
        System.out.println(findTheCity(4, edges1, 4));
        int[][] edges2 = {{0,1,2},{0,4,8},{1,2,3},{1,4,2},{2,3,1},{3,4,1}};
        System.out.println(findTheCity(5, edges2, 2));
    }
    public static int findTheCity(int n, int[][] edges, int distanceThreshold) {
        long[][] dist = new long[n][n]; // use long to avoid overflow when adding Integer MAX_VALUEs
        for (int r = 0; r < n; ++r) {
            Arrays.fill(dist[r], Integer.MAX_VALUE);
            dist[r][r] = 0;
        }
        for (int[] e: edges) {
            int r = e[0], c = e[1];
            dist[r][c] = e[2];
            dist[c][r] = e[2];
        }
        // Floyd Warshall
        for (int k = 0; k < n; ++k) {
            for (int s = 0; s < n; ++s) {
                for (int d = 0; d < n; ++d) {
                    if (dist[s][k] + dist[k][d] < dist[s][d])
                        dist[s][d] = dist[s][k] + dist[k][d];
                }
            }
        }
        int minCount = Integer.MAX_VALUE, ans = -1;
        for (int i = 0; i < n; ++i) {
            int reachable = 0;
            for (int j = 0; j < n; ++j) {
                if (dist[i][j] <= distanceThreshold)
                    ++reachable; // including self for everyone, so it's okay
            }
            if (reachable <= minCount) {
                minCount = reachable;
                ans = i;
            }
        }
        return ans;
    }

}
