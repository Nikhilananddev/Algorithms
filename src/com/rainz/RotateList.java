package com.rainz;

import com.rainz.Main.ListNode;

/**
 * Created by Yu on 2/8/2015.
 */
public class RotateList {
    public static void test(String args[]) {
        ListNode test = ListNode.buildListFromRange(1, 5);
        ListNode.printList(rotateRight(test, 2));
    }

    public static ListNode rotateRight(ListNode head, int n) {
        if (head == null)
            return null;
        int len = 0;
        ListNode tail = null;
        for (ListNode curr = head; curr != null; curr = curr.next) {
            if (curr.next == null)
                tail = curr;
            ++len;
        }
        n %= len;
        if (n == 0)
            return head;
        int moveCount = len - n;
        ListNode curr = head;
        for (int i = 1; i < moveCount; ++i)
            curr = curr.next;
        // Now curr points to the last element of the new list;
        ListNode newHead = curr.next;
        curr.next = null;
        tail.next = head;

        return newHead;
    }
}
