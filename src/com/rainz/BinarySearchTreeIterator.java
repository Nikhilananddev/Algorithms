package com.rainz;

import com.rainz.Main.TreeNode;

import java.util.Stack;
/*
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 * Calling next() will return the next smallest number in the BST.
 */

public class BinarySearchTreeIterator {
    public static void test(String args[]) {
        TreeNode input = TreeNode.buildTree("7,3,15,null,null,9,20");
        BinarySearchTreeIterator iterator = new BinarySearchTreeIterator(input);
        System.out.println(iterator.next());    // return 3
        System.out.println(iterator.next());    // return 7
        System.out.println(iterator.hasNext()); // return true
        System.out.println(iterator.next());    // return 9
        System.out.println(iterator.hasNext()); // return true
        System.out.println(iterator.next());    // return 15
        System.out.println(iterator.hasNext()); // return true
        System.out.println(iterator.next());    // return 20
        System.out.println(iterator.hasNext()); // return false
    }

    private Stack<TreeNode> _stk = new Stack<>();
    private TreeNode _curr = null;

    private void leftMost(TreeNode node) {
        _curr = node;
        while (_curr != null) {
            _stk.push(_curr);
            _curr = _curr.left;
        }
        if (!_stk.isEmpty())
            _curr = _stk.pop();
        else
            _curr = null;
    }

    public BinarySearchTreeIterator(TreeNode root) {
       leftMost(root);
    }

    /** @return the next smallest number */
    public int next() {
        int val = _curr.val;
        // Either the smallest of right tree, or pop()
        if (_curr.right == null) {
            if (!_stk.isEmpty())
                _curr = _stk.pop();
            else
                _curr = null;
        }
        else {
            leftMost(_curr.right);
        }
        return val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return _curr != null;
    }
}
