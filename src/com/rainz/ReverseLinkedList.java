package com.rainz;

import com.rainz.Main.ListNode;

/*
 * Reverse a singly linked list.
 */

public class ReverseLinkedList {
    public static void test(String args[]) {
        System.out.println(reverseList(ListNode.buildListFromRange(1,5)));
    }
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
