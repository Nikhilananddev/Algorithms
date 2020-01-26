package com.rainz;

import com.rainz.Main.ListNode;

import java.util.HashSet;
import java.util.Set;

/*
 * We are given head, the head node of a linked list containing unique integer values.
 * We are also given the list G, a subset of the values in the linked list.
 * Return the number of connected components in G, where two values are connected if they appear consecutively in the linked list.
 */
public class LinkedListComponents {
    public static void test(String args[]) {
        int[] input1 = {0, 1, 2, 3};
        int[] G1 = {0, 1, 3};
        System.out.println(numComponents(ListNode.buildList(input1), G1));
        int[] input2 = {0, 1, 2, 3, 4};
        int[] G2 = {0, 3, 1, 4};
        System.out.println(numComponents(ListNode.buildList(input1), G2));
    }
    public static int numComponents(ListNode head, int[] G) {
        Set<Integer> numSet = new HashSet<>();
        for (int g: G)
            numSet.add(g);
        int ans = 0;
        ListNode prev = null;
        for (ListNode n = head; n != null; n = n.next) {
            if (!numSet.contains(n.val)) {
                prev = null;
                continue;
            }
            if (prev == null)
                ++ans;
            prev = n;
        }
        return ans;
    }

}
