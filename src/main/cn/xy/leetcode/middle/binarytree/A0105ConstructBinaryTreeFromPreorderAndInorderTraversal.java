package cn.xy.leetcode.middle.binarytree;

import cn.xy.utils.TreeNode;

import java.util.HashMap;

/**
 * @author xiangyu
 * @date 2024-02-20 23:15
 * <p>
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历，
 * inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 */
public class A0105ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};

        A0105ConstructBinaryTreeFromPreorderAndInorderTraversal unit = new A0105ConstructBinaryTreeFromPreorderAndInorderTraversal();
        TreeNode treeNode = unit.buildTree(preorder, inorder);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildHelper(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    private TreeNode buildHelper(int[] preorder, int pStart, int pEnd, int[] inorder, int iStart, int iEnd) {
        if (pStart == pEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[pStart]);
        int iRootIndex = 0;
        for (int i = iStart; i < iEnd; i++) {
            if (root.val == inorder[i]) {
                iRootIndex = i;
                break;
            }
        }
        // 左树还有多少个元素
        int leftNum = iRootIndex - iStart;

        root.left = buildHelper(preorder, pStart + 1, pStart + leftNum + 1, inorder, iStart, iRootIndex);
        //pStart + leftNum + 1 跳过左树的元素
        root.right = buildHelper(preorder, pStart + leftNum + 1, pEnd, inorder, iRootIndex + 1, iEnd);
        return root;
    }

    // ----------------- 针对不重复的值，使用 map，减少查找时间

    HashMap<Integer,Integer> map = new HashMap<>();
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildHelper2(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    private TreeNode buildHelper2(int[] preorder, int pStart, int pEnd, int[] inorder, int iStart, int iEnd) {
        if (pStart == pEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[pStart]);
        int iRootIndex = map.get(root.val);

        // 左树还有多少个元素
        int leftNum = iRootIndex - iStart;

        root.left = buildHelper(preorder, pStart + 1, pStart + leftNum + 1, inorder, iStart, iRootIndex);
        //pStart + leftNum + 1 跳过左树的元素
        root.right = buildHelper(preorder, pStart + leftNum + 1, pEnd, inorder, iRootIndex + 1, iEnd);
        return root;
    }
}
