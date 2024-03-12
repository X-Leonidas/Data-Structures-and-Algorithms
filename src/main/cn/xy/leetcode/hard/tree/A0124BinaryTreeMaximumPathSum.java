package cn.xy.leetcode.hard.tree;

import cn.xy.utils.TreeNode;

/**
 * @author xiangyu
 * @date 2024-03-12 22:28
 * 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。
 * 同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 * <p>
 * 路径和 是路径中各节点值的总和。
 * <p>
 * 给你一个二叉树的根节点 root ，返回其 最大路径和
 * <p>
 * 树中节点数目范围是 [1, 3 * 104]
 * -1000 <= Node.val <= 1000
 */
public class A0124BinaryTreeMaximumPathSum {

    private int ans;

    public int maxPathSum(TreeNode root) {
        ans = root.val;
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int right = dfs(root.right);
        int left = dfs(root.left);
        int sum = Math.max(0,right) + Math.max(0,left) + root.val;

        if (sum > ans) {
            ans = sum;
        }
        return Math.max(left, right) + root.val;
    }
}
