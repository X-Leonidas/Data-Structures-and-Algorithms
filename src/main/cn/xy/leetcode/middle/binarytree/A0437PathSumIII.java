package cn.xy.leetcode.middle.binarytree;

import cn.xy.utils.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiangyu
 * @date 2024-02-22 21:01
 * <p>
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * <p>
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * <p>
 * 二叉树的节点个数的范围是 [0,1000]
 * -109 <= Node.val <= 109
 * -1000 <= targetSum <= 1000
 */
public class A0437PathSumIII {
    private int result = 0;


    public int pathSum(TreeNode root, int targetSum) {
        List<Integer> helpList = new ArrayList<>();
        help(root, helpList, targetSum);
        return result;
    }

    private void help(TreeNode root, List<Integer> helpList, int targetSum) {
        if (root == null) {
            return;
        }
        helpList.add(root.val);
        long tempValue = 0;
        for (int i = helpList.size() - 1; i >= 0; i--) {
            tempValue = tempValue + helpList.get(i);
            if (tempValue == targetSum) {
                result++;
            }
        }
        help(root.left, helpList, targetSum);
        help(root.right, helpList, targetSum);
        helpList.remove(helpList.size() - 1);
    }


    private int ans = 0;
    private Map<Long, Integer> prefixSum;

    public int pathSum2(TreeNode root, int targetSum) {
        prefixSum = new HashMap<>();
        //根节点的前缀和，重点****
        prefixSum.put(0L, 1);
        dfs(root, targetSum, 0);
        return ans;
    }

    public void dfs(TreeNode root, int targetSum, long sum) {
        if (root == null) {
            return;
        }
        //前缀和
        sum += root.val;
        //另一个满足要求的节点数量，因为当前节点的前缀和为sum，目标值为target，
        // 需要满足要求sum - sum2 = target => sum2 = sum - target
        int cnt = prefixSum.getOrDefault(sum - targetSum, 0);
        //更新答案
        ans += cnt;
        cnt = prefixSum.getOrDefault(sum, 0);
        //更新哈希表
        prefixSum.put(sum, ++cnt);
        dfs(root.left, targetSum, sum);
        dfs(root.right, targetSum, sum);
        // 这里至少为1，不会出现--cnt为负数的情况
        cnt = prefixSum.getOrDefault(sum, 0);
        //恢复
        prefixSum.put(sum, --cnt);
    }


    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1000000000);
        TreeNode treeNode1 = new TreeNode(1000000000);
        TreeNode treeNode2 = new TreeNode(294967296);
        TreeNode treeNode3 = new TreeNode(1000000000);
        TreeNode treeNode4 = new TreeNode(1000000000);
        TreeNode treeNode5 = new TreeNode(1000000000);
        treeNode.left = treeNode1;
        treeNode1.left = treeNode2;
        treeNode2.left = treeNode3;
        treeNode3.left = treeNode4;
        treeNode4.left = treeNode5;
        A0437PathSumIII a0437PathSumIII = new A0437PathSumIII();
        System.out.println(a0437PathSumIII.pathSum(treeNode, 0));
    }
}
