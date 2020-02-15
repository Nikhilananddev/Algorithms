package com.rainz;

/*
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
 * (Here, the distance between two points on a plane is the Euclidean distance.)
 * You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
 */
public class KClosestPointstoOrigin {
    public static void test(String args[]) {
        int[][] points1 = {{1,3},{-2,2}};
        Main.printArray2D(kClosest(points1, 1));
        int[][] points2 = {{3,3},{5,-1},{-2,4}};
        Main.printArray2D(kClosest(points2, 2));
        int[][] points3 = {{-6,-8},{4,-2},{4,5},{5,7},{3,1}};
        Main.printArray2D(kClosest(points3, 4));
    }

    private static void swapPoints(int[][] pd, int i, int j) {
        for (int idx = 0; idx < 3; ++idx) {
            int tmp = pd[i][idx];
            pd[i][idx] = pd[j][idx];
            pd[j][idx] = tmp;
        }
    }
    // Returns the index after the kth element
    private static int findKth(int[][] pd, int start, int end, int k) {
        int pivot = pd[start][2];
        int lt = start + 1;
        int gt = end - 1;
        while (lt <= gt) {
            if (pd[lt][2] <= pivot)
                ++lt;
            else
                swapPoints(pd, lt, gt--);
        }
        swapPoints(pd, start, lt-1);
        int count = lt - start;
        if (count < k)
            return findKth(pd, lt, end,k - count);
        else if (count > k)
            return findKth(pd, start, lt, k);
        return lt;
    }
    public static int[][] kClosest(int[][] points, int K) {
        int N = points.length;
        K = Math.min(K, N);
        int[][] pointDists = new int[N][3];
        for (int i = 0; i < N; ++i) {
            int x = points[i][0], y = points[i][1];
            pointDists[i][0] = x;
            pointDists[i][1] = y;
            pointDists[i][2] = x*x + y*y;
        }
        findKth(pointDists, 0, N, K);
        int[][] ans = new int[K][2];
        for (int i = 0; i < K; ++i) {
            ans[i][0] = pointDists[i][0];
            ans[i][1] = pointDists[i][1];
        }
        return ans;
    }
}
