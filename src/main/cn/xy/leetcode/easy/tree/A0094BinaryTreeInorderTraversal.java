package cn.xy.leetcode.easy.tree;

import cn.xy.utils.TreeNode;

import java.util.*;

/**
 * @author xiangyu
 * @date 2024-03-10 21:16
 * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
 * <p>
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 */
public class A0094BinaryTreeInorderTraversal {
    private List<Integer> result = null;

    public List<Integer> inorderTraversal(TreeNode root) {
        result = new ArrayList<>();
        traversal(root);
        return result;
    }

    private void traversal(TreeNode root) {
        if (root == null) {
            return;
        }
        traversal(root.left);
        result.add(root.val);
        traversal(root.right);
    }

    /**
     * 迭代算法 ,使用栈
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || root != null) {
            if(root != null){
                stack.push(root);
                root = root.left;
            }else{
                root = stack.pop();
                ans.add(root.val);
                root = root.right;
            }
        }
        return ans;
    }
}
