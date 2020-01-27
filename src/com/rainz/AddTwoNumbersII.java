package com.rainz;

import com.rainz.Main.ListNode;

import java.util.ArrayList;
import java.util.List;

/*
 * You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
 */

public class AddTwoNumbersII {
    public static void test(String args[]) {
        int[] a1 = {7,2,4,3};
        int[] b1 = {5,6,4};
        System.out.println(addTwoNumbers(ListNode.buildList(a1), ListNode.buildList(b1)));
        int[] a2 = {9,8,9,9};
        int[] b2 = {1,0,2};
        System.out.println(addTwoNumbers(ListNode.buildList(a2), ListNode.buildList(b2)));
        int[] a3 = {0};
        int[] b3 = {0};
        System.out.println(addTwoNumbers(ListNode.buildList(a3), ListNode.buildList(b3)));
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int len1 = 0, len2 = 0;
        ListNode curr = l1;
        while (curr != null) {
            ++len1;
            curr = curr.next;
        }
        curr = l2;
        while (curr != null) {
            ++len2;
            curr = curr.next;
        }
        int len = Math.max(len1, len2);
        ListNode dummy = new ListNode(0);
        curr = dummy;
        List<ListNode> prevs = new ArrayList<>();
        prevs.add(dummy);
        for (; len > 0; --len) {
            int d1 = 0;
            if (len <= len1) {
                d1 = l1.val;
                l1 = l1.next;
            }
            int d2 = 0;
            if (len <= len2) {
                d2 = l2.val;
                l2 = l2.next;
            }
            int sum = d1 + d2;
            ListNode n = new ListNode(sum%10);
            int idx = prevs.size() - 1;
            while (sum > 9) {
                ListNode prev = prevs.get(idx);
                sum = prev.val + 1;
                prev.val = sum % 10;
                --idx;
            }
            prevs.add(n);
            curr.next = n;
            curr = n;
        }
        return dummy.val > 0 ? dummy : dummy.next;
    }
}
