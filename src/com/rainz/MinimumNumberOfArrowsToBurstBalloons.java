package com.rainz;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class MinimumNumberOfArrowsToBurstBalloons {
    public static void test(String args[]) {
        int[][] input = {{10,16}, {2,8}, {1,6}, {7,12}};
        System.out.println(minimumNumberOfArrowsToBurstBalloons(input));
    }
    static class Segment {
        public int x;
        public int y;
        public boolean burst;
        public Segment(int xx, int yy) { x = xx; y = yy; burst = false; }
        public int getX() { return x; }
        public int getY() { return y; }
    }

    public static int minimumNumberOfArrowsToBurstBalloons(int[][] points) {
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
