package com.rainz;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        FindAllAnagramsinaString.test(args);
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
    // final int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    // final int[][] dirs8 = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}, {-1, -1}, {-1, 1}, {1, 1}, {1, -1}};

}
