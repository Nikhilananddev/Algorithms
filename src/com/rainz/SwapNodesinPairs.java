package com.rainz;

/**
 * Created by Yu on 1/24/2015.
 */
public class SwapNodesinPairs {
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
        ListNode test = ListNode.buildList(10);
        ListNode.printList(swapPairs(test));
        test = ListNode.buildList(11);
        ListNode.printList(swapPairs(test));
        test = ListNode.buildList(1);
        ListNode.printList(swapPairs(test));
        test = ListNode.buildList(2);
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
