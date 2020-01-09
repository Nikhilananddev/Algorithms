package com.rainz;

import com.rainz.Main.ListNode;

/*
 * Sort a linked list using insertion sort.
 */

public class InsertionSortList {
    public static void test(String args[]) {
        int[] input1 = {4,2,1,3};
        ListNode.printList(insertionSortList(ListNode.buildList(input1)));
        int[] input2 = {-1,5,3,4,0};
        ListNode.printList(insertionSortList(ListNode.buildList(input2)));
    }

    /* Recursive */
    public static ListNode insertionSortList(ListNode head) {
        if (head == null)
            return null;
        ListNode next = insertionSortList(head.next);
        ListNode curr = next;
        ListNode prev = null;
        while (curr != null && head.val > curr.val) {
            prev = curr;
            curr = curr.next;
        }
        if (curr == next) {
            // head is smallest
            head.next = curr;
            return head;
        }
        if (curr == null) {
            // head is largest
            prev.next = head;
            head.next = null;
            return next;
        }
        // Insert head between prev and curr
        prev.next = head;
        head.next = curr;
        return next;
    }

}
