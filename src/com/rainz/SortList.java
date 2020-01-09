package com.rainz;

import com.rainz.Main.ListNode;

/*
 * Sort a linked list in O(n log n) time using constant space complexity.
 */
public class SortList {
    public static void test(String args[]) {
        int[] input1 = {4,2,1,3};
        ListNode.printList(sortList(ListNode.buildList(input1)));
        int[] input2 = {-1,5,3,4,0};
        ListNode.printList(sortList(ListNode.buildList(input2)));
    }

    /* Split list, then merge sort */
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        // Now the list has at least 2 elements.
        ListNode p1 = head, p2 = head;
        /* Find mid point of list using slow-fast ptr method */
        ListNode prev = null;
        while (p2 != null) {
            p2 = p2.next;
            if (p2 != null) {
                prev = p1;
                p1 = p1.next;
                p2 = p2.next;
            }
        }
        prev.next = null; // terminate first half of list
        // Now p1 points to head of second half
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(p1);
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                tail.next = l2;
                break;
            }
            if (l2 == null) {
                tail.next = l1;
                break;
            }
            ListNode next;
            if (l1.val <= l2.val) {
                next = l1;
                l1 = l1.next;
            } else {
                next = l2;
                l2 = l2.next;
            }
            tail.next = next;
            tail = next;
            tail.next = null;
        }

        return dummy.next;
    }
}
