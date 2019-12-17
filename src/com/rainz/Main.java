package com.rainz;

public class Main {

    public static void main(String[] args) {
        SequentialDigits.test(args);
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

    // Print list of list
    // result.forEach( value -> {value.forEach( v -> System.out.print( ""+v+",")); System.out.println(); });

    // int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

}
