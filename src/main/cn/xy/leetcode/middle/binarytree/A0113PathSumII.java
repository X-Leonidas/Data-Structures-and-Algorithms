package cn.xy.leetcode.middle.binarytree;

import cn.xy.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiangyu
 * @date 2024-02-22 22:10
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * 提示：
 * <p>
 * 树中节点总数在范围 [0, 5000] 内
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 */
public class A0113PathSumII {
    private List<List<Integer>> result;


    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        result = new ArrayList<>();
        dfs(root, targetSum, 0, new ArrayList<>());
        return result;
    }

    public void dfs(TreeNode root, int targetSum, int curValue, List<Integer> curList) {
        if (root == null) {
            return;
        }
        curList.add(root.val);
        curValue = curValue + root.val;
        if (root.left == null && root.right == null && curValue == targetSum) {
            result.add(new ArrayList<>(curList));
        }
        dfs(root.left, targetSum, curValue, curList);
        dfs(root.right, targetSum, curValue, curList);
        curList.remove(curList.size() - 1);
    }

    public void dfs2(TreeNode root, int targetSum, List<Integer> curList) {
        if (root == null) {
            return;
        }
        curList.add(root.val);
        targetSum = targetSum - root.val;
        if (root.left == null && root.right == null && 0 == targetSum) {
            result.add(new ArrayList<>(curList));
        }
        dfs2(root.left, targetSum, curList);
        dfs2(root.right, targetSum, curList);
        curList.remove(curList.size() - 1);
    }


}
