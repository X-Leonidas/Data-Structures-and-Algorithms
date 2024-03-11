package cn.xy.leetcode.easy.tree;

import cn.xy.utils.TreeNode;

/**
 * @author XiangYu
 * @create2021-04-03-12:21 翻转一棵二叉树。
 * 示例：
 * <p>
 * 输入：
 * <p>
 * 4
 * /   \
 * 2     7
 * / \   / \
 * 1   3 6   9
 * 输出：
 * <p>
 * 4
 * /   \
 * 7     2
 * / \   / \
 * 9   6 3   1
 */
public class A0226InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode r = root.right;
        TreeNode l = root.left;
        root.left = r;
        root.right = l;

        if (root.left != null) {
            invertTree(root.left);
        }

        if (root.right != null) {
            invertTree(root.right);
        }
        return root;
    }


    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode l = invertTree2(root.left);
        TreeNode r = invertTree2(root.right);

        root.left = r;
        root.right = l;
        return root;
    }

    public TreeNode invertTree3(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree3(root.left);
        invertTree3(root.right);

        return root;
    }
}
