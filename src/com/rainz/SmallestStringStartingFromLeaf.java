package com.rainz;

import com.rainz.Main.TreeNode;

public class SmallestStringStartingFromLeaf {
    public static void test(String args[]) {
        System.out.println(smallestFromLeaf(TreeNode.buildTree("0,1,2,3,4,3,4")));
        System.out.println(smallestFromLeaf(TreeNode.buildTree("25,1,3,1,3,0,2")));
        System.out.println(smallestFromLeaf(TreeNode.buildTree("2,2,1,null,1,0,null,0")));
    }

    private static String helper(TreeNode root, StringBuilder sb, String min) {
        sb.append((char)('a'+root.val));
        if (root.left == null && root.right == null) {
            // Leaf node
            String path = sb.reverse().toString();
            sb.reverse(); // restore state of sb!!!
            if (min == null || min.compareTo(path) > 0)
                min = path;
        } else {
            if (root.left != null) {
                String l = helper(root.left, sb, min);
                if (min == null || min.compareTo(l) > 0)
                    min = l;
            }
            if (root.right != null) {
                String r = helper(root.right, sb, min);
                if (min == null || min.compareTo(r) > 0)
                    min = r;
            }
        }
        sb.deleteCharAt(sb.length()-1);
        return min;
    }

    public static String smallestFromLeaf(TreeNode root) {
        if (root == null)
            return "";
        StringBuilder sb = new StringBuilder();
        return helper(root, sb, null);
    }
}
