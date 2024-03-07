package cn.xy.leetcode.easy.linkedList;

import cn.xy.utils.ListNode;

/**
 * @author xiangyu
 * @date 2024-03-07 20:36
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 * <p>
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）
 * 。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
 * <p>
 * 果链表中存在环 ，则返回 true 。 否则，返回 false 。
 */
public class A0141LinkedListCycle {
    //hash 解法略过

    /**
     * 快慢指针
     *
     * @param head
     * @return
     */

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

}
