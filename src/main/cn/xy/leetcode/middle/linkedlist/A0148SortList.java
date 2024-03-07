package cn.xy.leetcode.middle.linkedlist;

import cn.xy.utils.ListNode;

/**
 * @author xiangyu
 * @date 2024-02-06 0:37
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * 提示：
 * <p>
 * 链表中节点的数目在范围 [0, 5 * 104] 内
 * -105 <= Node.val <= 105
 * <p>
 * <p>
 * 进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 * 归并排序
 */
public class A0148SortList {
    public ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode right = slow.next;
        slow.next = null;
        return mergeSort(head, right);
    }

    /**
     * 想到了归并排序，但是边界条件使用错了，很多边界条件没有判断出来
     *
     * @param left
     * @param right
     * @return
     */
    private ListNode mergeSort(ListNode left, ListNode right) {
        if (left.next == right) {
            if (left.val > right.val) {
                right.next = left;
                return right;
            } else {
                return left;
            }
        }
        ListNode slow = left;
        ListNode fast = left;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode curRight = slow.next;
        slow.next = null;
        ListNode mergeLeft = mergeSort(left, curRight);

        slow = right;
        fast = right;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mergeRight = mergeSort(right, slow);

        return merge(mergeLeft, mergeRight);
    }


    private ListNode merge(ListNode left, ListNode right) {
        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else if (left.val < right.val) {
            left.next = merge(left.next, right);
            return left;
        } else {
            right.next = merge(left, right.next);
            return right;
        }
    }


    public ListNode sortList2(ListNode head) {
        return sortList(head, null);
    }

    public ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) {
            return head;
        }
        // 只剩两个，取前一个，拆分取前一个， 后面那个会在第二sortList拿到
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);
        return merge2(list1, list2);
    }

    public ListNode merge2(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead;
        ListNode temp1 = head1;
        ListNode temp2 = head2;

        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return dummyHead.next;
    }
}
