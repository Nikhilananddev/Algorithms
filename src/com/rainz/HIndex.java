package com.rainz;

public class HIndex {
    public static void test(String args[]) {
        int[] citations = {3,0,6,1,5};
        System.out.println(hIndex(citations));
    }

    public static int hIndex(int[] citations) {
        final int N = citations.length;
        int[] stats = new int[N+1];

        for (int x: citations) {
            if (x >= N)
                ++stats[N];
            else
                ++stats[x];
        }
        int sum = 0; // sum of citations >= current citation count
        for (int i = N; i >= 0; --i) {
            sum += stats[i];
            if (sum >= i)
                return i;
        }
        return 0;
    }
}
