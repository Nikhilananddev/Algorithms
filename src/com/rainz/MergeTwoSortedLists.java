package com.rainz;

/**
 * Created by Yu on 1/27/2015.
 */
public class MergeTwoSortedLists {
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
        int[] a1 = {1, 3, 5, 5, 9, 11, 13};
        int[] a2 = {2, 4, 11, 11, 12, 15, 17, 31, 34, 44};
        //int[] a1 = {1};
        //int[] a2 = {2};
        ListNode l1 = ListNode.buildList(a1);
        ListNode l2 = ListNode.buildList(a2);
        ListNode merge = mergeTwoLists(l1, l2);
        ListNode.printList(merge);
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;

            }
            tail = tail.next;
        }
        tail.next = l1 != null ? l1 : l2;

        return dummy.next;
    }
}
