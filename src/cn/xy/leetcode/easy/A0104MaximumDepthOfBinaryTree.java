package cn.xy.leetcode.easy;

import cn.xy.utils.TreeNode;

/**
 * @author XiangYu
 * @create2021-04-02-16:25 给定一个二叉树，找出其最大深度。
 * <p>
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回它的最大深度 3 。
 * <p>
 */
public class A0104MaximumDepthOfBinaryTree {


    public int maxDepth(TreeNode root) {
        if(root == null){
            return  0;
        }else {
            return process(root);
        }
    }

    public int process(TreeNode head) {
        if(null == head.left && null == head.right){
            return  1;
        }

        int rightH = 0;
        int lefrH = 0;
        if(null != head.left){
            lefrH = process(head.left);
        }
        if(null != head.right){
            rightH = process(head.right);
        }

        return Math.max(rightH,lefrH) + 1;

    }

}
