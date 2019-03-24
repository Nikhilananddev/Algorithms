package com.rainz;

import java.util.*;

public class KeysAndRooms {
    public static void test(String args[]) {
        List<List<Integer>> input1 = new ArrayList<>();
        input1.add(List.of(1));
        input1.add(List.of(2));
        input1.add(List.of(3));
        input1.add(new ArrayList<>());
        System.out.println(keysAndRooms(input1));
        input1.clear();
        input1.add(List.of(1,3));
        input1.add(List.of(3,0,1));
        input1.add(List.of(2));
        input1.add(List.of(0));
        System.out.println(keysAndRooms(input1));
    }

    public static boolean keysAndRooms(List<List<Integer>> rooms) {
        final int N = rooms.size();
        boolean[] visited = new boolean[N];
        int visitedCount = 0;

        Queue<Integer> workQ = new LinkedList<>();
        workQ.offer(0);

        while (!workQ.isEmpty()) {
            int room = workQ.poll();
            if (!visited[room]) {
                visited[room] = true;
                ++visitedCount;
            }
            List<Integer> keys = rooms.get(room);
            for (Integer k: keys)
                if (!visited[k])
                    workQ.offer(k);
        }

        return visitedCount == N;
    }
}
