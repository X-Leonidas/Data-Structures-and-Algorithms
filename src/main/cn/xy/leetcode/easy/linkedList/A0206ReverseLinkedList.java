package cn.xy.leetcode.easy.linkedList;

import cn.xy.utils.ListNode;

/**
 * @author xiangyu
 * @date 2024-03-07 20:25
 * <p>
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 */
public class A0206ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        ListNode now = head;
        ListNode pre = null;
        while(now != null){
            ListNode temp = now.next;
            now.next = pre;
            pre = now;
            now = temp;
        }
        return pre;
    }


}
