package com.rainz;

import java.util.*;

public class RemoveSubFoldersfromtheFilesystem {
    public static void test(String args[]) {
        String[] input1 = {"/a","/a/b","/c/d","/c/d/e","/c/f"};
        String[] input2 = {"/a","/a/b/c","/a/b/d"};
        String[] input3 = {"/a/b/c","/a/b/ca","/a/b/d"};
        System.out.println(removeSubfolders(input1));
        System.out.println(removeSubfolders(input2));
        System.out.println(removeSubfolders(input3));
    }

    static class TrieNode {
        public Map<String, TrieNode> children = new HashMap<>();
        public boolean isEnd = false;
    }

    private static void findTopFolders(TrieNode node, List<String> path, List<String> result) {
        if (node.isEnd) {
            result.add("/" + String.join("/", path));
            return;
        }
        for (Map.Entry<String, TrieNode> entry: node.children.entrySet()) {
            path.add(entry.getKey());
            findTopFolders(entry.getValue(), path, result);
            path.remove(path.size()-1);
        }
    }

    public static List<String> removeSubfolders(String[] folder) {
        List<String> result = new ArrayList<>();

        TrieNode root = new TrieNode();
        for (String f: folder) {
            String[] parts = f.split("/");
            TrieNode curr = root;
            for (String p: parts) {
                if (p.isEmpty())
                    continue; // split() created an empty string for first "/"
                TrieNode child = curr.children.get(p);
                if (child == null) {
                    child = new TrieNode();
                    curr.children.put(p, child);
                }
                curr = child;
            }
            curr.isEnd = true;
        }
        List<String> path = new ArrayList<>();
        findTopFolders(root, path, result);
        return result;
    }
}
