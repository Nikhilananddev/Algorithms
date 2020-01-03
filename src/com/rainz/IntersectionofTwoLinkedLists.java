package com.rainz;

import com.rainz.Main.ListNode;

public class IntersectionofTwoLinkedLists {
    public static void test(String args[]) {
        int[] common = {8,4,5};
        int[] inputA = {4,1};
        int[] inputB = {5,0,1};
        ListNode a = ListNode.buildList(inputA);
        ListNode b = ListNode.buildList(inputB);
        ListNode c = ListNode.buildList(common);
        ListNode.appendList(a, c);
        ListNode.appendList(b, c);

        System.out.println(getIntersectionNode(a, b).val);
    }

    /*
     * Find lengths diff, move pointer of longer list forward until they are equal, then find the common node.
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = 0;
        int lenB = 0;
        for (ListNode n = headA; n != null; n = n.next)
            ++lenA;
        for (ListNode n = headB; n != null; n = n.next)
            ++lenB;
        ListNode a = headA;
        ListNode b = headB;
        int diff = lenA - lenB;
        if (diff > 0) {
            for (int i = 0; i < diff; ++i)
                a = a.next;
        } else if (diff < 0) {
            diff = -diff;
            for (int i = 0; i < diff; ++i)
                b = b.next;
        }
        while (a != b && a != null && b != null) {
            a = a.next;
            b = b.next;
        }
        if (a != b)
            return null;
        return a;
    }
}
