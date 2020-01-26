package com.rainz;

/*
 * Given a node from a Circular Linked List which is sorted in ascending order, write a function to insert a value insertVal into the list such that it remains a sorted circular list. The given node can be a reference to any single node in the list, and may not be necessarily the smallest value in the circular list.
 * If there are multiple suitable places for insertion, you may choose any place to insert the new value. After the insertion, the circular list should remain sorted.
 * If the list is empty (i.e., given node is null), you should create a new single circular list and return the reference to that single node. Otherwise, you should return the original given node.
 */
public class InsertintoaSortedCircularLinkedList {
    public static void test(String args[]) {
        Node n1 = new Node(1);
        Node n4 = new Node(4, n1);
        Node n3 = new Node(3, n4);
        n1.next = n3;
        Node head = n3;
        head = insert(head, 2);
        boolean started = false;
//        for (Node n = head; !started || n != head; n = n.next) {
//            System.out.print("" + n.val + ",");
//            started = true;
//        }
//        System.out.println();
        Node n2_3_1 = new Node(3);
        Node n2_3_2 = new Node(3, n2_3_1);
        Node n2_3_3 = new Node(3, n2_3_2);
        n2_3_1.next = n2_3_3;
        head = insert(n2_3_1, 0);
        started = false;
        for (Node n = head; !started || n != head; n = n.next) {
            System.out.print("" + n.val + ",");
            started = true;
        }
        System.out.println();
    }
    private static class Node {
        public int val;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    };
    public static Node insert(Node head, int insertVal) {
        Node nodeNew = new Node(insertVal);
        if (head == null) {
            nodeNew.next = nodeNew;
            return nodeNew;
        }
        if (head.next == head) {
            head.next = nodeNew;
            nodeNew.next = head;
            return head;
        }
        // At least 2 elements in the list
        Node prev = head, curr = head.next;
        do {
            if (prev.val <= insertVal && insertVal <= curr.val) {
                prev.next = nodeNew;
                nodeNew.next = curr;
                break;
            }
            if (prev.val > curr.val) {
                if (insertVal <= curr.val || insertVal >= prev.val) {
                    prev.next = nodeNew;
                    nodeNew.next = curr;
                    break;
                }
            }
            prev = curr;
            curr = curr.next;
        } while (prev != head);
        if (nodeNew.next == null) {
            // Must handle this case !!!!!!!!!!
            // not inserted, due to all numbers in existing list are equal. Just insert anywhere
            nodeNew.next = head.next;
            head.next = nodeNew;
        }
        return head;
    }
}
