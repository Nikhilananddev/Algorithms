package com.rainz;

/*
 * Design a hit counter which counts the number of hits received in the past 5 minutes.
 * Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.
 * It is possible that several hits arrive roughly at the same time.
 */
public class DesignHitCounter {
    public static void test(String args[]) {
        DesignHitCounter counter = new DesignHitCounter();
        counter.hit(1);
        counter.hit(2);
        counter.hit(3);
        System.out.println(counter.getHits(4));
        counter.hit(300);
        System.out.println(counter.getHits(300));
        System.out.println(counter.getHits(301));
    }

    /*
     * Use a circular buffer of size 300. Each slot stores timestamp & count;
     * No need to keep track of wrap-around. If new timestamp differs, we know it has wrapped around.
     */

    final static int DURATION = 5 * 60; // 5 minutes
    int[][] _ts = new int[DURATION][2]; // timestamp and count;

    /** Initialize your data structure here. */
    public DesignHitCounter() {

    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
        public void hit(int timestamp) {
            int idx = timestamp % DURATION;
            if (_ts[idx][0] != timestamp) {
                // Wrapped around, this is a new timestamp
                _ts[idx][0] = timestamp;
                _ts[idx][1] = 1;
            } else {
                // Same timestamp, increase count
                ++_ts[idx][1];
            }
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        int sum = 0;
        for (int i = 0; i < _ts.length; ++i) {
            if (timestamp - _ts[i][0] < DURATION)
                sum += _ts[i][1];
        }
        return sum;
    }
}
