package com.rainz;

/**
 * Created by Yu on 1/25/2015.
 */
public class ReverseNodesinkGroup {
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
        ListNode.printList(reverseKGroup(test, 3));
        test = ListNode.buildList(11);
        ListNode.printList(reverseKGroup(test, 4));
        test = ListNode.buildList(1);
        ListNode.printList(reverseKGroup(test, 2));
        test = ListNode.buildList(2);
        ListNode.printList(reverseKGroup(test, 2));
        test = ListNode.buildList(16);
        ListNode.printList(reverseKGroup(test, 4));
        test = ListNode.buildList(14);
        ListNode.printList(reverseKGroup(test, 1));
        test = ListNode.buildList(25);
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
