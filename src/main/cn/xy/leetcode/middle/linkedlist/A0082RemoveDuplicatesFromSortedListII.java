package cn.xy.leetcode.middle.linkedlist;/*
 * [82] 删除排序链表中的重复元素 II
 *
 * 给定一个已排序的链表的头head ，删除原始链表中所有重复数字的节点，只留下不同的数字。返回 已排序的链表。
 * 
 * 示例 1：
 * 输入：head = [1,2,3,3,4,4,5]
 * 输出：[1,2,5]
 *
 * 示例 2：
 * 输入：head = [1,1,1,2,3]
 * 输出：[2,3]
 * 
 * 链表中节点数目在范围 [0, 300] 内
 * -100 <= Node.val <= 100
 * 题目数据保证链表已经按升序 排列
 */

import cn.xy.utils.ListNode;

public class A0082RemoveDuplicatesFromSortedListII {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode preHead = new ListNode(-300, head);
        ListNode preNode = preHead;
        ListNode curNode = head;

        while(curNode != null){
            boolean flag = false;
            while(curNode != null && curNode.next != null && curNode.val == curNode.next.val){
                flag = true;
                curNode = curNode.next;
            }

            if(flag){
                preNode.next = curNode.next;
            }else{
                preNode = curNode;
            }

            curNode = curNode.next;

        }

        return preHead.next;
    }

    /**
     * 官方写法 双指针
     * @param head
     * @return
     */
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode dummy = new ListNode(0, head);

        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int x = cur.next.val;
                // 只要下一个的值为x 则去掉
                while (cur.next != null && cur.next.val == x) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }

        return dummy.next;
    }
}