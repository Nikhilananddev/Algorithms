package com.rainz;

import java.util.Stack;

/*
 * Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.
 * You may assume each number in the sequence is unique.
 */
public class VerifyPreorderSequenceinBinarySearchTree {
    public static void test(String args[]) {
        int[] input1 = {5,2,6,1,3};
        System.out.println(verifyPreorder(input1));
        int[] input2 = {5,2,1,3,6};
        System.out.println(verifyPreorder(input2));
        int[] input3 = {1,3,4,2};
        System.out.println(verifyPreorder(input3));
    }

    public static boolean verifyPreorder(int[] preorder) {
        int min = Integer.MIN_VALUE;
        Stack<Integer> roots = new Stack<>();
        for (int a: preorder) {
            if (a < min)
                return false;
            /* If a number is greater than the previous number, it is start of a right tree */
            while (!roots.isEmpty() && a > roots.peek())
                min = roots.pop(); // pop out left tree nodes
            roots.push(a); // a < s.peek(), so it can be root for a subtree
        }
        return true;
    }

    public static boolean helper(int[] preorder, int start, int end, int min, int max) {
        if (start > end)
            return true;
        int root = preorder[start];
        if (root < min || root > max)
            return false;
        int i = start + 1;
        while (i <= end && preorder[i] < root)
            ++i;
        return helper(preorder, start+1, i-1, min, root-1) &&
                helper(preorder, i, end, root+1, max);
    }

    public static boolean verifyPreorderDivideConquer(int[] preorder) {
        return helper(preorder, 0, preorder.length-1, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
}
