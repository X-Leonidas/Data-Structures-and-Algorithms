package cn.xy.leetcode.middle.binarytree;

import cn.xy.utils.TreeNode;

/**
 * @author xiangyu
 * @date 2025-03-08 22:52
 * <p>
 * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和 。
 * <p>
 * 叶节点 是指没有子节点的节点。
 * <p>
 * 示例 1：
 * 输入：root = [1,2,3]
 * 输出：25
 * 解释：
 * 从根到叶子节点路径 1->2 代表数字 12
 * 从根到叶子节点路径 1->3 代表数字 13
 * 因此，数字总和 = 12 + 13 = 25
 * <p>
 * 示例 2：
 * 输入：root = [4,9,0,5,1]
 * 输出：1026
 * 解释：
 * 从根到叶子节点路径 4->9->5 代表数字 495
 * 从根到叶子节点路径 4->9->1 代表数字 491
 * 从根到叶子节点路径 4->0 代表数字 40
 * 因此，数字总和 = 495 + 491 + 40 = 1026
 * <p>
 * 提示：
 * 树中节点的数目在范围 [1, 1000] 内
 * 0 <= Node.val <= 9
 * 树的深度不超过 10
 */
public class A0129SumRootToLeafNumbers {
    private int sum = 0;
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        sumNumbers(root, root.val);

        return sum;
    }

    private void sumNumbers(TreeNode root, int curVal) {
         if(root.left == null && root.right == null){
              sum = sum + curVal;
         }

         if(root.left != null){
             sumNumbers(root.left, (curVal * 10+ root.left.val));
         }
         if(root.right != null){
             sumNumbers(root.right, (curVal * 10 + root.right.val) );
         }
    }

    /**
     *     优化一下不使用类变量
      */
    private int sumNumbers2(TreeNode root, int prevSum) {
        if(root == null){
            return 0;
        }

        int sum = prevSum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        } else {
            return sumNumbers2(root.left, sum) + sumNumbers2(root.right, sum);
        }
    }
}
