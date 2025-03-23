package cn.xy.leetcode.middle.dp;

import cn.xy.utils.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiangyu
 * @date 2025-03-23 23:24
 * <p>
 * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
 * <p>
 * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。
 * 一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
 * <p>
 * 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
 * <p>
 * 示例 1:
 * 输入: root = [3,2,3,null,3,null,1]
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7
 * <p>
 * 示例 2:
 * 输入: root = [3,4,5,1,3,null,1]
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 4 + 5 = 9
 * <p>
 * 提示：
 * 树的节点数在 [1, 104] 范围内
 * 0 <= Node.val <= 104
 */
public class A0337HouseRobberⅢ {
    Map<TreeNode, Integer> doRob = new HashMap<>();
    Map<TreeNode, Integer> doNotRob = new HashMap<>();

    public int rob(TreeNode root) {
        dfs(root);
        return Math.max(doRob.getOrDefault(root, 0), doNotRob.getOrDefault(root, 0));
    }

    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        dfs(node.right);
        // 偷的话, 不偷左右节点的值的最优解
        int value = doNotRob.getOrDefault(node.left, 0) + doNotRob.getOrDefault(node.right, 0);
        doRob.put(node, node.val + value);
        // 不偷的话，看左右节点的最优解
        int leftValue = Math.max(doRob.getOrDefault(node.left, 0), doNotRob.getOrDefault(node.left, 0));
        int rightValue = Math.max(doRob.getOrDefault(node.right, 0), doNotRob.getOrDefault(node.right, 0));

        doNotRob.put(node, leftValue + rightValue);
    }

    public int rob2(TreeNode root) {
        int[] res = dfs2(root);
        // 根节点选或不选的最大值
        return Math.max(res[0], res[1]);
    }

    private int[] dfs2(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        // 递归左子树
        int[] left = dfs2(node.left);
        // 递归右子树
        int[] right = dfs2(node.right);
        // 选
        int rob = left[1] + right[1] + node.val;
        // 不选
        int notRob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return new int[]{rob, notRob};
    }

}
