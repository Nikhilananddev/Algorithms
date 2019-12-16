package com.rainz;

public class FriendCircles {
    public static void test(String args[]) {
        int[][] input1 = {{1,1,0}, {1,1,0}, {0,0,1}};
        System.out.println(findCircleNum(input1));
        int[][] input2 = {{1,1,0}, {1,1,1}, {0,1,1}};
        System.out.println(findCircleNum(input2));
    }

    private static void dfs(int ppl, int[][] M, boolean[] visited) {
        if (visited[ppl])
            return;
        visited[ppl] = true;
        for (int i = 0; i < M.length; ++i) {
            if (ppl != i && M[ppl][i] == 1)
                dfs(i, M, visited);
        }
    }

    public static int findCircleNum(int[][] M) {
        int numPpl = M.length;
        if (numPpl == 0)
            return 0;

        int numCircle = 0;
        boolean[] visited = new boolean[numPpl];
        for (int i = 0; i < numPpl; ++i) {
            if (visited[i])
                continue;
            dfs(i, M, visited);
            ++numCircle;
        }
        return numCircle;
    }
}
