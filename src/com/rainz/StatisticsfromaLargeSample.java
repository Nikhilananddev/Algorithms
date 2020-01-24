package com.rainz;

/*
 * We sampled integers between 0 and 255, and stored the results in an array count:  count[k] is the number of integers we sampled equal to k.
 * Return the minimum, maximum, mean, median, and mode of the sample respectively, as an array of floating point numbers.  The mode is guaranteed to be unique.
 */
public class StatisticsfromaLargeSample {
    public static void test(String args[]) {
        int[] input1 = {0,1,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        Main.printArray(sampleStats(input1));
        int[] input2 = {0,4,3,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        Main.printArray(sampleStats(input2));
    }

    public static double[] sampleStats(int[] count) {
        double min = -1, max = -1;
        int totalCnt = 0;
        int mode = 0;
        double avg = 0;
        // First find min, max, mean, and mode
        for (int i = 0; i < count.length; ++i) {
            int cnt = count[i];
            if (cnt == 0)
                continue;
            // Min & max
            if (min < 0)
                min = i;
            max = i;
            // Mean
            int newCnt = totalCnt + cnt;
            avg = avg*totalCnt/newCnt + (double)cnt/newCnt*i;
            totalCnt = newCnt;
            // Mode
            if (cnt > count[mode])
                mode = i;
        }
        // Now find median
        double median = -1;
        int cntSoFar = 0;
        int lastNum = -1;
        for (int i = 0; i < count.length; ++i) {
            int cnt = count[i];
            cntSoFar += cnt;
            if (cntSoFar > totalCnt/2) {
                // Odd case (or if first group already contains median)
                if (lastNum == -1 || totalCnt % 2 == 1) {
                    median = i;
                    break;
                }
                // Even case
                if (cntSoFar - cnt == totalCnt/2)
                    median = (lastNum + i) / 2.0; // first one in prev group, second one in current group
                else
                    median = i;
                break;
            }
            if (cnt > 0)
                lastNum = i;
        }
        double[] ans ={min, max, avg, median, mode};
        return ans;
    }
}
