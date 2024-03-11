package cn.xy.leetcode.middle.binarytree;

import cn.xy.utils.TreeNode;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 * @author XiangYu
 * @create2021-08-18-21:28
 */
public class A0098ValidateBinarySearchTree {
    long preValue = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        return midTraversal(root);
    }

    private boolean midTraversal(TreeNode root) {
        if (root == null) {
            return true;
        }

        if (!midTraversal(root.left)) {
            return false;
        }

        if (root.val <= preValue) {
            return false;
        }

        preValue = root.val;

        return midTraversal(root.right);
    }

    public boolean isValidBST2(TreeNode root) {
        return isValidBST2(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST2(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        return isValidBST2(node.left, lower, node.val) && isValidBST2(node.right, node.val, upper);
    }


}
