package com.rainz;

/*
 * Implement the class ProductOfNumbers that supports two methods:
 * 1. add(int num)
 * Adds the number num to the back of the current list of numbers.
 * 2. getProduct(int k)
 * Returns the product of the last k numbers in the current list.
 * You can assume that always the current list has at least k numbers.
 * At any time, the product of any contiguous sequence of numbers will fit into a single 32-bit integer without overflowing.
 */

import java.util.ArrayList;
import java.util.List;

public class ProductoftheLastKNumbers {
    public static void test(String args[]) {
        ProductoftheLastKNumbers productOfNumbers = new ProductoftheLastKNumbers();
        productOfNumbers.add(3);        // [3]
        productOfNumbers.add(0);        // [3,0]
        productOfNumbers.add(2);        // [3,0,2]
        productOfNumbers.add(5);        // [3,0,2,5]
        productOfNumbers.add(4);        // [3,0,2,5,4]
        System.out.println(productOfNumbers.getProduct(2)); // return 20. The product of the last 2 numbers is 5 * 4 = 20
        System.out.println(productOfNumbers.getProduct(3)); // return 40. The product of the last 3 numbers is 2 * 5 * 4 = 40
        System.out.println(productOfNumbers.getProduct(4)); // return 0. The product of the last 4 numbers is 0 * 2 * 5 * 4 = 0
        productOfNumbers.add(8);        // [3,0,2,5,4,8]
        System.out.println(productOfNumbers.getProduct(2)); // return 32. The product of the last 2 numbers is 4 * 8 = 32
    }

    List<Integer> nums = new ArrayList<>();
    List<Integer> products = new ArrayList<>();
    public ProductoftheLastKNumbers() {
    }

    public void add(int num) {
        nums.add(num);
        int N = products.size();
        if (num == 0)
            products.clear();
        else if (N == 0)
            products.add(num);
        else
            products.add(products.get(N-1)*num);
    }

    public int getProduct(int k) {
        int N = products.size();
        if (N < k)
            return 0;
        if (N == k)
            return products.get(N-1);
        return products.get(N-1) / products.get(N-k-1);
    }
}
