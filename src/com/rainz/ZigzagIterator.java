package com.rainz;

import java.util.ArrayList;
import java.util.List;

public class ZigzagIterator {
    public static void test(String args[]) {
        List<Integer> v1 = List.of(1, 2);
        List<Integer> v2 = List.of(3, 4, 5, 6);
        ZigzagIterator i = new ZigzagIterator(v1, v2);
        while (i.hasNext()){
            System.out.println(i.next());
        }
        ZigzagIterator i2 = new ZigzagIterator(new ArrayList<>(), new ArrayList<>());
        while (i2.hasNext()){
            System.out.println(i2.next());
        }
    }

    private List<List<Integer>> lists = new ArrayList<>();
    private int listIdx = 0;
    private int idx = 0;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        lists.add(v1);
        lists.add(v2);
        // Point listIdx to first non-empty list
        for (int i = 0; i < lists.size(); ++i) {
            if (lists.get(i).size() > 0) {
                listIdx = i;
                break;
            }
        }
    }

    public int next() {
        int ret = lists.get(listIdx).get(idx);
        int startIdx = listIdx++;
        for (; listIdx < lists.size(); ++listIdx) {
            if (idx < lists.get(listIdx).size())
                break;
        }
        if (listIdx >= lists.size()) {
            ++idx;
            for (listIdx = 0; listIdx <= startIdx; ++listIdx) {
                if (idx < lists.get(listIdx).size())
                    break;
            }
            if (listIdx > startIdx)
                listIdx = 0; // last element reached; just point listIdx to any valid list
        }
        return ret;
    }

    public boolean hasNext() {
        return idx < lists.get(listIdx).size();
    }
}
