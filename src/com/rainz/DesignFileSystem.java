package com.rainz;

import java.util.HashMap;
import java.util.Map;

/*
 * You are asked to design a file system which provides two functions:
 * createPath(path, value): Creates a new path and associates a value to it if possible and returns True. Returns False if the path already exists or its parent path doesn't exist.
 * get(path): Returns the value associated with a path or returns -1 if the path doesn't exist.
 * The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English letters. For example, /leetcode and /leetcode/problems are valid paths while an empty string and / are not.
 */

public class DesignFileSystem {
    public static void test(String args[]) {
        DesignFileSystem fileSystem = new DesignFileSystem();
        System.out.println(fileSystem.createPath("/leet", 1)); // return true
        System.out.println(fileSystem.createPath("/leet/code", 2)); // return true
        System.out.println(fileSystem.createPath("/leet", 1)); // return false because path already exists
        System.out.println(fileSystem.createPath("/leet/code", 2)); // return false because path already exists
        System.out.println(fileSystem.get("/leet/code")); // return 2
        System.out.println(fileSystem.createPath("/c/d", 1)); // return false because the parent path "/c" doesn't exist.
        System.out.println(fileSystem.get("/c")); // return -1 because this path doesn't exist.
    }

    private static class FileNode {
        public FileNode(int v) { val = v; }
        public Map<String, FileNode> children = new HashMap<>();
        public int val;
    }

    private FileNode root = new FileNode(-1);

    public DesignFileSystem() {

    }

    public boolean createPath(String path, int value) {
        String[] parts = path.split("/");
        FileNode curr = root;
        boolean ret = false;
        for (int i = 0; i < parts.length; ++i) {
            String s = parts[i];
            if (s.length() == 0)
                continue;
            FileNode node = curr.children.get(s);
            if (node != null)
                curr = node;
            else {
                if (i < parts.length-1)
                    return false;
                curr.children.put(s, new FileNode(value));
                ret = true;
            }
        }
        return ret;
    }

    public int get(String path) {
        String[] parts = path.split("/");
        FileNode curr = root;
        int ret = -1;
        for (int i = 0; i < parts.length; ++i) {
            String s = parts[i];
            if (s.length() == 0)
                continue;
            FileNode node = curr.children.get(s);
            if (node == null)
                return -1;
            curr = node;
        }
        return curr.val;
    }
}
