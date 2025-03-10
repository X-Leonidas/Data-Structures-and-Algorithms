package cn.xy.leetcode.hard.binarytree;
/*
 * [124] 二叉树中的最大路径和
 * https://leetcode.cn/problems/binary-tree-maximum-path-sum/description/
 *
 * 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个
 * 节点，且不一定经过根节点。
 * 
 * 路径和 是路径中各节点值的总和。
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 *
 * 示例 1：
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
 * 
 * 示例 2：
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 *
 * 提示：
 * 树中节点数目范围是 [1, 3 * 10^4]
 * -1000 <= Node.val <= 1000
 */

import cn.xy.utils.TreeNode;

// @lc code=start
public class BinaryTreeMaximumPathSum {

    public int sum = 0;

    public int maxPathSum(TreeNode root) {
        if (null == root) {
            return 0;
        }

        sum = root.val;

        sumPath(root);

        return sum;
    }

    public int sumPath(TreeNode root){
        if(root == null){
            return 0;
        }

        int rightSum = sumPath(root.right);
        int leftSum = sumPath(root.left);
        
        if(rightSum <= 0 && leftSum <= 0){
            sum = Math.max(sum, root.val);
            return root.val;
        }else if (rightSum <= 0){
            sum = Math.max(sum, root.val + leftSum);
            return root.val + leftSum;
        }else if (leftSum <= 0){
            sum = Math.max(sum, root.val + rightSum);
            return root.val + rightSum;
        }else {
            sum = Math.max(sum, root.val + leftSum + rightSum);
            if(rightSum > leftSum){
                return root.val + rightSum;
            }else{
                return root.val + leftSum;
            }
        }
    }
}