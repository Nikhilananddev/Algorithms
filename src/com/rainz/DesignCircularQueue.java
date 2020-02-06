package com.rainz;

/*
 * Design your implementation of the circular queue. The circular queue is a linear data structure in which the operations are performed based on FIFO (First In First Out) principle and the last position is connected back to the first position to make a circle. It is also called "Ring Buffer".
 * One of the benefits of the circular queue is that we can make use of the spaces in front of the queue. In a normal queue, once the queue becomes full, we cannot insert the next element even if there is a space in front of the queue. But using the circular queue, we can use the space to store new values.
 * Your implementation should support following operations:
 * MyCircularQueue(k): Constructor, set the size of the queue to be k.
 * Front: Get the front item from the queue. If the queue is empty, return -1.
 * Rear: Get the last item from the queue. If the queue is empty, return -1.
 * enQueue(value): Insert an element into the circular queue. Return true if the operation is successful.
 * deQueue(): Delete an element from the circular queue. Return true if the operation is successful.
 * isEmpty(): Checks whether the circular queue is empty or not.
 * isFull(): Checks whether the circular queue is full or not.
 */

public class DesignCircularQueue {
    public static void test(String args[]) {
        DesignCircularQueue circularQueue = new DesignCircularQueue(3); // set the size to be 3
        System.out.println(circularQueue.enQueue(1));  // return true
        System.out.println(circularQueue.enQueue(2));  // return true
        System.out.println(circularQueue.enQueue(3));  // return true
        System.out.println(circularQueue.enQueue(4));  // return false, the queue is full
        System.out.println(circularQueue.Rear());  // return 3
        System.out.println(circularQueue.isFull());  // return true
        System.out.println(circularQueue.deQueue());  // return true
        System.out.println(circularQueue.enQueue(4));  // return true
        System.out.println(circularQueue.Rear());  // return 4
    }

    /*
     * This solution uses sentinel method to detect empty/full
     */

    private int[] _data = null;
    private int _start = 0;
    private int _end = 0;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public DesignCircularQueue(int k) {
        _data = new int[k+1];
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (isFull())
            return false;
        _data[_end] = value;
        _end = (_end + 1) % _data.length;
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (isEmpty())
            return false;
        _start = (_start + 1) % _data.length;
        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if (isEmpty())
            return -1;
        return _data[_start];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if (isEmpty())
            return -1;
        return _data[ (_end + _data.length - 1) % _data.length];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return _start == _end;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return (_end + 1) % _data.length == _start;
    }
}
