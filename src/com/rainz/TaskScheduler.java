package com.rainz;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class TaskScheduler {
    public static void test(String args[]) {
        char[] input = {'A','A','A','B','B','B'};
        System.out.println(taskScheduler(input, 2));
    }

    public static int taskScheduler(char[] tasks, int n) {
        int[] histo = new int[26];
        for (char c: tasks)
            ++histo[c-'A'];

        PriorityQueue<Integer> pq = new PriorityQueue<>(
                (Integer i1, Integer i2) -> Integer.compare(histo[i2], histo[i1]));
        for (int i = 0; i < histo.length; ++i) {
            if (histo[i] > 0)
                pq.offer(i);
        }

        /*
         * Note: there's a way to avoid using waitQ:
         * Let cycle = n + 1. In the loop, extract up to "cycle" jobs every time.
         */

        Queue<Integer> waitQ = new LinkedList<>();
        for (int i = 0; i < n; ++i)
            waitQ.offer(null);

        int result = 0;
        int waitCount = 0;
        while (!pq.isEmpty() || waitCount > 0) {
            ++result;
            Integer job = pq.poll();
            if (job != null) {
                --histo[job];
                if (histo[job] == 0)
                    job = null;
            }
            waitQ.offer(job);
            if (job != null)
                ++waitCount;
            Integer nextJob = waitQ.poll();
            if (nextJob != null) {
                --waitCount;
                pq.offer(nextJob);
            }
        }
        return result;
    }
}
