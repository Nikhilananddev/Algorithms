package com.rainz;

import com.rainz.Main.TreeNode;

import java.util.Scanner;

/*
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 */
public class SerializeandDeserializeBinaryTree {
    public static void test(String args[]) {
        SerializeandDeserializeBinaryTree codec = new SerializeandDeserializeBinaryTree();
        TreeNode root = TreeNode.buildTree("1,2,3,null,null,4,5");
        String ser = codec.serialize(root);
        System.out.println(ser);
        System.out.println(codec.deserialize(ser));

    }

    private void serializeHelper(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("# ");
            return;
        }
        sb.append(root.val);
        sb.append(" ");
        serializeHelper(root.left, sb);
        serializeHelper(root.right, sb);
    }

    private TreeNode deserializeHelper(Scanner sc) {
        if (!sc.hasNext())
            return null;
        String token = sc.next();
        if (token.length() == 0 || token.equals("#"))
            return null;
        TreeNode n = new TreeNode(Integer.parseInt(token));
        n.left = deserializeHelper(sc);
        n.right = deserializeHelper(sc);
        return n;
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Scanner sc = new Scanner(data);
        return deserializeHelper(sc);
    }
}
