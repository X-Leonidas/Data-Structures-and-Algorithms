package cn.xy.leetcode.hard.linkedlist;

import cn.xy.utils.ListNode;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author xiangyu
 * @date 2024-03-07 23:33
 * <p>
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * <p>
 * 示例 1：
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [1->4->5,1->3->4,2->6]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * <p>
 * 示例 2：
 * 输入：lists = []
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：lists = [[]]
 * 输出：[]
 * <p>
 * 提示：
 * <p>
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 */
public class A0023MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (null == lists || lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        ListNode temp = mergeList(lists[0], lists[1]);
        for (int i = 2; i < lists.length; i++) {
            temp = mergeList(temp, lists[i]);
        }
        return temp;
    }

    public ListNode mergeList(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        } else if (list1.val < list2.val) {
            list1.next = mergeList(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeList(list1, list2.next);
            return list2;
        }
    }


    /**
     * 优化排序
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        if (null == lists || lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        return mergeList2(0, lists.length - 1, lists);
    }

    public ListNode mergeList2(int start, int end, ListNode[] lists) {
        if (start == end) {
            return lists[start];
        }
        int mid = start + (end - start) / 2;
        ListNode listNode1 = mergeList2(start, mid, lists);
        ListNode listNode2 = mergeList2(mid + 1, end, lists);
        return mergeTwoLists(listNode1, listNode2);
    }

    /**
     * 改进mergeList
     */
    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a != null ? a : b;
        }
        ListNode head = new ListNode(0);
        ListNode tail = head, aPtr = a, bPtr = b;
        while (aPtr != null && bPtr != null) {
            if (aPtr.val < bPtr.val) {
                tail.next = aPtr;
                aPtr = aPtr.next;
            } else {
                tail.next = bPtr;
                bPtr = bPtr.next;
            }
            tail = tail.next;
        }
        tail.next = (aPtr != null ? aPtr : bPtr);
        return head.next;
    }


    /**
     * 使用小根堆
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists3(ListNode[] lists) {
        // 小根堆 比较器中小的在上面 负数说明 v1 < v2
        Queue<ListNode> pq = new PriorityQueue<>((v1, v2) -> v1.val - v2.val);
        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }

        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;
        while (!pq.isEmpty()) {
            ListNode minNode = pq.poll();
            tail.next = minNode;
            tail = minNode;
            if (minNode.next != null) {
                pq.offer(minNode.next);
            }
        }

        return dummyHead.next;
    }
}
