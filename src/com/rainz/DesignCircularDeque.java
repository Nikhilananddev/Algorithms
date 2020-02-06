package com.rainz;

/*
 * Design your implementation of the circular double-ended queue (deque).
 * Your implementation should support following operations:
 * MyCircularDeque(k): Constructor, set the size of the deque to be k.
 * insertFront(): Adds an item at the front of Deque. Return true if the operation is successful.
 * insertLast(): Adds an item at the rear of Deque. Return true if the operation is successful.
 * deleteFront(): Deletes an item from the front of Deque. Return true if the operation is successful.
 * deleteLast(): Deletes an item from the rear of Deque. Return true if the operation is successful.
 * getFront(): Gets the front item from the Deque. If the deque is empty, return -1.
 * getRear(): Gets the last item from Deque. If the deque is empty, return -1.
 * isEmpty(): Checks whether Deque is empty or not.
 * isFull(): Checks whether Deque is full or not.
 */
public class DesignCircularDeque {
    public static void test(String args[]) {
        DesignCircularDeque circularDeque = new DesignCircularDeque(3); // set the size to be 3
        System.out.println(circularDeque.insertLast(1));			// return true
        System.out.println(circularDeque.insertLast(2));			// return true
        System.out.println(circularDeque.insertFront(3));			// return true
        System.out.println(circularDeque.insertFront(4));			// return false, the queue is full
        System.out.println(circularDeque.getRear());  			// return 2
        System.out.println(circularDeque.isFull());				// return true
        System.out.println(circularDeque.deleteLast());			// return true
        System.out.println(circularDeque.insertFront(4));			// return true
        System.out.println(circularDeque.getFront());			// return 4
    }

    private int[] _data = null;
    private int _start = 0;
    private int _end = 0;
    private boolean _empty = true;

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public DesignCircularDeque(int k) {
        _data = new int[k];
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if (isFull())
            return false;
        _start = (_start - 1 + _data.length) % _data.length;
        _data[_start] = value;
        _empty = false;
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if (isFull())
            return false;
        _data[_end] = value;
        _end = (_end + 1) % _data.length;
        _empty = false;
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if (isEmpty())
            return false;
        _start = (_start + 1) % _data.length;
        _empty = (_start == _end);
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (isEmpty())
            return false;
        _end = (_end + _data.length - 1) % _data.length;
        _empty = (_start == _end);
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        if (isEmpty())
            return -1;
        return _data[_start];
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if (isEmpty())
            return -1;
        return _data[ (_end + _data.length - 1) % _data.length];
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return _empty;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return !_empty && _start == _end;
    }
}
