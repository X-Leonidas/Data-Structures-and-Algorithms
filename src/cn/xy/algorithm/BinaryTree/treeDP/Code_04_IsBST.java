package cn.xy.algorithm.BinaryTree.treeDP;

import cn.xy.utils.TreeNode;

/**
 * @author XiangYu
 * @create2021-03-29-22:57 怎么判断一棵树是否是平衡二叉树
 */
public class Code_04_IsBST {

    public static class ReturnData {
        //二叉树高度
        public int h;
        //是否平衡
        public Boolean isB;

        public ReturnData() {
        }

        public ReturnData(int h, Boolean isB) {
            this.h = h;
            this.isB = isB;
        }
    }

    public static Boolean isB(TreeNode head) {
        ReturnData data = process(head);
        return data.isB;
    }

    public static ReturnData process(TreeNode head) {
        if (head == null) {
            return new ReturnData(0, true);
        }

        ReturnData rightData = process(head.right);
        if (!rightData.isB) {
            return new ReturnData(0, false);
        }
        ReturnData leftData = process(head.left);
        if (!leftData.isB) {
            return new ReturnData(0, false);
        }

        if (Math.abs(leftData.h - rightData.h) > 1) {
            return new ReturnData(0, false);
        }
        return new ReturnData(Math.max(leftData.h, rightData.h), true);

    }

}
