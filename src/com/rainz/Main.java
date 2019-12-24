package com.rainz;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        GasStation.test(args);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
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
