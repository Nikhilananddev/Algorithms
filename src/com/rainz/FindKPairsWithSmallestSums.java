package com.rainz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class FindKPairsWithSmallestSums {
    public static void test(String args[]) {
        int[] nums1 = {1,7,11};
        int[] nums2 = {2,4,6};
        List<int[]> result = findKPairsWithSmallestSums(nums1, nums2, 3);
        for (int[] arr: result)
            System.out.println(Arrays.toString(arr));
    }

        public static List<int[]> findKPairsWithSmallestSums(int[] nums1, int[] nums2, int k) {
            final int M = nums1.length;
            final int N = nums2.length;
            boolean[][] visited = new boolean[M][N];
            List<int[]> result = new ArrayList<>();
            if (M == 0 || N == 0)
                return result;

            PriorityQueue<Integer> pq = new PriorityQueue<> (
                    (Integer a1, Integer a2) -> Integer.compare(nums1[a1/N]+nums2[a1%N], nums1[a2/N]+nums2[a2%N]));
            pq.offer(0);
            while (!pq.isEmpty() && result.size() < k) {
                Integer idx = pq.poll();
                int idxM = idx / N;
                int idxN = idx % N;
                int[] indices = {nums1[idxM], nums2[idxN]};
                result.add(indices);
                if (idxM < M - 1) {
                    int idxM1 = idxM + 1;
                    if (!visited[idxM1][idxN]) {
                        pq.add(idxM1 * N + idxN);
                        visited[idxM1][idxN] = true;
                    }
                }
                if (idxN < N - 1) {
                    int idxN1 = idxN + 1;
                    if (!visited[idxM][idxN1]) {
                        pq.add(idxM * N + idxN1);
                        visited[idxM][idxN1] = true;
                    }
                }
            }
            return result;
    }
}
