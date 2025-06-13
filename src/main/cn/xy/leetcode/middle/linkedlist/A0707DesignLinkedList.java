package cn.xy.leetcode.middle.linkedlist;

/**
 * [707] 设计链表
 * <p>
 * https://leetcode.cn/problems/design-linked-list/description/
 * 你可以选择使用单链表或者双链表，设计并实现自己的链表。
 * 单链表中的节点应该具备两个属性：val 和 next 。val 是当前节点的值，next 是指向下一个节点的指针/引用。
 * 如果是双向链表，则还需要属性prev以指示链表中的上一个节点。假设链表中的所有节点下标从 0 开始。
 * <p>
 * 实现 MyLinkedList 类：
 * MyLinkedList() 初始化 MyLinkedList 对象。
 * int get(int index) 获取链表中下标为 index 的节点的值。如果下标无效，则返回 -1 。
 * void addAtHead(int val) 将一个值为 val的节点插入到链表中第一个元素之前。在插入完成后，新节点会成为链表的第一个节点。
 * void addAtTail(int val) 将一个值为 val 的节点追加到链表中作为链表的最后一个元素。
 * void addAtIndex(int index, int val) 将一个值为 val 的节点插入到链表中下标为 index 的节点之前。如果
 * index 等于链表的长度，那么该节点会被追加到链表的末尾。如果 index 比长度更大，该节点将 不会插入 到链表中。
 * void deleteAtIndex(int index) 如果下标有效，则删除链表中下标为 index 的节点。
 * <p>
 * 示例：
 * 输入
 * ["MyLinkedList", "addAtHead", "addAtTail", "addAtIndex", "get",
 * "deleteAtIndex", "get"]
 * [[], [1], [3], [1, 2], [1], [1], [1]]
 * 输出
 * [null, null, null, null, 2, null, 3]
 * 解释
 * MyLinkedList myLinkedList = new MyLinkedList();
 * myLinkedList.addAtHead(1);
 * myLinkedList.addAtTail(3);
 * myLinkedList.addAtIndex(1, 2);    // 链表变为 1->2->3
 * myLinkedList.get(1);              // 返回 2
 * myLinkedList.deleteAtIndex(1);    // 现在，链表变为 1->3
 * myLinkedList.get(1);              // 返回 3
 * <p>
 * 提示：
 * 0 <= index, val <= 1000
 * 请不要使用内置的 LinkedList 库。
 * 调用 get、addAtHead、addAtTail、addAtIndex 和 deleteAtIndex 的次数不超过 2000 。
 *
 * @author xiangyu
 * @date 2025-06-13 22:30
 */
public class A0707DesignLinkedList {
    class MyLinkedList {

        class Node {
            Node prev, next;
            int val;

            Node(int _val) {
                val = _val;
            }
        }

        Node head = new Node(-1);
        Node tail = new Node(-1);
        int size = 0;

        public MyLinkedList() {
            head.next = tail;
            tail.prev = head;
        }

        public int get(int index) {
            Node node = getNode(index);
            return node == null ? -1 : node.val;
        }

        public void addAtHead(int val) {
            Node node = new Node(val);
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
            size++;
        }

        public void addAtTail(int val) {
            Node node = new Node(val);
            node.prev = tail.prev;
            node.next = tail;
            tail.prev.next = node;
            tail.prev = node;
            size++;
        }

        /**
         * 在第 index 个节点之前插入一个新节点，例如index为0，那么新插入的节点为第一个节点
         *
         * @param index
         * @param val
         */
        public void addAtIndex(int index, int val) {
            if (index > size)
                return;
            if (index <= 0) {
                addAtHead(val);
            } else if (index == size) {
                addAtTail(val);
            } else {
                Node node = new Node(val);
                Node curNode = getNode(index);
                node.next = curNode;
                node.prev = curNode.prev;
                curNode.prev.next = node;
                curNode.prev = node;
                size++;
            }
        }

        public void deleteAtIndex(int index) {
            Node cur = getNode(index);
            if (cur == null)
                return;
            cur.next.prev = cur.prev;
            cur.prev.next = cur.next;
            size--;
        }

        Node getNode(int index) {
            boolean isLeft = index < size / 2;

            if (!isLeft)
                index = size - index - 1;

            for (Node cur = isLeft ? head.next : tail.prev; cur != tail
                    && cur != head; cur = isLeft ? cur.next : cur.prev) {
                if (index-- == 0)
                    return cur;
            }
            return null;
        }
    }
}
