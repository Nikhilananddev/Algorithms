package com.rainz;

import java.util.LinkedList;
import java.util.Queue;

/*
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 */
public class MovingAveragefromDataStream {
    public static void test(String args[]) {
        MovingAveragefromDataStream m = new MovingAveragefromDataStream(3);
        System.out.println(m.next(1));
        System.out.println(m.next(10));
        System.out.println(m.next(3));
        System.out.println(m.next(5));
    }

    private int _sum = 0;
    private Queue<Integer> _q = new LinkedList<>();
    private int _size = 0;

    /**
     * Initialize your data structure here.
     */
    public MovingAveragefromDataStream(int size) {
        _size = size;
    }

    public double next(int val) {
        _sum += val;
        _q.add(val);
        if (_q.size() > _size)
            _sum -= _q.remove();
        return (double) _sum / _q.size();
    }
}