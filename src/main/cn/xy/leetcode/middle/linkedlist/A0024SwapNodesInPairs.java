package cn.xy.leetcode.middle.linkedlist;

import cn.xy.utils.ListNode;

/**
 * @author xiangyu
 * @date 2024-01-31 22:44
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 * <p>
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 * 示例 2：
 * <p>
 * 输入：head = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：head = [1]
 * 输出：[1]
 */
public class A0024SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode preHead = new ListNode(-1);
        preHead.next = head;
        ListNode lastNode = preHead;
        ListNode firstNode = head;
        ListNode secondNode = head.next;
        while (firstNode != null && secondNode != null) {
            ListNode nextNode = secondNode.next;
            firstNode.next = nextNode;
            secondNode.next = firstNode;
            lastNode.next = secondNode;

            lastNode = firstNode;
            firstNode = nextNode;
            if (firstNode != null) {
                secondNode = firstNode.next;
            }
        }

        return preHead.next;
    }

    /**
     * 官方题解
     *
     * @param head
     * @return
     */
    public ListNode swapPairs2(ListNode head) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode temp = pre;
        while (temp.next != null && temp.next.next != null) {
            ListNode start = temp.next;
            ListNode end = temp.next.next;
            // 将第二个连接过来
            temp.next = end;
            // 将第一个连接到后面
            start.next = end.next;
            // 将第二个连接到第一个
            end.next = start;
            // 拿到前一个，此时start已经是链表末尾了
            temp = start;
        }
        return pre.next;
    }
}
