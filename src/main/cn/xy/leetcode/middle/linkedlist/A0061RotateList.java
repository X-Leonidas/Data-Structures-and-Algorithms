package cn.xy.leetcode.middle.linkedlist;/*
 * @lc app=leetcode.cn id=61 lang=java
 *
 * [61] 旋转链表
 * https://leetcode.cn/problems/rotate-list/description/

 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动k个位置。
 * 
 * 示例 1：
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 *
 * 示例 2：
 * 输入：head = [0,1,2], k = 4
 * 输出：[2,0,1]
 * 
 * 提示：
 * 链表中节点的数目在范围 [0, 500] 内
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 10^9
 */


import cn.xy.utils.ListNode;

import java.util.ArrayList;
import java.util.List;

public class A0061RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }

        List<ListNode> cache = new ArrayList<>();
        ListNode curNode = head;
        while (curNode != null) {
            cache.add(curNode);
            curNode = curNode.next;
        }

        k = k % cache.size();
        if (k == 0) {
            return head;
        }

        ListNode listHead = cache.get(0);
        ListNode listEnd = cache.get(cache.size() - 1);

        ListNode cur = cache.get(cache.size() -k);
        ListNode pre = cache.get(cache.size() - k - 1);

        pre.next = null;
        listEnd.next = listHead;

        return cur;
    }
}