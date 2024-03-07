package cn.xy.leetcode.easy.linkedList;

import cn.xy.utils.ListNode;

/**
 * @author XiangYu
 * @create2021-04-07-23:41 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * <p>
 * 示例 1：
 * <p>
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * 示例 2：
 * <p>
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 * <p>
 * 提示：
 * 两个链表的节点数目范围是 [0, 50]
 * -100 <= Node.val <= 100
 * l1 和 l2 均按 非递减顺序 排列
 */
public class A0021MergeTwoSortedLists {

    /**
     * 官方递归写法
     *
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
     * 官方非递归写法，性能会更好一点
     *
     * @param node1
     * @param node2
     * @return
     */
    public ListNode mergeTwoLists2(ListNode node1, ListNode node2) {
        if (node1 == null || node2 == null) {
            return node1 != null ? node1 : node2;
        }
        ListNode head = new ListNode(0);
        ListNode cur = head,
                curNode1 = node1,
                curNode2 = node2;
        while (curNode1 != null && curNode2 != null) {
            if (curNode1.val < curNode2.val) {
                cur.next = curNode1;
                curNode1 = curNode1.next;
            } else {
                cur.next = curNode2;
                curNode2 = curNode2.next;
            }
            cur = cur.next;
        }
        cur.next = (curNode1 != null ? curNode1 : curNode2);
        return head.next;
    }

    /**
     * 自己的写法
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists3(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        ListNode head = null;

        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            head = l1;
            l1 = l1.next;
        } else {
            head = l2;
            l2 = l2.next;
        }

        ListNode cur = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                ListNode temp = l1.next;
                cur.next = l1;
                l1.next = null;
                l1 = temp;
            } else {
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
