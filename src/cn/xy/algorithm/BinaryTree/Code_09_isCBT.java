package cn.xy.algorithm.BinaryTree;

import java.util.LinkedList;

/**
 * @author XiangYu
 * @create2021-04-01-22:17 判断一棵树是否为完全二叉树
 * <p>
 * 思路：
 * 满足一下条件则不是完全二叉树
 * 1） 有右子树，但是没有左子树
 * 2） 不违反一的情况下，如果左右孩子不全，则接下来的所有节点都应该为叶节点
 */
public class Code_09_isCBT {
    //TODO:力扣1361. 验证二叉树

    public static boolean isCBT(TreeNode head) {
        if (head == null) {
            return true;
        }
        //是否出现了只有左孩子的树
        boolean flag = false;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.push(head);
        TreeNode l = null;
        TreeNode r = null;
        while (!queue.isEmpty()) {
            head = queue.poll();
            r = head.right;
            l = head.left;
            if (   (flag && (l != null || r != null))   ||    (l == null && r != null)) {
                return false;
            }
            if (l != null) {
                queue.offer(l);
            }
            if (r != null) {
                queue.offer(r);
            } else {
                flag = true;
            }
        }

        return true;

    }


}
