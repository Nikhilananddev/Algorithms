package com.rainz;

import com.rainz.Main.ListNode;

/**
 * Created by Yu on 8/21/2016.
 */
public class ReverseLinkedListII {
    public static void test(String args[]) {
        int[] input = {1,2,3,4,5};
        ListNode l = ListNode.buildList(input);
        ListNode.printList(reverseBetween(l, 2, 4));
    }

    public static ListNode reverseBetween(ListNode head, int m, int n) {
        int idx = 0;
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode revHead = null; // the node before the list being reversed
        ListNode node = dummy;
        // Scan until we find the start of the section being reversed
        while (node != null && revHead == null) {
            if (idx == m - 1) {
                revHead = node;
            }
            node = node.next;
            ++idx;
        }
        if (revHead == null) {
            // m is out of range
            return head;
        }
        // Start reversing
        ListNode firstRev = node;
        ListNode prev = null;
        while (node != null) {
            ListNode nextNode = node.next;
            node.next = prev;
            if (idx == n) {
                firstRev.next = nextNode;
                revHead.next = node;
                break;
            }
            prev = node;
            node = nextNode;
            ++idx;
        }
        // Note: this can't handle the case where n > length of the list.
        return dummy.next;
    }
}
