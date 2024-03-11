package cn.xy.leetcode.easy.tree;

import cn.xy.utils.TreeNode;

/**
 * @author xiangyu
 * @date 2024-03-11 22:58
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵
 * 平衡二叉搜索树。
 */
public class A0108ConvertSortedArrayToBinarySearchTree {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null) {
            return null;
        }
        int mid = nums.length / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = dfs(nums, 0, mid - 1);
        root.right = dfs(nums, mid + 1, nums.length - 1);
        return root;
    }

    private TreeNode dfs(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        } else if (start == end) {
            return new TreeNode(nums[start]);
        }
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = dfs(nums, start, mid - 1);
        root.right = dfs(nums, mid + 1, end);
        return root;
    }

    /**
     * 优化版本
     *
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST2(int[] nums) {
        if (nums == null) {
            return null;
        }
        return dfs2(nums, 0, nums.length - 1);
    }

    private TreeNode dfs2(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        // 生成的树总是靠右
        int mid = (start + end) / 2;
        // 生成树总是靠左边，因为总是选取中间靠右的作为节点
        // int mid = (start + end + 1)  / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = dfs2(nums, start, mid - 1);
        root.right = dfs2(nums, mid + 1, end);
        return root;
    }
}
