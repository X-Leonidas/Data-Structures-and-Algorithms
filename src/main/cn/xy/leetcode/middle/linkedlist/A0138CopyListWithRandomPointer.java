package cn.xy.leetcode.middle.linkedlist;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author xiangyu
 * @date 2024-02-05 22:30
 * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
 * <p>
 * 构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。
 * 新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。
 * 复制链表中的指针都不应指向原链表中的节点 。
 * <p>
 * 例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。
 * <p>
 * 返回复制链表的头节点。
 * <p>
 * 用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
 * <p>
 * val：一个表示 Node.val 的整数。
 * random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
 * 你的代码 只 接受原链表的头节点 head 作为传入参数。
 */
public class A0138CopyListWithRandomPointer {

    public static void main(String[] args) {
        Node node = new Node(7);
        Node node1 = new Node(13);
        node.next = node1;
        Node node2 = new Node(11);
        node1.next = node2;
        Node node3 = new Node(10);
        node2.next = node3;
        Node node4 = new Node(1);
        node3.next = node4;

        node1.random = node;
        node2.random = node4;
        node3.random = node2;
        node4.random = node2;

        copyRandomList(node);
    }

    /**
     * 推荐写法
     * @param head
     * @return
     */
    public static Node copyRandomList(Node head) {
        if (null == head) {
            return null;
        }
        HashMap<Node, Node> cache = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            cache.put(cur, new Node(cur.val));
            cur = cur.next;
        }

        cur = head;
        while (cur != null) {
            cache.get(cur).next = cache.get(cur.next);
            cache.get(cur).random = cache.get(cur.random);
            cur = cur.next;
        }

        return cache.get(head);
    }

    /**
     * 自己写的
     * @param head
     * @return
     */
    public static Node copyRandomList2(Node head) {
        if (null == head) {
            return null;
        }
        HashMap<Node, Node> cache = new HashMap<>();
        Node preResult = new Node(-1);
        Node preNode = preResult;
        Node preHead = head;
        while (head != null) {
            preNode.next = new Node(head.val);
            cache.put(head, preNode.next);
            preNode = preNode.next;
            head = head.next;
        }
        preNode = preResult.next;
        head = preHead;
        while (head != null) {
            if (head.random != null) {
                preNode.random = cache.get(head.random);
            }
            preNode = preNode.next;
            head = head.next;
        }

        return preResult.next;
    }

    /**
     * 时间复杂度 O(1)
     * 考虑构建 原节点 1 -> 新节点 1 -> 原节点 2 -> 新节点 2 -> …… 的拼接链表，
     * 如此便可在访问原节点的 random 指向节点的同时找到新对应新节点的 random 指向节点。
     * @param head
     * @return
     */
    public Node copyRandomList3(Node head) {
        if(head == null) return null;
        Node cur = head;
        // 1. 复制各节点，并构建拼接链表

        while(cur != null) {
            Node tmp = new Node(cur.val);
            // 中间插入一个新的节点
            tmp.next = cur.next;
            cur.next = tmp;
            // 下一次操作
            cur = tmp.next;
        }
        // 2. 构建各新节点的 random 指向
        cur = head;
        while(cur != null) {
            if(cur.random != null){
                // 当前节点的复制节点的random节点则是当前节点的random的复制节点
                cur.next.random = cur.random.next;
            }

            cur = cur.next.next;
        }
        // 3. 拆分两链表
        // cur 代表复制节点
        cur = head.next;
        Node pre = head, res = head.next;
        // 还有其他的复制节点
        while(cur.next != null) {
            // 原节点
            pre.next = pre.next.next;
            // 复制节点
            cur.next = cur.next.next;

            pre = pre.next;
            cur = cur.next;
        }
        // 单独处理原链表尾节点
        pre.next = null;
        // 返回新链表头节点
        return res;
    }


    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}


