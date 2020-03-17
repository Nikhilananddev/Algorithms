package com.rainz;

/*
 * Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.
 * Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).
 * You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n). There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.
 */
public class FindtheCelebrity {
    public static void test(String args[]) {
        int[][] input1 = {
                {1,1,0},
                {0,1,0},
                {1,1,1}
        };
        FindtheCelebrity celeb = new FindtheCelebrity(input1);
        System.out.println(celeb.findCelebrity(input1.length));
    }

    private int[][] _graph;
    public FindtheCelebrity(int[][] graph) {
        _graph = graph;
    }

    public boolean knows(int a, int b) {
        return (_graph[a][b] == 1);
    }

    public int findCelebrity(int n) {
        if (n == 1)
            return 0;
        int p1 = 0, p2 = 1, last = p2;
        while (last < n) {
            boolean know12 = knows(p1, p2);
            boolean know21 = knows(p2, p1);
            if (know12 == know21) {
                // If both know each other, or neither knows either other, neither is celebrity.
                p1 = ++last;
                p2 = ++last;
                continue;
            }
            if (know12) {
                // p1 knows p2 but p2 doesn't know p1, p2 could be celebrity
                p1 = p2;
                p2 = ++last;
            } else {
                // p2 knows p1 but p1 doesn't know p2, p1 could be celebrity
                p2 = ++last;
            }
        }
        if (p1 < n) {
            // Verify p1 is celebrity
            boolean isCelebrity = true;
            for (int i = 0; i < n && isCelebrity; ++i) {
                if (i == p1)
                    continue;
                isCelebrity &= (!knows(p1, i) && knows(i, p1));
            }
            if (isCelebrity)
                return p1;
        }
        return -1;
    }
}
