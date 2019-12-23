package com.rainz;

/*
 * Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.
 * Each person may dislike some other people, and they should not go into the same group.
 * Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.
 * Return true if and only if it is possible to split everyone into two groups in this way.
 */

import java.util.*;

public class PossibleBipartition {
    public static void test(String args[]) {
        int[][] input1 = {{1,2},{1,3},{2,4}};
        System.out.println(possibleBipartition(4, input1));
        int[][] input2 = {{1,2},{1,3},{2,3}};
        System.out.println(possibleBipartition(3, input2));
        int[][] input3 = {{1,2},{2,3},{3,4},{4,5},{1,5}};
        System.out.println(possibleBipartition(5, input3));
    }
    private static boolean dfs(int[][] dislikes, int start, int[] groups) {
        if (start >= dislikes.length)
            return true; // done
        int[] d = dislikes[start];
        int g0 = groups[d[0]];
        int g1 = groups[d[1]];
        if (g0 != 0 && g1 != 0) {
            if (g0 == g1) {
                return false; // conflict found
            }
            if (dfs(dislikes, start + 1, groups))
                return true;
        } else if (g0 != 0) {
            groups[d[1]] = 3 - g0;
            if (dfs(dislikes, start + 1, groups))
                return true;
            groups[d[1]] = 0;
        } else if (g1 != 0) {
            groups[d[0]] = 3 - g1;
            if (dfs(dislikes, start + 1, groups))
                return true;
            groups[d[0]] = 0;
        }
        else {
            groups[d[0]] = 1;
            groups[d[1]] = 2;
            if (dfs(dislikes, start + 1, groups))
                return true;
            groups[d[0]] = 2;
            groups[d[1]] = 1;
            if (dfs(dislikes, start + 1, groups))
                return true;
            groups[d[0]] = 0;
            groups[d[1]] = 0;
        }
        return false;
    }

    public static boolean possibleBipartitionDfs(int N, int[][] dislikes) {
        Arrays.sort(dislikes, (x, y) -> Integer.compare(x[0], y[0]));
        int[] groups = new int[N+1];
        groups[1] = 1;
        return dfs(dislikes, 0, groups);
    }

    // Find connected nodes
    private static boolean bfs(List<Integer>[] graph, int[] groups, int node) {
        if (groups[node] != 0)
            return true; // already visited
        groups[node] = 1; // assign first node to group 1
        Queue<Integer> workQ = new LinkedList<>();
        workQ.add(node);
        while (!workQ.isEmpty()) {
            int n = workQ.remove();
            List<Integer> neighbors = graph[n];
            for (int nb: neighbors) {
                if (groups[nb] == 0) {
                    groups[nb] = 3 - groups[n];
                    workQ.add(nb);
                    continue;
                }
                if (groups[nb] != 3 - groups[n])
                    return false;
            }
        }
        return true;
    }

    public static boolean possibleBipartition(int N, int[][] dislikes) {
        List<Integer>[] graph = new List[N+1];
        for (int i = 1; i <= N; ++i)
            graph[i] = new ArrayList<>();
        for (int[] d: dislikes) {
            graph[d[0]].add(d[1]);
            graph[d[1]].add(d[0]);
        }
        int[] groups = new int[N+1];
        for (int i = 1; i <= N; ++i) {
            if (!bfs(graph, groups, i))
                return false;
        }
        return true;
    }
}
