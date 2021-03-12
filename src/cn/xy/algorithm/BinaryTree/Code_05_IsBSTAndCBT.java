package cn.xy.algorithm.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author XiangYu
 * @create2021-03-07-19:59
 *
 *
 * 如何判断搜索二叉树和完全二叉树
 *
 *   搜索二叉树BST：中序遍历是左中右的顺序 节点的值是递增的
 *
 *
 *   完全二叉树 CBT
 *      需要按层遍历
 *      + 如果一个节点有右子节点 但是没有左子节点，则它一定不是
 *      + 如果一个节点有左节点或左右节点都没有，则后续遍历的节点都必须是叶节点，不能是根节点
 *
 */
public class Code_05_IsBSTAndCBT {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     *
     * @param head
     * @return
     *
     * 使用Morris方法遍二叉树
     */
    public static boolean isBST(Node head) {
        if (head == null) {
            return true;
        }
        boolean res = true;
        Node pre = null;
        Node cur1 = head;
        Node cur2 = null;
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                if (cur2.right == null) {
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                }
            }
            if (pre != null && pre.value > cur1.value) {
                res = false;
            }
            pre = cur1;
            cur1 = cur1.right;
        }
        return res;
    }

    public static boolean isCBT(Node head) {
        if (head == null) {
            return true;
        }
        Queue<Node> queue = new LinkedList<Node>();
        boolean leaf = false;
        Node l = null;
        Node r = null;
        queue.offer(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;
            //开启叶节点判断后，每个节点都必须是叶节点  ||  左子节点为空，右子节点不为空
            if ((leaf && (l != null || r != null)) || (l == null && r != null)) {
                return false;
            }

//             等效代码
//            if (l != null) {
//                queue.offer(l);
//            }
//            if (r != null) {
//                queue.offer(r);
//            }
//            上面已经排除了左空右有的情况，所以现在只有左空右空和左有右空的情况
//            if(l ==null || r == null){
//                leaf = true;
//            }


            if (l != null) {
                queue.offer(l);
            }
            if (r != null) {
                queue.offer(r);
            } else {
                leaf = true;
            }
        }
        return true;
    }

    // for test -- print tree
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);

        printTree(head);
        System.out.println(isBST(head));
        System.out.println(isCBT(head));

    }
}
