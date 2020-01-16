package com.rainz;

import com.rainz.Main.ListNode;

import java.util.HashMap;
import java.util.Map;

/*
 * Given the head of a linked list, we repeatedly delete consecutive sequences of nodes that sum to 0 until there are no such sequences.
 *  After doing so, return the head of the final linked list.  You may return any such answer.
 */

public class RemoveZeroSumConsecutiveNodesfromLinkedList {
    public static void test(String args[]) {
        int[] input1 = {1,2,-3,3,1};
        System.out.println(removeZeroSumSublists(ListNode.buildList(input1)));
        int[] input2 = {1,2,3,-3,4};
        System.out.println(removeZeroSumSublists(ListNode.buildList(input2)));
        int[] input3 = {1,2,3,-3,-2};
        System.out.println(removeZeroSumSublists(ListNode.buildList(input3)));
    }

    public static ListNode removeZeroSumSublists(ListNode head) {
        if (head == null)
            return null;
        Map<Integer, ListNode> sumMap = new HashMap<>();
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int sum = 0;
        sumMap.put(sum, dummy);
        ListNode curr = head;
        while (curr != null) {
            sum += curr.val;
            ListNode prevSumNode = sumMap.get(sum);
            if (prevSumNode != null) {
                ListNode rmNode = prevSumNode.next;
                int sm = sum;
                // Remove intermediate sums  from hashmap
                while (rmNode != curr) {
                    sm += rmNode.val;
                    sumMap.remove(sm);
                    rmNode = rmNode.next;
                }
                // Remove nodes which sums to 0
                prevSumNode.next = curr.next;
            } else {
                sumMap.put(sum, curr);
            }
            curr = curr.next;
        }
        return dummy.next;
    }

}
