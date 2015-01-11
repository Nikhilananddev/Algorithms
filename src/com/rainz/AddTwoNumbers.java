package com.rainz;

/**
 * Created by Yu on 1/11/2015.
 */

 public class AddTwoNumbers {

    protected static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    private static ListNode buildList(int val) {
        ListNode result = new ListNode(0); // dummy
        if (val == 0)
            return result;
        ListNode curr = result;
        while (val > 0) {
            curr.next = new ListNode(val % 10);
            curr = curr.next;
            val /= 10;
        }
        return result.next;
    }

    private static void printList(ListNode l) {
        if (l != null) {
            System.out.print(l.val);
            l = l.next;
        }
        while (l != null) {
            System.out.print("->");
            System.out.print(l.val);
            l = l.next;
        }
        System.out.println();
    }

    public static void test(String args[]) {
        ListNode l1 = buildList(123);
        ListNode l2 = buildList(798);
        printList(addTwoNumbers(l1, l2));
        l1 = buildList(189);
        l2 = buildList(971);
        printList(addTwoNumbers(l1, l2));
        l1 = buildList(0);
        l2 = buildList(0);
        printList(addTwoNumbers(l1, l2));
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
