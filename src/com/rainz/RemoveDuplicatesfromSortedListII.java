package com.rainz;

import com.rainz.Main.ListNode;

/**
 * Created by Yu on 3/7/2015.
 */

public class RemoveDuplicatesfromSortedListII {
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
