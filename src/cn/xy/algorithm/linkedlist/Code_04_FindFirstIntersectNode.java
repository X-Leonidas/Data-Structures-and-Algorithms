package cn.xy.algorithm.linkedlist;

/**
 * @author XiangYu
 * @create2021-03-03-17:44
 *
 *
 *两个单链表相交的一系列问题
 * 【 题目】 在本题中， 单链表可能有环， 也可能无环。 给定两个单链表的头节点 head1和head2， 这两个链表可能相交， 也可能
 * 不相交。 请实现一个函数， 如果两个链表相交， 请返回相交的第一个节点； 如果不相交， 返回null 即可。 要求： 如果链表1
 * 的长度为N， 链表2的长度为M， 时间复杂度请达到 O(N+M)， 额外空间复杂度请达到O(1)。
 *
 *
 *
 * 思路：
 *  一. 首先判断是否有环
 *          1. 使用hash表，每取出一个元素，在hashset中查看是否已经存在，如果存在则说明有环并且该节点为入环的第一个节点loop
 *          2. 不适用额外空间，使用两个快慢指针，慢指针每次遍历一个，快指针每次遍历两个，如果有环，则必定会在某个节点相遇，
 *             相遇后，快指针返回头节点并且变为每次遍历一个与慢指针继续遍历节点，直到再次相遇，则相遇的节点为入环的第一个
 *             节点loop，这个为已经证明的理论
 *
 *  二. 两个无环链表寻找相交的点
 *          1. 使用hash表，其中一个放入hashset中，遍历第二个链表，每取出一个元素就与set中进行比较
 *          2. 拿到两个链表的长度length1、length2和尾节点end1、end2，如果end1 != end2 则两个链表不相交，如果相等，则拿到长
 *             度差值diff，长度较长的链表先遍历diff长度，然后两个链表一起遍历，直到拿到第一个内存地址相等的节点
 *
 *  三. 一个无环单链表和一个有环单链表不存在交点 因为都是单链表
 *
 *  四. 两个有环单链表
 *          1. loop1的内存地址 == loop2的内存地址
 *             说明是两个链表在一个节点接入环中，则可以抽象为两个无环链表寻找相交的点
 *          2. loop1的内存地址 != loop2的内存地址
 *              说明两个链表是两个不同的节点接入环中，从loop1节点遍历 寻找loop2,如果找到了 返回loop1和loop2d都可以，如果没找到
 *              则说明两个环没有相交
 */
public class Code_04_FindFirstIntersectNode {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }


    /**
     * 返回第一个相交的节点
     * @param head1
     * @param head2
     * @return
     */
    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if (loop1 == null && loop2 == null) {
            //无环链表相交的情况
            return noLoop(head1, head2);
        }
        if (loop1 != null && loop2 != null) {
            //有环链表相交的情况
            return bothLoop(head1, loop1, head2, loop2);
        }

        //一个有环 一个没环  不可能相交返回null
        return null;
    }

    /**
     * 获得第一个入环的节点
     * @param head
     * @return
     */
    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node n1 = head.next; // n1 -> slow
        Node n2 = head.next.next; // n2 -> fast
        while (n1 != n2) {
            if (n2.next == null || n2.next.next == null) {
                return null;
            }
            n2 = n2.next.next;
            n1 = n1.next;
        }
        n2 = head; // n2 -> walk again from head
        while (n1 != n2) {
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1;
    }

    /**
     * 无环找到第一个相交的节点
     * @param head1
     * @param head2
     * @return
     */
    public static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        //获得差值
        int n = 0;
        while (cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }

        //如果最后一个节点都不相同 则说明两个单链表不相交
        if (cur1 != cur2) {
            return null;
        }

        //定位两个链表谁长谁短
        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);
        while (n != 0) {
            n--;
            cur1 = cur1.next;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }


    /**
     *  两个有环链表相交的问题
     * @param head1
     * @param loop1
     * @param head2
     * @param loop2
     * @return
     */
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = null;
        Node cur2 = null;
        //可以简化为两个单链表相交的问题
        if (loop1 == loop2) {
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            //loop1k开始转，当转到loop1 == loop2时，则相交，否则不相交 返回null
            cur1 = loop1.next;
            while (cur1 != loop1) {
                if (cur1 == loop2) {
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

    }
}
