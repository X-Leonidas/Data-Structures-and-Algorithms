package cn.xy.leetcode.middle.linkedlist;


import cn.xy.utils.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class A0086PartitionList {
    /**
     * Your runtime beats 3.26 % of java submissions
     * Your memory usage beats 50.96 % of java submissions (41.3 MB)
     *
     * @param head
     * @param x
     * @return
     */
    public ListNode partition1(ListNode head, int x) {
        Deque<ListNode> right = new ArrayDeque<>();
        Deque<ListNode> left = new ArrayDeque<>();

        while (head != null) {
            if (head.val < x) {
                left.addLast(head);
            } else {
                right.addLast(head);
            }
            head = head.next;
        }

        ListNode ans = left.peekFirst() == null ? right.peekFirst() : left.peekFirst();
        ListNode pre = left.peekLast();
        ListNode end = right.peekFirst();

        while (left.peekFirst() != null) {
            left.pollFirst().next = left.peekFirst();
        }

        while (right.peekFirst() != null) {
            right.pollFirst().next = right.peekFirst();
        }
        if (pre != null) {
            pre.next = end;
        }

        return ans;
    }

    /**
     * 优化后的写法
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 32.66 % of java submissions (41.4 MB)
     *
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        ListNode left = new ListNode(-1);
        ListNode right = new ListNode(-1);
        ListNode curLeft = left;
        ListNode curRight = right;

        while (head != null) {
            if (head.val < x) {
                curLeft.next = head;
                curLeft = head;
            } else {
                curRight.next = head;
                curRight = head;
            }
            head = head.next;
        }

        curLeft.next = right.next;
        curRight.next = null;
        return left.next;
    }
}
