package com.rainz;

import java.util.Arrays;

public class PrisonCellsAfterNDays {
    public static void test(String args[]) {
        int[] input = {0,1,0,1,1,0,0,1};
        System.out.println(Arrays.toString(prisonCellsAfterNDays(input, 7)));
        int[] input2 = {1,0,0,1,0,0,1,0};
        System.out.println(Arrays.toString(prisonCellsAfterNDays(input2, 1000000000)));
    }

    public static int[] prisonCellsAfterNDays(int[] cells, int N) {
        int state = 0;
        for (int c: cells)
            state = (state << 1) | c;

        int[] visited = new int[256];
        int[] states = new int[256];
        Arrays.fill(visited, -1);
        visited[state] = 0; // day 0
        states[0] = state;
        int day = 1;
        while (day <= N) {
            state = ~((state << 1) ^ (state >> 1)) & 0x7e;
            states[day] = state;
            int firstOccur = visited[state];
            if (firstOccur >= 0) {
                int period = day - firstOccur;
                state = states[(N - firstOccur) % period + firstOccur];
                break;
            } else {
                visited[state] = day++;
            }
        }

        int[] result = new int[8];
        int mask = 0x80;
        for (int i = 0; i < result.length; ++i) {
            result[i] = (state & mask) > 0 ? 1 : 0;
            mask >>= 1;
        }
        return result;
    }
}
