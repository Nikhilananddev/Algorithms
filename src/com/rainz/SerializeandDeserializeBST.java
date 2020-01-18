package com.rainz;

import com.rainz.Main.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.
 * The encoded string should be as compact as possible.
 */

public class SerializeandDeserializeBST {
    public static void test(String args[]) {
        SerializeandDeserializeBST codec = new SerializeandDeserializeBST();
        TreeNode t1 = TreeNode.buildTree("4,2,6,1,3,5,7");
        String ser = codec.serialize(t1);
        System.out.println(ser);
        TreeNode deSer = codec.deserialize(ser);
        System.out.println(deSer);

        t1 = TreeNode.buildTree("");
        ser = codec.serialize(t1);
        System.out.println(ser);
        deSer = codec.deserialize(ser);
        System.out.println(deSer);
    }

    private static void serializeHelper(TreeNode root, List<String> strs) {
        if (root == null)
            return;
        strs.add(Integer.toString(root.val));
        serializeHelper(root.left, strs);
        serializeHelper(root.right, strs);
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<String> strs = new ArrayList<>();
        serializeHelper(root, strs);
        return String.join(",", strs);
    }

    private static TreeNode deserializeHelper(int[] preorder, int start, int end) {
        if (start >= end)
            return null;
        TreeNode root = new TreeNode(preorder[start]);
        int rightStart = start + 1;
        while (rightStart < end && preorder[rightStart] < root.val)
            ++rightStart;
        root.left = deserializeHelper(preorder, start+1, rightStart);
        root.right = deserializeHelper(preorder, rightStart, end);
        return root;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() == 0)
            return null;
        String[] parts = data.split(",");
        int[] nums = new int[parts.length];
        for (int i = 0; i < nums.length; ++i)
            nums[i] = Integer.parseInt(parts[i]);
        return deserializeHelper(nums, 0, nums.length);
    }
}
