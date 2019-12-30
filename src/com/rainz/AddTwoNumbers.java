package com.rainz;

/**
 * Created by Yu on 1/11/2015.
 */

import com.rainz.Main.ListNode;

 public class AddTwoNumbers {

    public static void test(String args[]) {
        ListNode l1 = ListNode.buildListFromInt(123);
        ListNode l2 = ListNode.buildListFromInt(798);
        ListNode.printList(addTwoNumbers(l1, l2));
        l1 = ListNode.buildListFromInt(189);
        l2 = ListNode.buildListFromInt(971);
        ListNode.printList(addTwoNumbers(l1, l2));
        l1 = ListNode.buildListFromInt(0);
        l2 = ListNode.buildListFromInt(0);
        ListNode.printList(addTwoNumbers(l1, l2));
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode curr1 = l1;
        ListNode curr2 = l2;
        int carry = 0;
        ListNode result = new ListNode(0); // dummy
        ListNode curr = result;
        while (curr1 != null || curr2 != null || carry != 0) {
            int val1 = curr1 != null ? curr1.val : 0;
            int val2 = curr2 != null ? curr2.val : 0;
            int val = val1 + val2 + carry;
            curr.next = new ListNode(val % 10);
            curr = curr.next;
            carry = val > 9 ? 1 : 0;
            if (curr1 != null)
                curr1 = curr1.next;
            if (curr2 != null)
                curr2 = curr2.next;
        }
        return result.next;
    }

}
