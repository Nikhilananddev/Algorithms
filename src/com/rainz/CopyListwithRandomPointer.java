package com.rainz;

/*
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
 * Return a deep copy of the list.
 */

import java.util.HashMap;
import java.util.Map;

public class CopyListwithRandomPointer {
    public static void test(String args[]) {
        copyRandomList(null);
    }
    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    public static Node copyRandomList(Node head) {
        Map<Node, Node> clones = new HashMap<>();
        Node dummy = new Node(0);
        Node prevClone = dummy;
        for (Node curr = head; curr != null; curr = curr.next) {
            Node clone = clones.get(curr);
            if (clone == null) {
                clone = new Node(curr.val);
                clones.put(curr, clone);
            }
            prevClone.next = clone;
            if (curr.random != null) {
                Node randClone = clones.get(curr.random);
                if (randClone == null) {
                    randClone = new Node(curr.random.val);
                    clones.put(curr.random, randClone);
                }
                clone.random = randClone;
            }
            prevClone = clone;
        }
        return dummy.next;
    }

}
