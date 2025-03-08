package cn.xy.utils;

/**
 * @author xiangyu
 */
public class Node {
    public Node left;

    public Node right;

    public int val;

    public Node next;

    /**
     * 先序遍历二叉树
     *
     * @param rootTreeNode
     */
    public static void preTraverseBTree(Node rootTreeNode) {
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
    public static void inTraverseBTree(Node rootTreeNode) {
        if (rootTreeNode != null) {
            inTraverseBTree(rootTreeNode.getLeft());
            System.out.println(rootTreeNode.getVal());
            inTraverseBTree(rootTreeNode.getRight());
        }
    }

    /**
     * 后续遍历二叉树
     */
    public static void endTraverseBTree(Node rootTreeNode) {
        if (rootTreeNode != null) {
            inTraverseBTree(rootTreeNode.getLeft());
            inTraverseBTree(rootTreeNode.getRight());
            System.out.println(rootTreeNode.getVal());
        }
    }

    /**
     * 查询树的深度
     */

    public static int getHeight(Node treeNode) {
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
    public static int getMax(Node rootTreeNode) {
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


    public Node(int value) {
        this.val = value;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getNext() {
        return next;
    }
    public void setNext(Node next) {
        this.next = next;
    }
}
