package com.rainz;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.
 * Given a list of stones' positions (in units) in sorted ascending order, determine if the frog is able to cross the river by landing on the last stone. Initially, the frog is on the first stone and assume the first jump must be 1 unit.
 * If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. Note that the frog can only jump in the forward direction.
 * Note:
 * The number of stones is ≥ 2 and is < 1,100.
 * Each stone's position will be a non-negative integer < 231.
 * The first stone's position is always 0.
 */
public class FrogJump {
    public static void test(String args[]) {
        int[] input1 = {0,1,3,5,6,8,12,17};
        System.out.println(canCross(input1));
        int[] input2 = {0,1,2,3,4,8,9,11};
        System.out.println(canCross(input2));
        int[] input3 = {0,1,3,6,10,15,16,21};
        System.out.println(canCross(input3));
    }

    /*
     * Idea is for earlier stones to set possible speeds for later stones
     * 1st stone has one possible speed, when it reaches next stone, next stone will have up to 3 possible speeds.
     * For ith stone with k speeds, it reaches up to k stones, each of them would then gain up to 3 extra speeds.
     * If a stone has no speed, it is not reachable. If last stone has no speed, return false.
     */
    public static boolean canCross(int[] stones) {
        int N = stones.length;
        Map<Integer, Set<Integer>> speedTable = new HashMap<>();
        for (int s: stones)
            speedTable.put(s, new HashSet<>());
        speedTable.get(0).add(1); // initial speed of 1 at starting stone
        for (int i = 0; i < N; ++i) {
            int s = stones[i];
            Set<Integer> speeds = speedTable.get(s);
            for (int sp: speeds) {
                if (sp <= 0)
                    continue;
                int nextStone = s + sp;
                Set<Integer> nextSpeeds = speedTable.get(nextStone);
                if (nextSpeeds == null)
                    continue; // doesn't have this stone
                for (int spIdx = sp-1; spIdx <= sp+1; ++spIdx)
                    nextSpeeds.add(spIdx);
            }
        }
        return speedTable.get(stones[N-1]).size() > 0;
    }
}
