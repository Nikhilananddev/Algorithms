package com.rainz;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import com.rainz.Main.ListNode;

/**
 * Created by Yu on 1/24/2015.
 */
public class MergekSortedLists {
    public static void test(String args[]) {
        List<ListNode> lists = new ArrayList<ListNode>();
        int[] arr1 = {3, 5, 7, 23, 113};
        int[] arr2 = {1, 2, 17, 21, 23, 142};
        int[] arr3 = {9, 29, 331, 423, 531, 612, 744, 888, 911, 1002, 1113};
        lists.add(ListNode.buildList(arr1));
        lists.add(ListNode.buildList(arr2));
        lists.add(ListNode.buildList(arr3));
        ListNode result = mergeKLists(lists);
        ListNode.printList(result);
    }

    public static ListNode mergeKLists(List<ListNode> lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(16, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return Integer.compare(o1.val, o2.val);
            }
        });
        // Insert the head of every list
        for (ListNode l: lists) {
            if (l == null)
                continue;
            pq.add(l);
        }
        // Get the smallest element and use it as head, insert its next to pq
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (!pq.isEmpty()) {
            ListNode max = pq.poll();
            if (max.next != null)
                pq.add(max.next);
            max.next = null;
            tail.next = max;
            tail = max;
        }

        return dummy.next;
    }
}
