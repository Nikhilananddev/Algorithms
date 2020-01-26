package com.rainz;

import java.util.PriorityQueue;
import java.util.Queue;

/*
 * Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * Your KthLargest class will have a constructor which accepts an integer k and an integer array nums, which contains initial elements from the stream. For each call to the method KthLargest.add, return the element representing the kth largest element in the stream.
 */
public class KthLargestElementinaStream {
    public static void test(String args[]) {
        int k = 3;
        int[] arr = {4,5,8,2};
        KthLargestElementinaStream kthLargest = new KthLargestElementinaStream(3, arr);
        System.out.println(kthLargest.add(3));   // returns 4
        System.out.println(kthLargest.add(5));   // returns 5
        System.out.println(kthLargest.add(10));  // returns 5
        System.out.println(kthLargest.add(9));   // returns 8
        System.out.println(kthLargest.add(4));   // returns 8
    }
    private Queue<Integer> pq = new PriorityQueue<>();
    private int kth = 1;
    public KthLargestElementinaStream(int k, int[] nums) {
        kth = k;
        for (int n: nums)
            add(n);
    }

    public int add(int val) {
        pq.add(val);
        while (pq.size() > kth) {
            pq.remove();
        }
        return pq.peek();
    }
}
