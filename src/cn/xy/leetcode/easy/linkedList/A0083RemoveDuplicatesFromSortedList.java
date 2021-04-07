package cn.xy.leetcode.easy.linkedList;

import cn.xy.codinginterviews.ListNode;

/**
 * @author XiangYu
 * @create2021-04-07-9:46
 *
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
 *
 * 返回同样按升序排列的结果链表。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,1,2]
 * 输出：[1,2]
 * 示例 2：
 *
 *
 * 输入：head = [1,1,2,3,3]
 * 输出：[1,2,3]
 *
 */
public class A0083RemoveDuplicatesFromSortedList {
    //忽视了升序的条件！！！！
    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;
        ListNode index;

        ListNode per;

        while (cur  != null){
            index = cur.next;
            per =cur;
            while(index != null){
                ListNode temp = index.next;
                if(index.val == cur.val){
                    index.next = null;
                    per.next = temp;
                }else{
                    per = index;
                }

                index = temp;
            }
            cur = cur.next;
        }

        return head;
    }


    public ListNode deleteDuplicates2(ListNode head) {


        if (head == null) {
            return head;
        }

        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }

        return head;
    }
}
