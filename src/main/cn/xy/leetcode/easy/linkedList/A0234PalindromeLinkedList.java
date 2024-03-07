package cn.xy.leetcode.easy.linkedList;

import cn.xy.utils.ListNode;

/**
 * @author XiangYu
 * @create2021-04-06-21:33 请判断一个链表是否为回文链表。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 * <p>
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */
public class A0234PalindromeLinkedList {

    public boolean isPalindrome(ListNode head) {
        //排除只有一个元素的情况
        if (head == null || head.next == null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = null;
        // 快慢指针找中点，并且反转前面的链表
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            ListNode temp = slow.next;
            slow.next = pre;
            pre = slow;
            slow = temp;
        }
        //奇数情况下,
        if (fast != null) {
            slow = slow.next;
        }
        while (pre != null && slow != null) {
            if (pre.val != slow.val) {
                return false;
            }
            pre = pre.next;
            slow = slow.next;
        }
        return true;
    }
}
