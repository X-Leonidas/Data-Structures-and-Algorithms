package cn.xy.leetcode.middle.binarytree;

import cn.xy.utils.TreeNode;

import java.util.Stack;

/**
 * @author xiangyu
 * @date 2024-02-06 22:08
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 * <p>
 * 提示：
 * <p>
 * 树中的节点数为 n 。
 * 1 <= k <= n <= 104
 * 0 <= Node.val <= 104
 */
public class A0230KthSmallestElementInBST {
    public int kthSmallest(TreeNode root, int k) {
        // 中序遍历然后计数
        TreeNode head = root;
        int curIndex = 0;
        if (head != null) {
            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    curIndex++;
                    if (curIndex == k) {
                        return head.val;
                    }
                    head = head.right;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3);
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(4);
        TreeNode treeNode3 = new TreeNode(2);
        treeNode.left = treeNode1;
        treeNode.right = treeNode2;
        treeNode1.right = treeNode3;

    }

    private int res = -1;
    private int k = -1;

    public int kthSmallest2(TreeNode root, int k) {
        this.k = k;
        // 中序遍历然后计数
        inOrderTraversal(root);
        return res;
    }

    public void inOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left);
        if (k == 0) {
            return;
        }
        --k;
        if (k == 0) {
            res = root.val;
        }
        inOrderTraversal(root.right);
    }

}
