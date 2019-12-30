package com.rainz;

import com.rainz.Main.TreeNode;

public class ConstructBinaryTreefromInorderandPostorderTraversal {
    public static void test(String args[]) {
        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};
        System.out.println(buildTree(inorder, postorder));
        int[] inorder2 = {2,1};
        int[] postorder2 = {2,1};
        System.out.println(buildTree(inorder2, postorder2));
    }

    private static TreeNode helper(int[] inorder, int iStart, int iEnd, int[] postorder, int pStart, int pEnd) {
        if (pStart >= pEnd || iStart >= iEnd)
            return null;
        TreeNode root = new TreeNode(postorder[pEnd-1]);
        int liStart = iStart;
        int lpStart = pStart;

        int liEnd = liStart;
        // Use root val instead of comparing left trees because root is always there!
        for (;liEnd < iEnd; ++liEnd) {
            if (inorder[liEnd] == root.val) {
                break;
            }
        }
        int lpEnd = lpStart + liEnd - liStart;

        int riStart = liEnd + 1;
        int riEnd = iEnd;
        int rpStart = lpEnd;
        int rpEnd = pEnd - 1;
        root.left = helper(inorder, liStart, liEnd, postorder, lpStart, lpEnd);
        root.right = helper(inorder, riStart, riEnd, postorder, rpStart, rpEnd);
        return root;
    }

    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        return helper(inorder, 0, inorder.length, postorder, 0, postorder.length);
    }
}
