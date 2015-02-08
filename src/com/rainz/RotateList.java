package com.rainz;

/**
 * Created by Yu on 2/8/2015.
 */
public class RotateList {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }

        public static void printList(ListNode n) {
            while (n != null) {
                System.out.print("" + n.val + " ");
                n = n.next;
            }
            System.out.println();
        }

        public static ListNode buildList(int count) {
            ListNode head = null;
            ListNode tail = null;
            for (int i = 1; i <= count; ++i) {
                ListNode curr = new ListNode(i);
                if (head == null)
                    head = curr;
                else
                    tail.next = curr;
                tail = curr;
            }
            return head;
        }

        public static ListNode buildList(int[] array) {
            ListNode head = null;
            ListNode tail = null;
            for (int i = 0; i < array.length; ++i) {
                ListNode curr = new ListNode(array[i]);
                if (head == null)
                    head = curr;
                else
                    tail.next = curr;
                tail = curr;
            }
            return head;
        }
    }

    public static void test(String args[]) {
        ListNode test = ListNode.buildList(5);
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
