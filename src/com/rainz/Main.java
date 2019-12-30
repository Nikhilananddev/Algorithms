package com.rainz;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        ConstructBinaryTreefromPreorderandInorderTraversal.test(args);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }

        public static TreeNode buildTree(String s) {
            String[] parts = s.split(",");
            if (parts.length == 0)
                return null;
            if (parts.length == 1)
                return (parts[0].equals("null") ? null : new TreeNode(Integer.parseInt(parts[0])));
            int idx = 0;
            TreeNode root = new TreeNode(Integer.parseInt(parts[idx++]));
            Queue<List<TreeNode>> workQ = new LinkedList<>();
            List<TreeNode> prev = new ArrayList<>();
            prev.add(root);
            workQ.add(prev);
            while (!workQ.isEmpty() && idx < parts.length) {
                List<TreeNode> curr = new ArrayList<>();
                for (TreeNode node: prev) {
                    if (node != null) {
                        node.left = idx < parts.length ? buildTreeLevelOrder(parts[idx++]) : null;
                        if (node.left != null)
                            curr.add(node.left);
                        node.right = idx < parts.length ? buildTreeLevelOrder(parts[idx++]) : null;
                        if (node.right != null)
                            curr.add(node.right);
                    }
                }
                prev = curr;
            }
            return root;
        }

        // Must specify all nulls, even children of nulls
        public static TreeNode buildTreeLevelOrder(String s) {
            String[] parts = s.split(",");
            if (parts.length == 0)
                return null;
            if (parts.length == 1)
                return (parts[0].equals("null") ? null : new TreeNode(Integer.parseInt(parts[0])));
            /*if ((parts.length & (parts.length+1)) != 0) {
                System.err.println("Unable to build tree with length="+parts.length);
                return null;
            }*/
            int idx = 0;
            TreeNode root = new TreeNode(Integer.parseInt(parts[idx++]));
            Queue<List<TreeNode>> workQ = new LinkedList<>();
            List<TreeNode> prev = new ArrayList<>();
            prev.add(root);
            workQ.add(prev);
            while (!workQ.isEmpty() && idx < parts.length) {
                List<TreeNode> curr = new ArrayList<>();
                for (TreeNode node: prev) {
                    if (node != null) {
                        node.left = buildTreeLevelOrder(parts[idx++]);
                        curr.add(node.left);
                        node.right = buildTreeLevelOrder(parts[idx++]);
                        curr.add(node.right);
                    } else {
                        curr.add(null);
                        curr.add(null);
                        idx += 2;
                    }
                }
                prev = curr;
            }
            return root;
        }

        private static void toStringInOrderHelper(TreeNode root, StringBuilder sb) {
            if (root == null) {
                sb.append("null,");
                return;
            }
            sb.append(""+root.val+",");
            toStringInOrderHelper(root.left, sb);
            toStringInOrderHelper(root.right, sb);
        }
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append('{');
            toStringInOrderHelper(this, sb);
            sb.append('}');
            return sb.toString();
        }
    }

    public static <T> void printArray(T[] a) {
        System.out.print("{");
        for (int i = 0; i < a.length; ++i)
            System.out.print(""+a[i]+",");
        System.out.println("}");
    }

    public static void printArray(int[] a) {
        System.out.print("{");
        for (int i = 0; i < a.length; ++i)
            System.out.print(""+a[i]+",");
        System.out.println("}");
    }

    public static void printArray(boolean[] a) {
        System.out.print("{");
        for (int i = 0; i < a.length; ++i)
            System.out.print(""+a[i]+",");
        System.out.println("}");
    }

    public static void printArray(char[] a) {
        System.out.print("{");
        for (int i = 0; i < a.length; ++i)
            System.out.print(""+a[i]+",");
        System.out.println("}");
    }

    public static <T> void printArray2D(T[][] a) {
        System.out.println("{");
        for (int i = 0; i < a.length; ++i) {
            System.out.print("{");
            for (int j = 0; j < a[i].length; ++j)
                System.out.print(""+a[i][j]+",");
            System.out.println("},");
        }
        System.out.println("}");
    }

    public static void printArray2D(int[][] a) {
        System.out.println("{");
        for (int i = 0; i < a.length; ++i) {
            System.out.print("{");
            for (int j = 0; j < a[i].length; ++j)
                System.out.print(""+a[i][j]+",");
            System.out.println("},");
        }
        System.out.println("}");
    }

    public static void printArray2D(boolean[][] a) {
        System.out.println("{");
        for (int i = 0; i < a.length; ++i) {
            System.out.print("{");
            for (int j = 0; j < a[i].length; ++j)
                System.out.print(""+a[i][j]+",");
            System.out.println("},");
        }
        System.out.println("}");
    }

    public static void printArray2D(char[][] a) {
        System.out.println("{");
        for (int i = 0; i < a.length; ++i) {
            System.out.print("{");
            for (int j = 0; j < a[i].length; ++j)
                System.out.print(""+a[i][j]+",");
            System.out.println("},");
        }
        System.out.println("}");
    }

    public static <T> void printList(List<T> l) {
        System.out.print("{");
        l.forEach( v -> System.out.print( ""+v+",") );
        System.out.println("}");
    }

    public static <T> void printList2D(List<List<T>> l) {
        System.out.println("{");
        //l.forEach( value -> {value.forEach( v -> System.out.print( ""+v+",")); System.out.println(); });
        l.forEach ( v -> printList(v));
        System.out.println("}");
    }

    public static List<List<Integer>> buildList2D(int input[][]) {
        List<List<Integer>> ll = new ArrayList<>();
        for (int r = 0; r < input.length; ++r) {
            List<Integer> l = new ArrayList<>();
            for (int c = 0; c < input[r].length; ++c) {
                l.add(input[r][c]);
            }
            ll.add(l);
        }
        return ll;
    }

    public static <T> List<List<T>> buildList2D(T input[][]) {
        List<List<T>> ll = new ArrayList<>();
        for (int r = 0; r < input.length; ++r) {
            List<T> l = new ArrayList<>();
            for (int c = 0; c < input[r].length; ++c) {
                l.add(input[r][c]);
            }
            ll.add(l);
        }
        return ll;
    }

    // final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    // final int[][] dirs8 = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}, {-1, -1}, {-1, 1}, {1, 1}, {1, -1}};

}
