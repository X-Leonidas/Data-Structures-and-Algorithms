package cn.xy.leetcode.middle.linkedlist;

import cn.xy.utils.ListNode;

/**
 * @author XiangYu
 * @create2021-04-24-12:05 给你两个非空 的链表，表示两个非负的整数。
 * 它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数都不会以0开头。
 * <p>
 * 示例 1：
 * <p>
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * 示例 2：
 * <p>
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * 示例 3：
 * <p>
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 * <p>
 * 提示：
 * 每个链表中的节点数在范围 [1, 100] 内
 * 0 <= Node.val <= 9
 * 题目数据保证列表表示的数字不含前导零
 */
public class A0002AddTwoNumbers {


    /**
     * 第一次没有考虑溢出的问题
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        boolean flag = false;
        ListNode head = new ListNode(0);
        ListNode temp = head;

        while (l1 != null || l2 != null || flag) {
            int res = 0;
            //优化代码
//            if(l1 != null && l2 != null){
//                res = l1.val + l2.val;
//                l1 = l1.next;
//                l2 = l2.next;
//            }else  if(l1 != null){
//                res = l1.val;
//                l1 = l1.next;
//            }else if(l2 != null) {
//                res = l2.val;
//                l2 = l2.next;
//            }
            //当前位的和
            res = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0);

            //判断上一位的进位
            if (flag) {
                res++;
                flag = false;
            }
            //判断当前进位
            if (res >= 10) {
                res = res % 10;
                flag = true;
            }
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;

            temp.next = new ListNode(res);
            temp = temp.next;
        }

        return head.next;
    }

    public static void main(String[] args) {

        int[] intsA = {9};
        int[] intsB = {1, 9, 9, 9, 9, 9, 9, 9, 9, 9};
        ListNode l1 = ListNode.getDemo(intsA);
        ListNode l2 = ListNode.getDemo(intsB);
        addTwoNumbers(l1, l2);

    }

    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        boolean carryFlag = false;
        ListNode head = new ListNode(-1);
        ListNode temp = head;
        while (l1 != null || l2 != null || carryFlag) {
            int res = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val);
            if (carryFlag) {
                res++;
                carryFlag = false;
            }

            if (res >= 10) {
                res = res % 10;
                carryFlag = true;
            }
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
            temp.next = new ListNode(res);
            temp = temp.next;
        }

        return head.next;
    }

}
