package com.rainz;

import java.util.*;

/*
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 */
public class CourseSchedule {
    public static void test(String args[]) {
        int[][] input1 = {{1,0}};
        System.out.println(canFinish(2, input1));
        int[][] input2 = {{1,0},{0,1}};
        System.out.println(canFinish(2, input2));
    }

    /*
     * Note: this code can be rewritten using a adjacency list graph,
     * where key is node, value (neighbors) is a list of post-requisites.
     * Also you keep an "in-degree" table
     */

    static class Requisites {
        public Set<Integer> preRequisites;
        public Set<Integer> postRequisites;
        public Requisites() {
            preRequisites = new HashSet<>();
            postRequisites = new HashSet<>();
        }
    }
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Requisites> reqList = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; ++i)
            reqList.add(new Requisites());
        for (int[] pair: prerequisites) {
            Requisites pre = reqList.get(pair[1]);
            Requisites post = reqList.get(pair[0]);
            pre.postRequisites.add(pair[0]);
            post.preRequisites.add(pair[1]);
        }
        // Courses in workQ have all prerequisites completed.
        Queue<Integer> workQ = new LinkedList<>();
        for (int i = 0; i < reqList.size(); ++i) {
            Requisites req = reqList.get(i);
            if (req.preRequisites.isEmpty())
                workQ.offer(i);
        }

        int courseTaken = 0;
        while (!workQ.isEmpty()) {
            // Take this course
            Integer course = workQ.poll();
            Requisites req = reqList.get(course);
            ++courseTaken;
            // Remove it from others' prerequisites
            for (int i: req.postRequisites) {
                Requisites r = reqList.get(i);
                r.preRequisites.remove(course);
                if (r.preRequisites.isEmpty())
                    workQ.add(i);
            }
        }
        return (courseTaken == numCourses);
    }
}
