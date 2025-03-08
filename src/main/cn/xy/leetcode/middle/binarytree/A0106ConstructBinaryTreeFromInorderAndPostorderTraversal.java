package cn.xy.leetcode.middle.binarytree;
/*
 * [106] 从中序与后序遍历序列构造二叉树
 *
 * https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/
 *
 * 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder
 * 是同一棵树的后序遍历，请你构造并返回这颗二叉树。
 *
 * 示例 1:
 * 输入：inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 * 输出：[3,9,20,null,null,15,7]
 *
 * 示例 2:
 * 输入：inorder = [-1], postorder = [-1]
 * 输出：[-1]
 *
 * 提示:
 * 1 <= inorder.length <= 3000
 * postorder.length == inorder.length
 * -3000 <= inorder[i], postorder[i] <= 3000
 * inorder和postorder都由 不同 的值组成
 * postorder中每一个值都在inorder中
 * inorder保证是树的中序遍历
 * postorder保证是树的后序遍历
 */

// @lc code=start

import cn.xy.utils.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class A0106ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> cache = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            cache.put(inorder[i], i);
        }

        return buildTree(postorder, cache, 0, inorder.length - 1, 0, postorder.length - 1);
    }

    private TreeNode buildTree(int[] postorder, Map<Integer, Integer> cache, int pStart, int pEnd, int iStart,
                               int iEnd) {
        if (pStart > pEnd || iStart > iEnd) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[pEnd]);
        Integer iRootIndex = cache.get(postorder[pEnd]);

        // postorder 左子树长度由 iRootIndex -1  - iStart 得出  下表 得是 开始 + 长度

        root.left = buildTree(postorder, cache, pStart, pStart + iRootIndex - 1 - iStart, iStart, iRootIndex - 1);
        root.right = buildTree(postorder, cache, pStart + iRootIndex - iStart, pEnd - 1, iRootIndex + 1, iEnd);
        return root;
    }
}