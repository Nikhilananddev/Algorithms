package com.rainz;

import com.rainz.Main.ListNode;

/**
 * Created by Yu on 1/25/2015.
 */
public class ReverseNodesinkGroup {
    public static void test(String args[]) {
        ListNode test = ListNode.buildListFromRange(1, 10);
        ListNode.printList(reverseKGroup(test, 3));
        test = ListNode.buildListFromRange(1, 11);
        ListNode.printList(reverseKGroup(test, 4));
        test = ListNode.buildListFromRange(1, 1);
        ListNode.printList(reverseKGroup(test, 2));
        test = ListNode.buildListFromRange(1, 2);
        ListNode.printList(reverseKGroup(test, 2));
        test = ListNode.buildListFromRange(1, 16);
        ListNode.printList(reverseKGroup(test, 4));
        test = ListNode.buildListFromRange(1, 14);
        ListNode.printList(reverseKGroup(test, 1));
        test = ListNode.buildListFromRange(1, 25);
        ListNode.printList(reverseKGroup(test, 7));
    }

    public static ListNode reverseLL(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        ListNode newHead = null;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            newHead = curr;
            curr = next;
        }
        return newHead;
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        ListNode p1 = head;

        while (p1 != null) {
            ListNode p2 = p1;
            int groupCount = 1;
            for (int i = 0; i < k-1 && p2.next != null; ++i) {
                p2 = p2.next;
                ++groupCount;
            }
            if (groupCount < k) {
                prev.next = p1;
                break; // we are done, don't reverse the last group
            }
            ListNode next = p2.next;
            p2.next = null;
            prev.next = reverseLL(p1);
            p1.next = null;
            // Set up for next iteration
            prev = p1;
            p1 = next;
        }
        return dummy.next;
    }
}
