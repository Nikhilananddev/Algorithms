package com.rainz;

/**
 * Created by Yu on 1/24/2015.
 */
public class RemoveNthNodeFromEndofList {
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
    }

     public static void test(String args[]) {
         ListNode test = ListNode.buildList(3);
         ListNode removed = removeNthFromEnd(test, 1);
         ListNode.printList(removed);
         test = ListNode.buildList(3);
         removed = removeNthFromEnd(test, 2);
         ListNode.printList(removed);
         test = ListNode.buildList(3);
         removed = removeNthFromEnd(test, 3);
         ListNode.printList(removed);
         test = ListNode.buildList(3);
         removed = removeNthFromEnd(test, 4);
         ListNode.printList(removed);
         test = ListNode.buildList(1);
         removed = removeNthFromEnd(test, 1);
         ListNode.printList(removed);
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode node1 = head, node2 = head;
        // Move node2 n+1 nodes ahead of node1
        while (n >= 0) {
            if (node2 != null)
                node2 = node2.next;
            else {
                if (n > 0)
                    return null; // not enough nodes
                return head.next; // There are only n nodes, so remove head
            }
            --n;
        }
        // Move node1 and node2 forward synchronously
        // The goal is to let node1 point the *prev* node of the one we are deleting
        while (node2 != null) {
            node2 = node2.next;
            node1 = node1.next;
        }
        node1.next = node1.next.next;
        return head;
    }
}
