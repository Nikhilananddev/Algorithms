package com.rainz;

import com.rainz.Main.ListNode;

/**
 * Created by Yu on 3/7/2015.
 */
public class RemoveDuplicatesfromSortedList {
    public static void test(String args[]) {
        int[] tests = {1,1,1,2,3,3,4,4,5};
        ListNode l = ListNode.buildList(tests);
        ListNode answer = deleteDuplicates(l);
        ListNode.printList(answer);

    }

    public static ListNode deleteDuplicates(ListNode head) {
        ListNode answer = new ListNode(0); // dummy
        ListNode tail = answer;
        int prev = 0;
        for (ListNode curr = head; curr != null; curr = curr.next) {
            if (curr == head || curr.val != prev) {
                tail.next = curr;
                tail = curr;
                prev = curr.val;
            }
        }
        tail.next = null;
        return answer.next;
    }
}