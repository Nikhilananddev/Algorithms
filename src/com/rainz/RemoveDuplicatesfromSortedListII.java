package com.rainz;

/**
 * Created by Yu on 3/7/2015.
 */
public class RemoveDuplicatesfromSortedListII {
    static class ListNode {
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
        int[] tests = {1,1,1,2,3,3,4,4,5};
        ListNode l = ListNode.buildList(tests);
        ListNode answer = deleteDuplicates(l);
        ListNode.printList(answer);
    }

    public static ListNode deleteDuplicates(ListNode head) {
        ListNode answer = new ListNode(0); // dummy node
        ListNode tail = answer;
        int prevVal = 0;
        ListNode distinct = null;
        for (ListNode curr = head; curr != null; curr = curr.next) {
            if (curr == head || curr.val != prevVal) {
                // curr is different from prev
                if (distinct != null) {
                    // Found distinct value
                    tail.next = distinct;
                    tail = distinct;
                }
                distinct = curr;
                prevVal = curr.val;
            }
            else {
                distinct = null; // found dup, don't output this
            }
        }
        tail.next = distinct;
        return answer.next;
    }
}
