package com.rainz;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * There are N workers.  The i-th worker has a quality[i] and a minimum wage expectation wage[i].
 * Now we want to hire exactly K workers to form a paid group.  When hiring a group of K workers, we must pay them according to the following rules:
 * Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
 * Every worker in the paid group must be paid at least their minimum wage expectation.
 * Return the least amount of money needed to form a paid group satisfying the above conditions.
 */
public class MinimumCosttoHireKWorkers {
    public static void test(String args[]) {
        int[] quality1 = {10,20,5}, wage1 = {70,50,30};
        System.out.println(mincostToHireWorkers(quality1, wage1, 2));
        int[] quality2 = {3,1,10,10,1}, wage2 = {4,8,2,2,7};
        System.out.println(mincostToHireWorkers(quality2, wage2, 3));
    }

    /*
     * First, sort workers by wage-to-quality ratio.
     * In order to meet min wage of K workers, the ratio has to be at least Kth smallest
     * So we compute total cost of first K workers with kth ratio first.
     * Then we add new workers with higher ratios while removing most expensive workers to keep count at K.
     * Since all workers use the same ratio, most expensive worker is the one with highest quality.
     * So we use a priority queue and sort based on quality.
     * Each time we add a new worker, we also remove the most expensive worker, then compute total cost.
     */
    public static double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        int N = quality.length;
        int[][] workers = new int[N][2];
        for (int i = 0; i < N; ++i) {
            workers[i][0] = quality[i];
            workers[i][1] = wage[i];
        }
        // Sort by wage-to-quality ratio
        Arrays.sort(workers, (x, y)->Double.compare((double)x[1]/x[0], (double)y[1]/y[0]));
        // Put quality of first k workers with lowest ratio in a max PQ.
        // With a fixed ratio, quality directly reflects wage.
        // Then we increase ratio while getting rid of most expensive worker
        Queue<Integer> pq = new PriorityQueue<>((x, y)->workers[y][0]-workers[x][0]);
        int qualitySum = 0;
        double ans = Double.MAX_VALUE;
        for (int i = 0; i < N; ++i) {
            pq.add(i);
            qualitySum += workers[i][0];
            if (i >= K - 1) {
                // We have K workers, compute cost
                int ratioIdx = Math.max(K - 1, i);
                double ratio = (double)workers[ratioIdx][1] / workers[ratioIdx][0];
                if (i >= K) {
                    // Find the most expensive worker to remove
                    int expensiveIdx = pq.remove();
                    qualitySum -= workers[expensiveIdx][0];
                }
                double totalCost = qualitySum * ratio;
                if (totalCost < ans)
                    ans = totalCost;
            }
        }
        return ans;
    }
}
