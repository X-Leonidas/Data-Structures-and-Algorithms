package cn.xy.algorithm.BinaryTree;

public class TreeNode {
    public TreeNode left;
    public TreeNode right;
    public int val;

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
        treeNode1.setLeft(treeNode2);
        treeNode1.setRight(treeNode3);

        //20节点的左右孩子
        treeNode3.setLeft(treeNode4);
        treeNode3.setRight(treeNode5);


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
     *  后续遍历二叉树
     */
    public static void endTraverseBTree(TreeNode rootTreeNode){

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
                if (value > tempRoot.getVal()) {
                    if (tempRoot.getRight() == null) {
                        tempRoot.setRight(new TreeNode(value));
                        return;
                    } else {
                        tempRoot = tempRoot.getRight();
                    }
                } else {
                    if (tempRoot.getLeft() == null) {
                        tempRoot.setLeft(new TreeNode(value));
                        return;
                    } else {
                        tempRoot = tempRoot.getLeft();
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

}
