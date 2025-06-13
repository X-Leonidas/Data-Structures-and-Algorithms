package cn.xy.leetcode.easy.linkedList;


import cn.xy.utils.ListNode;
/*
 * [203] 移除链表元素
 * https://leetcode.cn/problems/remove-linked-list-elements/description/
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 *
 * 示例 1：
 * 输入：head = [1,2,6,3,4,5,6], val = 6
 * 输出：[1,2,3,4,5]
 *
 * 示例 2：
 * 输入：head = [], val = 1
 * 输出：[]
 *
 * 示例 3：
 * 输入：head = [7,7,7,7], val = 7
 * 输出：[]
 *
 * 提示：
 *
 * 列表中的节点数目在范围 [0, 10^4] 内
 * 1
 * 0
 */

public class A0203RemoveLinkedListElements {
    public ListNode removeElements(ListNode head, int val) {
        ListNode preHead = new ListNode(-1);
        preHead.next = head;
        ListNode curNode = head;
        ListNode preNode = preHead;
        while (curNode != null) {
            if (curNode.val == val) {
                preNode.next = curNode.next;

                ListNode next = curNode.next;
                curNode.next = null;
                curNode = next;
            } else {
                preNode = curNode;
                curNode = curNode.next;
            }
        }

        return preHead.next;
    }
}
