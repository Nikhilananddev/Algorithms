package com.rainz;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
/*
 * In a warehouse, there is a row of barcodes, where the i-th barcode is barcodes[i].
 * Rearrange the barcodes so that no two adjacent barcodes are equal.  You may return any answer, and it is guaranteed an answer exists.
 */
public class DistantBarcodes {
    public static void test(String args[]) {
        int[] input1 = {1,1,1,2,2,2};
        Main.printArray(rearrangeBarcodes(input1));
        int[] input2 = {1,1,1,1,2,2,3,3};
        Main.printArray(rearrangeBarcodes(input2));
    }

    public static int[] rearrangeBarcodes(int[] barcodes) {
        Map<Integer, Integer> freqs = new HashMap<>();
        for (int n: barcodes) {
            Integer count = freqs.get(n);
            if (count == null)
                count = 0;
            freqs.put(n, count+1);
        }

        int[] output = new int[barcodes.length];
        int idx = 0;
        PriorityQueue<Map.Entry<Integer, Integer>> pq =
                new PriorityQueue<>((x, y) -> y.getValue() - x.getValue());
        for (Map.Entry<Integer, Integer> entry: freqs.entrySet()) {
            pq.add(entry);
        }
        int lastNum = 0;
        while (!pq.isEmpty()) {
            Map.Entry<Integer, Integer> entry = pq.remove();
            int num = entry.getKey();
            if (lastNum == num) {
                // Can't use the same entry, get the next one
                Map.Entry<Integer, Integer> entry2 = pq.remove();
                pq.add(entry); // put first entry back
                // Decrement 2nd entry and put it back
                num = entry2.getKey();
                int newVal = entry2.getValue() - 1;
                if (newVal > 0) {
                    entry2.setValue(newVal);
                    pq.add(entry2);
                }
            } else {
                // Decrement entry and put it back
                int newVal = entry.getValue() - 1;
                if (newVal > 0) {
                    entry.setValue(newVal);
                    pq.add(entry);
                }
            }
            output[idx++] = num;
            lastNum = num;
        }
        return output;
    }
}
