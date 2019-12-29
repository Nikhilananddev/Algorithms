package com.rainz;

import com.rainz.Main.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class DeepestLeavesSum {
    public static void test(String args[]) {
        TreeNode input1 = TreeNode.buildTree("1,2,3,4,5,null,6,7,null,null,null,null,8");
        System.out.println(deepestLeavesSum(input1));
    }

    public static int deepestLeavesSum(TreeNode root) {
        int ans = 0;
        if (root == null)
            return 0;
        List<TreeNode> level = new ArrayList<>();
        level.add(root);
        while (!level.isEmpty()) {
            List<TreeNode> nextLevel = new ArrayList<>();
            for (TreeNode node: level)  {
                if (node.left != null)
                    nextLevel.add(node.left);
                if (node.right != null)
                    nextLevel.add(node.right);
            }
            if (nextLevel.isEmpty())
                break;
            level = nextLevel;
        }
        for (TreeNode n: level)
            ans += n.val;
        return ans;
    }
}
