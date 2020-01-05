package com.rainz;

import com.rainz.Main.ListNode;

public class ReorderList {
    public static void test(String args[]) {
        int[] input1 = {1,2,3,4};
        ListNode l1 = ListNode.buildList(input1);
        reorderList(l1);
        ListNode.printList(l1);
        int[] input2 = {1,2,3,4,5};
        ListNode l2 = ListNode.buildList(input2);
        reorderList(l2);
        ListNode.printList(l2);
    }

    public static void reorderList(ListNode head) {
        if (head == null)
            return;
        // Use 2 ptrs to determine mid point of list
        ListNode p1 = head;
        ListNode p2 = head;
        ListNode l1End = null;
        while (p2 != null) {
            l1End = p1;
            p1 = p1.next;
            p2 = p2.next;
            if (p2 != null) {
                p2 = p2.next;
            }
        }
        l1End.next = null;
        ListNode prev = null;
        // p1 points at start of 2nd half
        // Now reverse linked list for p1
        while (p1 != null) {
            ListNode next = p1.next;
            p1.next = prev;
            prev = p1;
            p1 = next;
        }
        ListNode l2 = prev;
        prev = head;
        while (l2 != null) {
            // insert node from l2 into l1
            ListNode l1Next = prev.next;
            prev.next = l2;
            l2 = l2.next;
            prev.next.next = l1Next;
            if (l1Next != null)
                prev = l1Next;
        }
    }
}
