package com.rainz;

import com.rainz.Main.ListNode;

import java.util.Random;

/*
 * Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.
 */

public class LinkedListRandomNode {
    public static void test(String args[]) {
        int[] input1 = {1,2,3};
        LinkedListRandomNode n = new LinkedListRandomNode(ListNode.buildList(input1));
        for (int i = 0; i < 10; ++i)
        System.out.println(n.getRandom());
    }

    private ListNode listHead;
    private Random rand  = new Random();
    public LinkedListRandomNode(ListNode head) {
        listHead = head;
    }

    /* Rservoir sampling */
    public int getRandom() {
        ListNode chosen = listHead;
        int n = 1;
        ListNode curr = listHead.next;
        while (curr != null) {
            ++n;
            int r = rand.nextInt(n);
            if (r == 0)
                chosen = curr;
            curr = curr.next;
        }
        return chosen.val;
    }
}
