package com.rainz;

import com.rainz.Main.ListNode;

import java.util.List;

/*
 * Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.
 * You may assume the integer do not contain any leading zero, except the number 0 itself.
 * The digits are stored such that the most significant digit is at the head of the list.
 */

public class PlusOneLinkedList {
    public static void test(String args[]) {
        int[] input1 = {1,2,3};
        ListNode list1 = ListNode.buildList(input1);
        System.out.println(plusOne(list1));
    }

    private static int helper(ListNode node) {
        if (node == null)
            return 1; // need to add 1
        int carry = helper(node.next);
        node.val += carry;
        int ret = 0;
        if (node.val > 9) {
            node.val = 0;
            ret = 1;
        }
        return ret;
    }

    public static ListNode plusOne(ListNode head) {
        int carry = helper(head);
        ListNode ans;
        if (carry > 0) {
            ans = new ListNode(1);
            ans.next = head;
        } else
            ans = head;
        return ans;
    }
}
