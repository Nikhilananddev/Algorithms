package com.rainz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindDuplicateFileinSystem {
    public static void test(String args[]) {
        String[] input1 = {"root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"};
        Main.printList2D(findDuplicate(input1));
    }

    public static List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> fileMap = new HashMap<>();
        for (String p : paths) {
            String[] parts = p.split(" ");
            String dir = parts[0];
            for (int i = 1; i < parts.length; ++i) {
                String s = parts[i];
                int left = s.indexOf('(');
                int right = s.indexOf(')', left + 1);
                String name = s.substring(0, left);
                String content = s.substring(left + 1, right);
                String filePath = String.format("%s/%s", dir, name);
                List<String> fileList = fileMap.get(content);
                if (fileList == null) {
                    fileList = new ArrayList<>();
                    fileMap.put(content, fileList);
                }
                fileList.add(filePath);
            }
        }
        List<List<String>> ans = new ArrayList<>();
        for (List<String> l: fileMap.values())
            if (l.size() > 1)
                ans.add(l);
        return ans;
    }
}