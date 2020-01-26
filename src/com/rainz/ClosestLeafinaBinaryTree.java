package com.rainz;

import com.rainz.Main.TreeNode;

import java.util.*;

/*
 * Given a binary tree where every node has a unique value, and a target key k, find the value of the nearest leaf node to target k in the tree.
 * Here, nearest to a leaf means the least number of edges travelled on the binary tree to reach any leaf of the tree. Also, a node is called a leaf if it has no children.
 */
public class ClosestLeafinaBinaryTree {
    public static void test(String args[]) {
        System.out.println(findClosestLeaf(TreeNode.buildTree("1,3,2"), 1));
        System.out.println(findClosestLeaf(TreeNode.buildTree("1"), 1));
        System.out.println(findClosestLeaf(TreeNode.buildTree("1,2,3,4,null,null,null,5,null,6"), 2));
    }

    private static void buildGraph(TreeNode root, Map<TreeNode, List<TreeNode>> graph, int k, TreeNode[] target) {
        if (root == null)
            return;
        if (root.val == k)
            target[0] = root;
        List<TreeNode> rootList = graph.get(root);
        if (rootList == null) {
            rootList = new ArrayList<>();
            graph.put(root, rootList);
        }
        if (root.left != null) {
            rootList.add(root.left);
            List<TreeNode> leftList = graph.get(root.left);
            if (leftList == null) {
                leftList = new ArrayList<>();
                graph.put(root.left, leftList);
            }
            leftList.add(root);
            buildGraph(root.left, graph, k, target);
        }
        if (root.right != null) {
            rootList.add(root.right);
            List<TreeNode> rightList = graph.get(root.right);
            if (rightList == null) {
                rightList = new ArrayList<>();
                graph.put(root.right, rightList);
            }
            rightList.add(root);
            buildGraph(root.right, graph, k, target);
        }
    }

    public static int findClosestLeaf(TreeNode root, int k) {
        Map<TreeNode, List<TreeNode>> graph = new HashMap<>();
        TreeNode[] target = new TreeNode[1];
        buildGraph(root, graph, k, target);
        // BFS
        List<TreeNode> curr = new ArrayList<>();
        curr.add(target[0]);
        Set<TreeNode> visited = new HashSet<>();
        while (!curr.isEmpty()) {
            List<TreeNode> next = new ArrayList<>();
            for (TreeNode node: curr) {
                if (node.left == null && node.right == null)
                    return node.val;
                if (visited.contains(node))
                    continue;
                visited.add(node);
                List<TreeNode> nbs = graph.get(node);
                for (TreeNode nb: nbs)
                    next.add(nb);
            }
            curr = next;
        }
        return -1;
    }
}
