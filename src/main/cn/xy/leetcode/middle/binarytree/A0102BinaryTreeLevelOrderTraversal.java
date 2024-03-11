package cn.xy.leetcode.middle.binarytree;

import cn.xy.utils.TreeNode;

import java.util.*;

/**
 * @author xiangyu
 * @date 2024-03-11 21:44
 * 二叉树的层序遍历
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 */
public class A0102BinaryTreeLevelOrderTraversal {
    List<List<Integer>> ans;

    public List<List<Integer>> levelOrder(TreeNode root) {
        ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);
        bfs(queue);
        return ans;
    }

    public void bfs(Deque<TreeNode> deque) {
        if (deque.isEmpty()) {
            return;
        }
        Deque<TreeNode> nextDeque = new ArrayDeque<>();
        List<Integer> subAns = new ArrayList<>();
        while (!deque.isEmpty()) {
            TreeNode node = deque.pollFirst();
            subAns.add(node.val);
            if (node.left != null) {
                nextDeque.addLast(node.getLeft());
            }
            if (node.right != null) {
                nextDeque.addLast(node.getRight());
            }
        }
        ans.add(subAns);
        bfs(nextDeque);
    }

    /**
     * 使用迭代的方式实现
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int currentLevelSize = queue.size();
            for (int i = 1; i <= currentLevelSize; ++i) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ret.add(level);
        }

        return ret;
    }
}
