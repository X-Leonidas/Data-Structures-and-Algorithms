package cn.xy.leetcode.middle.binarytree;

import cn.xy.utils.TreeNode;
import cn.xy.utils.Utils;

import java.util.*;

/**
 * @author xiangyu
 * @date 2024-02-07 0:35
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * <p>
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 * <p>
 * <p>
 * 输入：root = [1,2,5,3,4,null,6]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]
 * 示例 2：
 * <p>
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：root = [0]
 * 输出：[0]
 */
public class A0114FlattenBinaryTreeToLinkedList {



    public static void main(String[] args) {
        TreeNode treeNode = Utils.buildTreeFromDFS(Arrays.asList(1, 2, 5, 3, 4, null, 6));
        new A0114FlattenBinaryTreeToLinkedList().flatten3(treeNode);
    }


    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        ArrayList<TreeNode> resultList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            resultList.add(root);
            if (root.right != null) {
                stack.push(root.right);
            }
            if (root.left != null) {
                stack.push(root.left);
            }
        }
        TreeNode preHead = new TreeNode(-1);
        TreeNode cur = preHead;
        for (TreeNode treeNode : resultList) {
            cur.right = treeNode;
            treeNode.left = null;
            cur = treeNode;
        }
        root = preHead.right;
    }

    public void flatten2(TreeNode root) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        TreeNode prev = null;
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            if (prev != null) {
                prev.left = null;
                prev.right = curr;
            }
            TreeNode left = curr.left;
            TreeNode right = curr.right;
            if (right != null) {
                stack.push(right);
            }
            if (left != null) {
                stack.push(left);
            }
            prev = curr;
        }
    }

    /**
     * 使用常数空间的算法
     * @param root
     */
    public void flatten3(TreeNode root) {
        if(root == null){
            return;
        }
        adjustNode(root);
    }

    private TreeNode  adjustNode(TreeNode root){
        if(root == null){
            return null;
        }
        TreeNode left =  adjustNode(root.left);
        TreeNode right = adjustNode(root.right);

        if(left != null){
            left.right = root.right;
            root.right = root.left;
            root.left = null;
        }

        if(right != null){
            return right;
        }else if (left != null){
            return left;
        }else{
            return root;
        }
    }

}
