package com.rainz;

/*
 * Implement a SnapshotArray that supports the following interface:
 * SnapshotArray(int length) initializes an array-like data structure with the given length.  Initially, each element equals 0.
 * void set(index, val) sets the element at the given index to be equal to val.
 * int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
 * int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id
 */

import java.util.ArrayList;
import java.util.List;

public class SnapshotArray {
    public static void test(String args[]) {
        SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
        snapshotArr.set(0,5);  // Set array[0] = 5
        snapshotArr.snap();  // Take a snapshot, return snap_id = 0
        snapshotArr.set(0,6);
        System.out.println(snapshotArr.get(0,0));  // Get the value of array[0] with snap_id = 0, return 5
    }

    private List<int[]>[] values;
    private int currSnap = 0;

    public SnapshotArray(int length) {
        values = new List[length];
        for (int i = 0; i < length; ++i) {
            values[i] = new ArrayList<>();
            int[] zero = {0, 0}; // snap_id, value
            values[i].add(zero);
        }
    }

    public void set(int index, int val) {
        List<int[]> l = values[index];
        int[] lastEntry = l.get(l.size()-1);
        if (currSnap == lastEntry[0])
            lastEntry[1] = val;
        else {
            int[] entry = {currSnap, val};
            l.add(entry);
        }
    }

    public int snap() {
        return currSnap++;
    }

    public int get(int index, int snap_id) {
        List<int[]> l = values[index];
        int lo = 0, hi = l.size()-1;
        int snap_val = 0;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (l.get(mid)[0] <= snap_id) {
                snap_val = l.get(mid)[1];
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return snap_val;
    }
}
