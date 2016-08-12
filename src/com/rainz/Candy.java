package com.rainz;

import java.util.Arrays;

/**
 * Created by Yu on 8/11/2016.
 */
public class Candy {

    public static void test(String args[]) {
        int[] ratings = {2, 3, 1, 5, 1, 2, 5, 7};
        //int[] ratings = {2, 1};
        System.out.println(candy(ratings));
    }

    static final class Child implements Comparable<Child>
    {
        public int index;
        public int rating;

        public Child(int idx, int r) {
            index = idx;
            rating = r;
        }

        @Override
        public int compareTo(Child other) {
            return Integer.compare(this.rating, other.rating);
        }
    }

    public static int candy(int[] ratings) {
        Child[] children = new Child[ratings.length];
        for (int i = 0; i < ratings.length; ++i) {
            children[i] = new Child(i, ratings[i]);
        }
        Arrays.sort(children);
        int[] candies = new int[ratings.length];
        for (int i = 0; i < children.length; ++i) {
            int count = 1;
            int myIndex = children[i].index;
            if (myIndex > 0 && ratings[myIndex-1] < ratings[myIndex]) {
                count = candies[myIndex-1] + 1;
            }
            if (myIndex < ratings.length-1 && ratings[myIndex+1] < ratings[myIndex]) {
                count = Math.max(count, candies[myIndex+1]+1);
            }
            candies[myIndex] = count;
        }

        int total = 0;
        for (int c: candies) {
            total += c;
        }

        return total;
    }

}
