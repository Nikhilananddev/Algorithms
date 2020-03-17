package com.rainz;

/*
 * In a town, there are N people labelled from 1 to N.  There is a rumor that one of these people is secretly the town judge.
 * If the town judge exists, then:
 * The town judge trusts nobody.
 * Everybody (except for the town judge) trusts the town judge.
 * There is exactly one person that satisfies properties 1 and 2.
 * You are given trust, an array of pairs trust[i] = [a, b] representing that the person labelled a trusts the person labelled b.
 * If the town judge exists and can be identified, return the label of the town judge.  Otherwise, return -1.
 */
public class FindtheTownJudge {
    public static void test(String args[]) {
        int[][] trust1 = {{1,2}};
        System.out.println(findJudge(2, trust1));
        int[][] trust2 = {{1,3},{2,3}};
        System.out.println(findJudge(3, trust2));
        int[][] trust3 = {{1,3},{2,3},{3,1}};
        System.out.println(findJudge(3, trust3));
        int[][] trust4 = {{1,2},{2,3}};
        System.out.println(findJudge(3, trust4));
        int[][] trust5 = {{1,3},{1,4},{2,3},{2,4},{4,3}};
        System.out.println(findJudge(4, trust5));
    }
    public static int findJudge(int N, int[][] trust) {
        if (N == 1)
            return 1;
        int p1 = 1, p2 = 2, last = 2;
        boolean[][] trustMap = new boolean[N+1][N+1];
        for (int[] t: trust)
            trustMap[t[0]][t[1]] = true;
        while (last <= N) {
            boolean trust12 = trustMap[p1][p2];
            boolean trust21 = trustMap[p2][p1];
            if (trust12 == trust21) {
                // If both trust each other, or neither trust either other, neither is judge.
                p1 = ++last;
                p2 = ++last;
                continue;
            }
            if (trust12) {
                // p1 trust p2 but p2 doesn't trust p1, p2 could be judge
                p1 = p2;
                p2 = ++last;
            } else {
                // p2 trust p1 but p1 doesn't trust p2, p1 could be judge
                p2 = ++last;
            }
        }
        if (p1 <= N) {
            // Verify p1 is judge
            boolean isJudge = true;
            for (int i = 1; i <= N && isJudge; ++i) {
                if (i == p1)
                    continue;
                isJudge &= (!trustMap[p1][i] && trustMap[i][p1]);
            }
            if (isJudge)
                return p1;
        }
        return -1;
    }
}
