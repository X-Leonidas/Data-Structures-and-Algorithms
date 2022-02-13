package cn.xy.leetcode.easy.linkedList;

import cn.xy.utils.ListNode;

/**
 * @author XiangYu
 * @create2021-04-07-23:41
 *
 *   将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * 示例 2：
 *
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 *  
 *
 * 提示：
 *
 * 两个链表的节点数目范围是 [0, 50]
 * -100 <= Node.val <= 100
 * l1 和 l2 均按 非递减顺序 排列
 *
 */
public class A0021MergeTwoSortedLists {

    /**
     * 官方递归写法
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }

    }


    /**
     * 自己的写法
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if(l1 == null && l2 == null){
            return null;
        }
        ListNode head  = null;

        if(l1 == null){
            return l2;
        }else if(l2  == null){
            return l1;
        }else if(l1.val < l2.val){
            head  = l1;
            l1 = l1.next;
        }else{
            head = l2;
            l2  = l2.next;
        }

        ListNode cur = head;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                ListNode temp = l1.next;
                cur.next = l1;
                l1.next  = null;
                l1 = temp;
            }else{
                ListNode temp = l2.next;
                cur.next = l2;
                l2.next = null;
                l2 = temp;
            }
            cur = cur.next;
        }

        cur.next = l1 == null ? l2 : l1;

        return head;
    }
}
