package xy.algorithm.BinaryTree;

/**
 * @author XiangYu
 * @create2021-03-07-20:34
 *
 *
 * 已知一棵完全二叉树， 求其节点的个数
 * 要求： 时间复杂度低于O(N)， N为这棵树的节点个数
 *
 *
 *   因为是完全二叉树，所以树的的最大高度为该二叉树的最左节点的深度  获得高度L
 *      第一种情况：头节点的右子树的最左节点到了高度L. 可以获得头结点的左子树为满二叉树，
 *          可以通过2^(L-1) - 1 来获得头节点的左子树的节点个数。此时右子树为一个完全二
 *          叉树，递归调用继续求解
 *      第二种情况：头结点的右子树的左边界没达到高度L，则右子树一定为高度为L-2的满二叉树，
 *          左子树为一个完全二叉树，递归调用继续求解
 *
 *
 */
public class Code_06_CompleteTreeNodeNumber {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 时间复杂度为 O( (logN) ^ 2 )
     * @param head
     * @return
     */
    public static int nodeNum(Node head) {
        if (head == null) {
            return 0;
        }
        return bs(head, 1, mostLeftLevel(head, 1));
    }

    /**
     *
     * @param node  当前节点
     * @param l     当前节点在第几层
     * @param h     这个树的总高度
     * @return  以node为头的子树，返回该子树的节点个数
     */
    public static int bs(Node node, int l, int h) {
        if (l == h) {
            return 1;
        }
        //右子树最深的深度是否达到了最深深度
        if (mostLeftLevel(node.right, l + 1) == h) {
            //2^（h-l）-1 + 1 左子树节点个数+头节点     最后递归右子树
            return (1 << (h - l)) + bs(node.right, l + 1, h);
        } else {

            return (1 << (h - l - 1)) + bs(node.left, l + 1, h);
        }
    }

    /**
     * 获得最左节点的高度
     * @param node
     * @param level
     * @return
     */
    public static int mostLeftLevel(Node node, int level) {
        while (node != null) {
            level++;
            node = node.left;
        }
        return level - 1;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        System.out.println(nodeNum(head));

    }
}
