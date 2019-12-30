package com.rainz;

import com.rainz.Main.TreeNode;

public class ConstructBinaryTreefromPreorderandInorderTraversal {
    public static void test(String args[]) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        System.out.println(buildTree(preorder, inorder));
        int[] preorder2 = {1,2,3};
        int[] inorder2 = {3,2,1};
        System.out.println(buildTree(preorder2, inorder2));
    }

    private static TreeNode helper(int[] preorder, int pStart, int pEnd, int[] inorder, int iStart, int iEnd) {
        if (pStart >= pEnd || iStart >= iEnd)
            return null;
        TreeNode root = new TreeNode(preorder[pStart]);
        int liStart = iStart;
        int liEnd = -1;
        for (int i = iStart; i < iEnd; ++i) {
            if (inorder[i] == root.val) {
                liEnd = i;
                break;
            }
        }
        int riStart = liEnd + 1;
        int riEnd = iEnd;
        int lpStart = pStart + 1;
        int lpEnd = lpStart + liEnd - liStart;
        int rpStart = lpEnd;
        int rpEnd = pEnd;
        root.left = helper(preorder, lpStart, lpEnd, inorder, liStart, liEnd);
        root.right = helper(preorder, rpStart, rpEnd, inorder, riStart, riEnd);
        return root;
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }
}
