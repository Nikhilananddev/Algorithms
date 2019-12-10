package com.rainz;

import java.util.*;

public class GroupThePeopleGivenTheGroupSizeTheyBelongTo {
    public static void test(String args[]) {
        int[] input1 = {3,3,3,3,3,1,3};
        int[] input2 = {2,1,3,3,3,2};
        List<List<Integer>> result = groupThePeopleGivenTheGroupSizeTheyBelongTo(input1);
        System.out.println(Arrays.toString(result.toArray()));
        result = groupThePeopleGivenTheGroupSizeTheyBelongTo(input2);
        System.out.println(Arrays.toString(result.toArray()));
    }

    public static List<List<Integer>> groupThePeopleGivenTheGroupSizeTheyBelongTo(int[] groupSizes) {
        // Note: this problem only ask for one possible solution.
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, List<Integer>> sizeToGroup = new HashMap<>();
        // First, put all members with same group size into one hash entry,
        // even if they might belong to different groups. We'll break them down later.
        for (int i = 0; i < groupSizes.length; ++i) {
            int sz = groupSizes[i];
            List<Integer> group = sizeToGroup.get(sz);
            if (group == null) {
                group = new ArrayList<>();
                sizeToGroup.put(sz, group);
            }
            group.add(i);
        }
        // Now go through all hash entries, if # members exceed group size, create a separate group
        for (Map.Entry<Integer, List<Integer>> entry: sizeToGroup.entrySet()) {
            List<Integer> group = new ArrayList<>();
            int groupSz = entry.getKey();
            List<Integer> members = entry.getValue();
            while (!members.isEmpty()) {
                Integer m = members.remove(members.size()-1);
                group.add(m);
                if (group.size() >= groupSz) {
                    result.add(group);
                    group = new ArrayList<>();
                }
            }
        }

        return result;
    }
}
