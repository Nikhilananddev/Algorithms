package com.rainz;

import com.rainz.Main.TreeNode;
import com.rainz.Main.ListNode;

/*
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 */
public class ConvertSortedListtoBinarySearchTree {
    public static void test(String args[]) {
        int[] input1 = {-10,-3,0,5,9};
        System.out.println(sortedListToBST(ListNode.buildList(input1)));
    }

    public static TreeNode sortedListToBST(ListNode head) {
        if (head == null)
            return null;
        if (head.next == null)
            return new TreeNode(head.val);
        ListNode p1 = head, p2 = head;
        ListNode prev = null;
        // Check next 2 before moving p2. This helps balancing the tree
        while (p2 != null && p2.next != null) {
            prev = p1;
            p1 = p1.next;
            p2 = p2.next.next;
        }
        prev.next = null; // terminate left list
        TreeNode node = new TreeNode(p1.val);
        node.left = sortedListToBST(head);
        node.right = sortedListToBST(p1.next);
        return node;
    }
}
