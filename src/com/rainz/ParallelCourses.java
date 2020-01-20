package com.rainz;

import java.util.ArrayList;
import java.util.List;

/*
 * There are N courses, labelled from 1 to N.
 * We are given relations[i] = [X, Y], representing a prerequisite relationship between course X and course Y: course X has to be studied before course Y.
 * In one semester you can study any number of courses as long as you have studied all the prerequisites for the course you are studying.
 * Return the minimum number of semesters needed to study all courses.  If there is no way to study all the courses, return -1.
 */
public class ParallelCourses {
    public static void test(String args[]) {
        int[][] relations1 = {{1,3},{2,3}};
        System.out.println(minimumSemesters(3, relations1));
        int[][] relations2 = {{1,2},{2,3},{3,1}};
        System.out.println(minimumSemesters(3, relations2));
    }

    // Kahn’s algorithm for topo sort
    public static int minimumSemesters(int N, int[][] relations) {
        List<Integer>[] graph = new List[N+1];
        for (int i = 1; i < graph.length; ++i)
            graph[i] = new ArrayList<>();
        int[] inDegrees = new int[N+1];
        for (int[] r: relations) {
            graph[r[0]].add(r[1]);
            ++inDegrees[r[1]];
        }
        int ans = 0;
        int coursesTaken = 0;
        List<Integer> currSemester = new ArrayList<>();
        for (int i = 1; i < inDegrees.length; ++i)
            if (inDegrees[i] == 0)
                currSemester.add(i);
        while (!currSemester.isEmpty()) {
            ++ans;
            List<Integer> nextSemester = new ArrayList<>();
            for (int course: currSemester) {
                ++coursesTaken;
                List<Integer> nbs = graph[course];
                for (int nb: nbs) {
                    if (--inDegrees[nb] == 0)
                        nextSemester.add(nb);
                }
            }
            currSemester = nextSemester;
        }
        if (coursesTaken == N)
            return ans;
        return -1;
    }
}
