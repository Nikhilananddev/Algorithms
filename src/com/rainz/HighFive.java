package com.rainz;

import java.util.*;

/*
 * Given a list of scores of different students, return the average score of each student's top five scores in the order of each student's id.
 * Each entry items[i] has items[i][0] the student's id, and items[i][1] the student's score.  The average score is calculated using integer division.
 */
public class HighFive {
    public static void test(String args[]) {
        int[][] input1 = {{1,91},{1,92},{2,93},{2,97},{1,60},{2,77},{1,65},{1,87},{1,100},{2,100},{2,76}};
        Main.printArray2D(highFive(input1));
    }

    public static int[][] highFive(int[][] items) {
        Map<Integer, PriorityQueue<Integer>> students = new TreeMap<>();
        for (int[] item: items) {
            int id = item[0];
            int score = item[1];
            PriorityQueue<Integer> pq = students.get(id);
            if (pq == null) {
                pq = new PriorityQueue<>();
                students.put(id, pq);
            }
            pq.add(score);
            if (pq.size() > 5)
                pq.remove();
        }
        int[][] ans = new int[students.size()][2];
        int idx = 0;
        for (Map.Entry<Integer, PriorityQueue<Integer>> entry: students.entrySet()) {
            PriorityQueue<Integer> pq = entry.getValue();
            int scoreCount = pq.size();
            int sum = 0;
            while (!pq.isEmpty())
                sum += pq.remove();
            ans[idx][0] = entry.getKey();
            ans[idx][1] = sum/scoreCount;
            ++idx;
        }
        return ans;
    }
}
