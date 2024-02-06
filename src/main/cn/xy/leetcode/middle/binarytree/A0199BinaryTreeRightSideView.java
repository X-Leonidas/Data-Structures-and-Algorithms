package cn.xy.leetcode.middle.binarytree;

import cn.xy.utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author xiangyu
 * @date 2024-02-06 22:55
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * 示例 1:
 * <p>
 * <p>
 * <p>
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1,3,4]
 * 示例 2:
 * <p>
 * 输入: [1,null,3]
 * 输出: [1,3]
 * 示例 3:
 * <p>
 * 输入: []
 * 输出: []
 * <p>
 * 二叉树的节点个数的范围是 [0,100]
 * -100 <= Node.val <= 100
 */
public class A0199BinaryTreeRightSideView {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        A0199BinaryTreeRightSideView a0199BinaryTreeRightSideView = new A0199BinaryTreeRightSideView();
        List<Integer> integers = a0199BinaryTreeRightSideView.rightSideView(root);
        System.out.println(integers);
    }

    /**
     * BFS
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        //思路：因为一层有且只能有一个节点被看到，因此可以遍历出每一层，选择最右边的节点加入答案
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                //将当前层的最后一个节点放入结果列表
                if (i == size - 1) {
                    res.add(node.val);
                }
            }
        }
        return res;
    }
}
