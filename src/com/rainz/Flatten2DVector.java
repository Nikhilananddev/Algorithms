package com.rainz;

/*
 * Design and implement an iterator to flatten a 2d vector. It should support the following operations: next and hasNext.
 */
public class Flatten2DVector {
    public static void test(String args[]) {
        int[][] input1 = {{1,2},{3},{4}};
        Flatten2DVector iterator = new Flatten2DVector(input1);

        System.out.println(iterator.next()); // return 1
        System.out.println(iterator.next()); // return 2
        System.out.println(iterator.next()); // return 3
        System.out.println(iterator.hasNext()); // return true
        System.out.println(iterator.hasNext()); // return true
        System.out.println(iterator.next()); // return 4
        System.out.println(iterator.hasNext()); // return false
    }

    private int _r = 0;
    private int _c = 0;
    private int[][] _v;
    public Flatten2DVector(int[][] v) {
        _v = v;
        while (_r < _v.length && _v[_r].length == 0)
            ++_r; // skip blank rows
    }

    public int next() {
        int ret = _v[_r][_c];
        if (++_c >= _v[_r].length) {
            // Move to next non-blank line
            do {
                ++_r;
            } while (_r < _v.length && _v[_r].length == 0);
            _c = 0;
        }
        return ret;
    }

    public boolean hasNext() {
        return _r < _v.length;
    }
}
