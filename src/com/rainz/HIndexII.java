package com.rainz;

public class HIndexII {
    public static void test(String args[]) {
        int[] citations = {0,1,3,5,6};
        System.out.println(hIndexII(citations));
        int[] citations2 = {100};
        System.out.println(hIndexII(citations2));
    }

    public static int hIndexII(int[] citations) {
        int lower = 0;
        int upper = citations.length-1;
        int result = 0;
        while (lower <= upper) {
            int curr =  (lower + upper)/2;
            int count = citations.length - curr;
            int citation = citations[curr];
            if (citation > count) {
                if (count > result)
                    result = count;
                upper = curr - 1;
            } else {
                if (citation > result)
                    result = citations[curr];
                lower = curr + 1;
            }
        }
        return result;
    }
}
