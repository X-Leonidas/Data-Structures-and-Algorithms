package cn.xy.algorithm.BinaryTree;

/**
 * @author XiangYu
 * @create2021-03-24-20:35 Morris遍历
 * 空间复杂度 O(1)
 *
 *
 *     遍历标准：   当前节点为cur
 *      1. cur没有左子树，cue向右子树 移动(cur = cur.right)
 *      2. cur存在左子树，找到左子树上最右的节点 记为mostright
 *          2.1 mostright的右子树为空， 则设置右子树指向cur,然后cur向左移动
 *          2.2 mostright的右子树为cur,让其指向空，cur向右移动
 *
 */
public class Code_08_Morris {


    public static void process(Node head) {
        if (head == null) {
            return;
        }
        // 1
        //System.out.println(head.value);
        process(head.left);
        // 2
        //System.out.println(head.value);
        process(head.right);
        // 3
        //System.out.println(head.value);
    }


    public static class Node {
        public int value;
        Node left;
        Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     *
     * @param head
     * 中序遍历
     */
    public static void morrisIn(Node head) {
        if (head == null) {
            return;
        }
        //cur
        Node cur1 = head;
        //mostright
        Node mostright = null;

        while (cur1 != null) {
            //临时的,节省变量
            mostright = cur1.left;
            //当左孩子不等于空时，找到mostright
            if (mostright != null) {
                //找到mostright     mostright.right != cur1 指向当前节点的情况
                while (mostright.right != null && mostright.right != cur1) {
                    mostright = mostright.right;
                }

                //右子树为空，设置mostRight的右子树指向cur,然后cur向右移动
                if (mostright.right == null) {
                    mostright.right = cur1;
                    cur1 = cur1.left;
                    continue;
                } else {
                    //指向空，cur向右移动在86行
                    mostright.right = null;
                }
            }
            System.out.print(cur1.value + " ");
            //当cur左子树为空时，cur向右移动
            cur1 = cur1.right;
        }
        System.out.println();
    }

    public static void morrisPre(Node head) {
        if (head == null) {
            return;
        }
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
                    System.out.print(cur1.value + " ");
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                }
            } else {
                System.out.print(cur1.value + " ");
            }
            cur1 = cur1.right;
        }
        System.out.println();
    }





    /**
     *
     * @param head
     * 后序遍历，只关注可以到达两次的节点，当第二次到达时，逆序打印左子树右边界，
     * 打印完成后，在突出函数前，单独打印整个树的右边界
     */
    public static void morrisPos(Node head) {
        if (head == null) {
            return;
        }
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
                    printEdge(cur1.left);
                }
            }
            cur1 = cur1.right;
        }
        printEdge(head);
        System.out.println();
    }

    public static void printEdge(Node head) {
        Node tail = reverseEdge(head);
        Node cur = tail;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    public static Node reverseEdge(Node from) {
        Node pre = null;
        Node next = null;
        while (from != null) {
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return pre;
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
        head.right.right = new Node(7);
        printTree(head);
        morrisIn(head);
        morrisPre(head);
        morrisPos(head);
        printTree(head);

    }
}
