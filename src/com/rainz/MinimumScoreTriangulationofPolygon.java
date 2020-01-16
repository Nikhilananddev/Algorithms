package com.rainz;

/*
 * Given N, consider a convex N-sided polygon with vertices labelled A[0], A[i], ..., A[N-1] in clockwise order.
 * Suppose you triangulate the polygon into N-2 triangles.  For each triangle, the value of that triangle is the product of the labels of the vertices, and the total score of the triangulation is the sum of these values over all N-2 triangles in the triangulation.
 * Return the smallest possible total score that you can achieve with some triangulation of the polygon.
 */
public class MinimumScoreTriangulationofPolygon {
    public static void test(String args[]) {
        int[] input1 = {1,2,3};
        System.out.println(minScoreTriangulation(input1));
        int[] input2 = {3,7,4,5};
        System.out.println(minScoreTriangulation(input2));
        int[] input3 = {1,3,1,4,1,5};
        System.out.println(minScoreTriangulation(input3));
    }

    /*
     * For two points i and j (i<j), pick a point m between them and form a triangle.
     * This partitions the polygon into 3 parts: left, triangle (i-m-j), and right.
     * Then score for left is f(i, m), score for right is f(m, j) .
     * So f(i, j) = min( f(i, m) + A[i]*A[m]*A[j] + f(m, j) ) for all m between i and j.
     */
    public static int minScoreTriangulation(int[] A) {
        int N = A.length;
        int[][] dp = new int[N][N]; // start, end (inclusive)

        for (int i = 0; i < N-2; ++i)
            dp[i][i+2] = A[i]*A[i+1]*A[i+2];
        // Make sure you compute dp for smaller length first!!
        for (int len = 3; len < N; ++len) {
            for (int i = 0; i + len < N; ++i) {
                int j = i + len;
                int min = Integer.MAX_VALUE;
                for (int mid = i+1; mid < j; ++mid) {
                    int sum =  dp[i][mid] + A[i]*A[mid]*A[j] + dp[mid][j];
                    if (sum < min)
                        min = sum;
                }
                dp[i][j] = min;
            }
        }

        return dp[0][N-1];
    }

}
