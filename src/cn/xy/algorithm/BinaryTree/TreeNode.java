package cn.xy.algorithm.BinaryTree;

public class TreeNode {
    private TreeNode leftTreeNode;
    private TreeNode rightTreeNode;
    private int value;

    public TreeNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public TreeNode getLeftTreeNode() {
        return leftTreeNode;
    }

    public TreeNode getRightTreeNode() {
        return rightTreeNode;
    }

    public void setLeftTreeNode(TreeNode leftTreeNode) {
        this.leftTreeNode = leftTreeNode;
    }

    public void setRightTreeNode(TreeNode rightTreeNode) {
        this.rightTreeNode = rightTreeNode;
    }

    public static void main(String[] args) {
        //根节点-->10
        TreeNode treeNode1 = new TreeNode(10);

        //左孩子-->9
        TreeNode treeNode2 = new TreeNode(9);

        //右孩子-->20
        TreeNode treeNode3 = new TreeNode(20);

        //20的左孩子-->15
        TreeNode treeNode4 = new TreeNode(15);

        //20的右孩子-->35
        TreeNode treeNode5 = new TreeNode(35);

        //根节点的左右孩子
        treeNode1.setLeftTreeNode(treeNode2);
        treeNode1.setRightTreeNode(treeNode3);

        //20节点的左右孩子
        treeNode3.setLeftTreeNode(treeNode4);
        treeNode3.setRightTreeNode(treeNode5);


        int[] arrays = {2, 3, 1, 4, 5};
        TreeRoot treeRoot = new TreeRoot();
        for (int value : arrays) {
            createTree(treeRoot, value);
        }
        //inTraverseBTree(treeRoot.getTreeRoot());
        int height = getHeight(treeRoot.getTreeRoot());

        // System.out.println("____________________---"+treeRoot.getTreeRoot().getLeftTreeNode().getValue());;

        int max = getMax(treeRoot.getTreeRoot());
        System.out.println(max);
    }

    /**
     * 先序遍历
     *
     * @param rootTreeNode
     */
    public static void preTraverseBTree(TreeNode rootTreeNode) {
        if (rootTreeNode != null) {
            System.out.println(rootTreeNode.getValue());
            preTraverseBTree(rootTreeNode.getLeftTreeNode());
            preTraverseBTree(rootTreeNode.getRightTreeNode());

        }
    }

    /**
     * 中序遍历二叉树
     *
     * @param rootTreeNode
     */
    public static void inTraverseBTree(TreeNode rootTreeNode) {
        if (rootTreeNode != null) {
            inTraverseBTree(rootTreeNode.getLeftTreeNode());
            System.out.println(rootTreeNode.getValue());
            inTraverseBTree(rootTreeNode.getRightTreeNode());
        }
    }

    /**
     * 动态的创建二叉树
     */
    public static void createTree(TreeRoot treeRoot, int value) {
        if (treeRoot.getTreeRoot() == null) {
            TreeNode treeNode = new TreeNode(value);
            treeRoot.setTreeRoot(treeNode);
        } else {
            TreeNode tempRoot = treeRoot.getTreeRoot();
            while (tempRoot != null) {
                if (value > tempRoot.getValue()) {
                    if (tempRoot.getRightTreeNode() == null) {
                        tempRoot.setRightTreeNode(new TreeNode(value));
                        return;
                    } else {
                        tempRoot = tempRoot.getRightTreeNode();
                    }
                } else {
                    if (tempRoot.getLeftTreeNode() == null) {
                        tempRoot.setLeftTreeNode(new TreeNode(value));
                        return;
                    } else {
                        tempRoot = tempRoot.getLeftTreeNode();
                    }
                }
            }
        }
    }

    /**
     * 查询树的深度
     */

    public static int getHeight(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        } else {
            int left = getHeight(treeNode.getLeftTreeNode());
            int right = getHeight(treeNode.getRightTreeNode());


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
            int right = getMax(rootTreeNode.getRightTreeNode());
            int left = getMax(rootTreeNode.getLeftTreeNode());

            int currentRootValue = rootTreeNode.getValue();
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

}
