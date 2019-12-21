package com.rainz;

import java.util.*;

/*
 * In a group of N people (labelled 0, 1, 2, ..., N-1), each person has different amounts of money, and different levels of quietness.
 * For convenience, we'll call the person with label x, simply "person x".
 * We'll say that richer[i] = [x, y] if person x definitely has more money than person y.  Note that richer may only be a subset of valid observations.
 * Also, we'll say quiet[x] = q if person x has quietness q.
 * Now, return answer, where answer[x] = y if y is the least quiet person (that is, the person y with the smallest value of quiet[y]), among all people who definitely have equal to or more money than person x.
 */

public class LoudandRich {
    public static void test(String args[]) {
        int[][] richer = {{1,0},{2,1},{3,1},{3,7},{4,3},{5,3},{6,3}};
        int[] quiet = {3,2,5,4,6,1,7,0};
        Main.printArray(loudAndRich(richer, quiet));
    }

    private static int dfs(List<Integer>[] graph, int[] quiet, int ppl, int[] result)
    {
        if (result[ppl] < 0) {
            result[ppl] = ppl; // initiaize to self
            List<Integer> neighbors = graph[ppl];
            for (Integer n : neighbors) {
                int l = dfs(graph, quiet, n, result);
                if (quiet[l] < quiet[result[ppl]])
                    result[ppl] = l;
            }
        }
        return result[ppl];
    }

    public static int[] loudAndRich(int[][] richer, int[] quiet) {
        int N = quiet.length;

        // Build graph. Note: graph can just be an array of lists instead of hashmap of lists
        List<Integer>[] graph = new ArrayList[N];
        for (int i = 0; i < N; ++i)
            graph[i] = new ArrayList<>();
        for (int[] link: richer) {
            graph[link[1]].add(link[0]);
        }
        int[] result = new int[N];
        Arrays.fill(result, -1);
        for (int ppl = 0; ppl < N; ++ppl) {
            dfs(graph, quiet, ppl, result);
        }
        return result;
    }
}
