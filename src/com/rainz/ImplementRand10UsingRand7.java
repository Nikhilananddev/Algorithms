package com.rainz;

import java.util.Random;

/*
 * Given a function rand7 which generates a uniform random integer in the range 1 to 7, write a function rand10 which generates a uniform random integer in the range 1 to 10.
 * Do NOT use system's Math.random().
 */
public class ImplementRand10UsingRand7 {
    public static void test(String args[]) {
        ImplementRand10UsingRand7 r10 = new ImplementRand10UsingRand7();
        for (int i = 0; i < 10; ++i)
            System.out.println(r10.rand10());
    }
    private Random rnd = new Random(System.currentTimeMillis());
    private int rand7() {
        return rnd.nextInt(7) + 1;
    }
    public int rand10() {
        int result = Integer.MAX_VALUE;
        while (result >= 40) {
            int n1 = rand7();
            int n2 = rand7();
            result = (n1-1)*7 + (n2-1);
        }
        return result/4 + 1;
    }
}
