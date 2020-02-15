package com.rainz;

import com.rainz.Main.TreeNode;

/*
 * Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place.
 * You can think of the left and right pointers as synonymous to the predecessor and successor pointers in a doubly-linked list. For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.
 * We want to do the transformation in place. After the transformation, the left pointer of the tree node should point to its predecessor, and the right pointer should point to its successor. You should return the pointer to the smallest element of the linked list.
 */
public class ConvertBinarySearchTreetoSortedDoublyLinkedList {
    public static void test(String args[]) {
        Node[] input1 = new Node[5];
        for (int i = 0; i < input1.length; ++i)
            input1[i] = new Node(i+1);
        input1[1].left = input1[0];
        input1[1].right = input1[2];
        input1[3].left = input1[1];
        input1[3].right = input1[4];
        Node res = treeToDoublyList(input1[3]);
        Node curr = res;
        int count = 0;
        while (curr != null && count < input1.length) {
            System.out.print(curr.val+",");
            ++count;
            curr = curr.right;
        }
        System.out.println();
    }
    private static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    };

    private static Node[] helper(Node root) {
        Node[] res = { root, root };
        if (root == null)
            return res;
        Node left = root.left;
        Node right = root.right;
        root.left = null;
        root.right = null;
        Node[] lRes = helper(left);
        if (lRes[0] != null) {
            res[0] = lRes[0];
            lRes[1].right = root;
            root.left = lRes[1];
        }
        Node[] rRes = helper(right);
        if (rRes[1] != null) {
            res[1] = rRes[1];
            root.right = rRes[0];
            rRes[0].left = root;
        }
        return res;
    }
    public static Node treeToDoublyList(Node root) {
        Node[] res = helper(root);
        if (res[0] != null) {
            res[0].left = res[1];
            res[1].right = res[0];
        }
        return res[0];
    }
}
