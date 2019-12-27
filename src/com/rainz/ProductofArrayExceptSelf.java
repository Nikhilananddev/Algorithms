package com.rainz;

/*
 * Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
 */
public class ProductofArrayExceptSelf {
    public static void test(String args[]) {
        int[] input1 = {1,2,3,4};
        Main.printArray(productExceptSelf(input1));
    }

    // Idea is to compute product to the left and product to the right for each num.
    public static int[] productExceptSelf(int[] nums) {
        int N = nums.length;
        int[] products = new int[N];
        // Build left product table
        products[0] = 1;
        for (int i = 1; i < N; ++i)
            products[i] = products[i-1] * nums[i-1];
        // Now compute products to the right and apply them to the output
        int rightProduct = 1;
        for (int i = N-2; i >= 0; --i) {
            rightProduct *= nums[i+1];
            products[i] *= rightProduct;
        }
        return products;
    }
}
