package com.rainz;

import com.rainz.Main.ListNode;

/**
 * Created by Yu on 1/24/2015.
 */
public class SwapNodesinPairs {
    public static void test(String args[]) {
        ListNode test = ListNode.buildListFromRange(1, 10);
        ListNode.printList(swapPairs(test));
        test = ListNode.buildListFromRange(1, 11);
        ListNode.printList(swapPairs(test));
        test = ListNode.buildListFromRange(1,1);
        ListNode.printList(swapPairs(test));
        test = ListNode.buildListFromRange(1,2);
        ListNode.printList(swapPairs(test));
    }

    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        ListNode p1 = head;

        while (p1 != null) {
            ListNode p2 = p1.next;
            if (p2 == null) {
                prev.next = p1;
                break;
            }
            ListNode next = p2.next;
            prev.next = p2;
            p2.next = p1;
            p1.next = null;
            prev = p1;
            p1 = next;
        }
        return dummy.next;
    }
}
