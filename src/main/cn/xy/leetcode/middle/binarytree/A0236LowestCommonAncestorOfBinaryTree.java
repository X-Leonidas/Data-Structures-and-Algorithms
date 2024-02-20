package cn.xy.leetcode.middle.binarytree;

import cn.xy.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author xiangyu
 * @date 2024-02-21 0:44
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，
 * 最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 */
public class A0236LowestCommonAncestorOfBinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p == q) {
            return p;
        }
        LinkedList<TreeNode> pDeque = new LinkedList<>();
        LinkedList<TreeNode> qDeque = new LinkedList<>();
        getStock(root, p, pDeque);
        getStock(root, q, qDeque);
        TreeNode result = null;
        while (pDeque.peekFirst() != null && pDeque.peekFirst() == qDeque.peekFirst()) {
            result = pDeque.pollFirst();
            qDeque.pollFirst();
        }
        return result;
    }


    public void getStock(TreeNode root, TreeNode p, LinkedList<TreeNode> linkedLis) {
        if (root == null) {
            return;
        }
        if (root == p) {
            linkedLis.offerLast(root);
            return;
        }
        linkedLis.offerLast(root);
        getStock(root.right, p, linkedLis);
        getStock(root.left, p, linkedLis);
        if (linkedLis.peekLast() != p) {
            linkedLis.pollLast();
        }
    }


    private TreeNode ans = null;

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }
        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.right, p, q);
        // lson && rson 说明在p,q各在当前root节点的左右子树上
        //((root.val == p.val || root.val == q.val) && (lson || rson))
        // 考虑的是 p或者q自身是公共祖先
        if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))) {
            ans = root;
        }
        return lson || rson || (root.val == p.val || root.val == q.val);
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        this.dfs(root, p, q);
        return this.ans;
    }

}
