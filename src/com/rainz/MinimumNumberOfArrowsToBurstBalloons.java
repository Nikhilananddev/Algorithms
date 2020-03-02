package com.rainz;

import java.util.*;

public class MinimumNumberOfArrowsToBurstBalloons {
    public static void test(String args[]) {
        int[][] input = {{10,16}, {2,8}, {1,6}, {7,12}};
        System.out.println(minimumNumberOfArrowsToBurstBalloons(input));
    }

    public static int minimumNumberOfArrowsToBurstBalloons(int[][] points) {
        if (points.length == 0)
            return 0;
        int ans = 1; // first shot
        Arrays.sort(points, (x, y) -> x[0] != y[0] ? x[0] - y[0] : x[1] - y[1]);
        int end = points[0][1];
        for (int i = 1; i < points.length; ++i) {
            int[] p = points[i];
            if (p[0] <= end)
                end = Math.min(end, p[1]); // can be included in first shot; might have to shrink end
            else {
                ++ans; // next shot
                end = p[1];
            }
        }
        return ans;
    }
    static class Segment {
        public int x;
        public int y;
        public boolean burst;
        public Segment(int xx, int yy) { x = xx; y = yy; burst = false; }
        public int getX() { return x; }
        public int getY() { return y; }
    }

    public static int minimumNumberOfArrowsToBurstBalloonsOldPass(int[][] points) {
        List<Segment> byStart = new ArrayList<>(); // actually byStart is not needed
        List<Segment> byEnd = new ArrayList<>();
        for (int[] p: points) {
            Segment point = new Segment(p[0], p[1]);
            byStart.add(point);
            byEnd.add(point);
        }
        Collections.sort(byStart, Comparator.comparing(Segment::getX));
        Collections.sort(byEnd, Comparator.comparing(Segment::getY));

        int result = 0;
        int startIdx = 0;
        for (Segment p: byEnd) {
            if (p.burst)
                continue;
            while (startIdx < byStart.size() && byStart.get(startIdx).x <= p.y) {
                byStart.get(startIdx).burst = true;
                ++startIdx;
            }
            ++result;
        }
        return result;
    }
}
