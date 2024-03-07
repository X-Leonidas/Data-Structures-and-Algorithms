package cn.xy.leetcode.middle.linkedlist;

import cn.xy.utils.ListNode;

import java.util.ArrayList;

/**
 * @author XiangYu
 * @create2021-05-01-11:10 给你一个链表，删除链表的倒数第n个结点，并且返回链表的头结点。
 * <p>
 * 进阶：你能尝试使用一趟扫描实现吗？
 * <p>
 * 示例 1：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * <p>
 * 示例 2：
 * 输入：head = [1], n = 1
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 * <p>
 * 提示：
 * <p>
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 */
public class A0019RemoveNthNodeFromEndOfList {
    //使用liist
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode res = head;
        ArrayList<ListNode> listNodes = new ArrayList<>();
        while (res != null) {
            listNodes.add(res);
            res = res.next;
        }

        int length = listNodes.size();
        if (length == 1) {
            return null;
        }

        int index = length - n;
        if (index > 0 && index + 1 < length) {
            listNodes.get(index - 1).next = listNodes.get(index + 1);
        } else if (index == 0) {
            head = listNodes.get(index + 1);
        } else {
            listNodes.get(index - 1).next = null;
        }

        return head;
    }


    /**
     * 快慢指针
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode fast = head;
        ListNode slow = pre;

        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        // 因为pre是附加的一个，slow从pre开始，当fast走完时，最小也得差一个，所以slow.next 没有NPE的问题
        slow.next = slow.next.next;

        return pre.next;
    }
}
