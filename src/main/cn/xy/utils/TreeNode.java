package cn.xy.utils;

public class TreeNode {
    public TreeNode left;
    public TreeNode right;
    public int val;

    /**
     * 先序遍历二叉树
     *
     * @param rootTreeNode
     */
    public static void preTraverseBTree(TreeNode rootTreeNode) {
        if (rootTreeNode != null) {
            System.out.println(rootTreeNode.getVal());
            preTraverseBTree(rootTreeNode.getLeft());
            preTraverseBTree(rootTreeNode.getRight());

        }
    }

    /**
     * 中序遍历二叉树
     *
     * @param rootTreeNode
     */
    public static void inTraverseBTree(TreeNode rootTreeNode) {
        if (rootTreeNode != null) {
            inTraverseBTree(rootTreeNode.getLeft());
            System.out.println(rootTreeNode.getVal());
            inTraverseBTree(rootTreeNode.getRight());
        }
    }

    /**
     * 后续遍历二叉树
     */
    public static void endTraverseBTree(TreeNode rootTreeNode) {
        if (rootTreeNode != null) {
            inTraverseBTree(rootTreeNode.getLeft());
            inTraverseBTree(rootTreeNode.getRight());
            System.out.println(rootTreeNode.getVal());
        }
    }

    /**
     * 查询树的深度
     */

    public static int getHeight(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        } else {
            int left = getHeight(treeNode.getLeft());
            int right = getHeight(treeNode.getRight());


            int max = right;

            if (max < left) {
                max = left;
            }
            max += 1;
            return max;
        }
    }

    /**
     * 查询树的最大值
     */
    public static int getMax(TreeNode rootTreeNode) {
        if (rootTreeNode == null) {
            return -1;
        } else {
            int right = getMax(rootTreeNode.getRight());
            int left = getMax(rootTreeNode.getLeft());

            int currentRootValue = rootTreeNode.getVal();
            int max = right;
            if (left > left) {
                max = left;
            }

            if (max < currentRootValue) {
                max = currentRootValue;
            }
            return max;


        }
    }


    public TreeNode(int value) {
        this.val = value;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

}
