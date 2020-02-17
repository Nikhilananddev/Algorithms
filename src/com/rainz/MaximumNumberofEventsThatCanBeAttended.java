package com.rainz;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
 * Given an array of events where events[i] = [startDayi, endDayi]. Every event i starts at startDayi and ends at endDayi.
 * You can attend an event i at any day d where startTimei <= d <= endTimei. Notice that you can only attend one event at any time d.
 * Return the maximum number of events you can attend.
 */
public class MaximumNumberofEventsThatCanBeAttended {
    public static void test(String args[]) {
        int[][] events1 = {{1,2},{2,3},{3,4},{1,2}};
        System.out.println(maxEvents(events1));
        int[][] events2 = {{1,4},{4,4},{2,2},{3,4},{1,1}};
        System.out.println(maxEvents(events2));
        int[][] events3 = {{1,100000}};
        System.out.println(maxEvents(events3));
        int[][] events4 = {{1,1},{1,2},{1,3},{1,4},{1,5},{1,6},{1,7}};
        System.out.println(maxEvents(events4));
        int[][] events5 = {{25,26},{19,19},{9,13},{16,17},{17,18},{20,21},
                           {22,25},{11,12},{19,23},{7,9},{1,1},{21,23},{14,14},
                           {4,7},{16,16},{24,28},{16,18},{4,5},{18,20},{16,16},{25,26}};
        System.out.println(maxEvents(events5));
    }

    /*
     * Greedy: always take the event which starts and ends sooner
     */
    public static int maxEvents(int[][] events) {
        if (events.length == 0)
            return 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y)->(x[0] != y[0] ? x[0]-y[0] : x[1]-y[1]));
        for (int[] event: events) {
            pq.offer(event);
        }
        int ans = 0;
        int currDay = -1;
        while (!pq.isEmpty()) {
            int[] event = pq.poll();
            if (currDay >= 0) {
                if (event[0] < currDay) {
                    // Event started before currDay, but we haven't attended it
                    if (event[1] < currDay)
                        continue; // this event already is over
                    // This event is still going on. Adjust start date and put back to pq for re-selection.
                    // This is because there could be another event with later start date but earlier end date
                    event[0] = currDay;
                    pq.offer(event);
                    continue;
                }
            }
            // Go to this event on day event[0]
            ++ans;
            currDay = event[0] + 1; // move on to next day
        }
        return ans;
    }
}
