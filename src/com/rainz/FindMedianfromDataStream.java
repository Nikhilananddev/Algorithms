package com.rainz;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * Design a data structure that supports the following two operations:
 * void addNum(int num) - Add a integer number from the data stream to the data structure.
 * double findMedian() - Return the median of all elements so far.
 */
public class FindMedianfromDataStream {
    public static void test(String args[]) {
        FindMedianfromDataStream m = new FindMedianfromDataStream();
        m.addNum(1);
        m.addNum(2);
        System.out.println(m.findMedian()); // -> 1.5
        m.addNum(3);
        System.out.println(m.findMedian()); // -> 2
    }

    PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Comparator.reverseOrder());
    PriorityQueue<Integer> minPQ = new PriorityQueue<>();

    public FindMedianfromDataStream() {

    }

    public void addNum(int num) {
        if (maxPQ.isEmpty() || maxPQ.peek() > num)
            maxPQ.offer(num);
        else
            minPQ.offer(num);
        // Balance PQs
        if (maxPQ.size() >= minPQ.size()) {
            while (maxPQ.size() > minPQ.size()+1)
                minPQ.offer(maxPQ.poll());
        } else {
            while (maxPQ.size() < minPQ.size())
                maxPQ.offer(minPQ.poll());
        }
    }

    public double findMedian() {
        if ((maxPQ.size() + minPQ.size()) % 2 == 1)
            return maxPQ.peek();
        else
            return ((double)maxPQ.peek() + minPQ.peek()) / 2.0;
    }
}
