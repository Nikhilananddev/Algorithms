package com.rainz;

/*
 * Given an array of integers arr, you are initially positioned at the first index of the array.
 * In one step you can jump from index i to index:
 * i + 1 where: i + 1 < arr.length.
 * i - 1 where: i - 1 >= 0.
 * j where: arr[i] == arr[j] and i != j.
 * Return the minimum number of steps to reach the last index of the array.
 * Notice that you can not jump outside of the array at any time.
 */

import java.util.*;

public class JumpGameIV {
    public static void test(String args[]) {
        int[] arr1 = {100,-23,-23,404,100,23,23,23,3,404};
        System.out.println(minJumps(arr1));
        int[] arr2 = {7};
        System.out.println(minJumps(arr2));
        int[] arr3 = {7,6,9,6,9,6,9,7};
        System.out.println(minJumps(arr3));
        int[] arr4 = {6,1,9};
        System.out.println(minJumps(arr4));
        int[] arr5 = {11,22,7,7,7,7,7,7,7,22,13};
        System.out.println(minJumps(arr5));
    }

    public static int minJumps(int[] arr) {
        int N = arr.length;
        boolean[] visited = new boolean[N];
        Map<Integer, List<Integer>> valMap = new HashMap<>();
        for (int i = 0; i < N; ++i) {
            int n = arr[i];
            valMap.putIfAbsent(n, new ArrayList<>());
            valMap.get(n).add(i);
        }
        int ans = 0;
        Set<Integer> currLevel = new HashSet<>();
        currLevel.add(0);
        while (!currLevel.isEmpty()) {
            Set<Integer> nextLevel = new HashSet<>();
            for (int node: currLevel) {
                if (node == N-1)
                    return ans;
                if (visited[node])
                    continue;
                visited[node] = true;
                if (node - 1 >= 0)
                    nextLevel.add(node - 1);
                if (node + 1 < N)
                    nextLevel.add(node + 1);
                List<Integer> sameVal = valMap.get(arr[node]);
                if (sameVal != null) {
                    for (int nextNode : sameVal) {
                        if (!visited[nextNode])
                            nextLevel.add(nextNode);
                    }
                    // Remove this since we already enqueued all these nodes. Needed to avoid TLE
                    valMap.remove(arr[node]);
                }
            }
            currLevel = nextLevel;
            ++ans;
        }
        return ans;
    }
}
