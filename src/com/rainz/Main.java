package com.rainz;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        MaximumLevelSumofaBinaryTree.test(args);
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
                return (parts[0].equals("null") || parts[0].length()==0 ? null : new TreeNode(Integer.parseInt(parts[0])));
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

        public static TreeNode findFirst(TreeNode root, int val) {
            if (root == null) return null;
            if (root.val == val) return root;
            TreeNode n = findFirst(root.left, val);
            if (n != null) return n;
            return findFirst(root.right, val);
        }

        private static void toStringInOrderHelper(TreeNode root, StringBuilder sb) {
            if (root == null) {
                sb.append("null,");
                return;
            }
            sb.append(""+root.val+",");
            if (root.left != null || root.right != null) {
                toStringInOrderHelper(root.left, sb);
                toStringInOrderHelper(root.right, sb);
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Infix: {");
            toStringInOrderHelper(this, sb);
            sb.append('}');
            return sb.toString();
        }
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }

        public static ListNode buildListFromInt(int val) {
            ListNode result = new ListNode(0); // dummy
            if (val == 0)
                return result;
            ListNode curr = result;
            while (val > 0) {
                curr.next = new ListNode(val % 10);
                curr = curr.next;
                val /= 10;
            }
            return result.next;
        }

        public static ListNode buildListFromRange(int start, int len) {
            ListNode head = null;
            ListNode tail = null;
            for (int i = start; i < start+len; ++i) {
                ListNode curr = new ListNode(i);
                if (head == null)
                    head = curr;
                else
                    tail.next = curr;
                tail = curr;
            }
            return head;
        }
        public static ListNode buildList(int[] array) {
            ListNode head = null;
            ListNode tail = null;
            for (int i = 0; i < array.length; ++i) {
                ListNode curr = new ListNode(array[i]);
                if (head == null)
                    head = curr;
                else
                    tail.next = curr;
                tail = curr;
            }
            return head;
        }

        public static ListNode appendList(ListNode a, ListNode b) {
            ListNode last = null;
            for (ListNode l = a; l != null; l = l.next) {
                last = l;
            }
            if (last == null)
                return b;
            last.next = b;
            return a;
        }

        @Deprecated
        public static void printList(ListNode l) {
            if (l != null) {
                System.out.print(l.val);
                l = l.next;
            }
            while (l != null) {
                System.out.print("->");
                System.out.print(l.val);
                l = l.next;
            }
            System.out.println();
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            ListNode l = this;
            sb.append(l.val);
            l = l.next;
            while (l != null) {
                sb.append("->");
                sb.append(l.val);
                l = l.next;
            }
            return sb.toString();
        }
    }

    public static class Graph {
        public static class Node {
            public int val;
            public List<Node> neighbors;

            public Node() {}

            public Node(int _val,List<Node> _neighbors) {
                val = _val;
                neighbors = _neighbors;
            }
        }
    }

    public static class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
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
