package cn.xy.leetcode.middle.linkedlist;

import cn.xy.utils.ListNode;
import cn.xy.utils.Utils;

import java.util.List;

/**
 * @author xiangyu
 * @date 2025-03-01 0:48
 * <p>
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 * <p>
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 * <p>
 * 示例 2：
 * 输入：head = [5], left = 1, right = 1
 * 输出：[5]
 * <p>
 * 提示：
 * 链表中节点数目为 n
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 * <p>
 * 进阶： 你可以使用一趟扫描完成反转吗？
 */
public class A0092ReverseLinkedListII {
        public static void main(String[] args) {
            ListNode head = Utils.convertNode(List.of(1,2,3,4,5));
            ListNode listNode = new A0092ReverseLinkedListII().reverseBetween2(head, 2, 4);

            Utils.printNode(listNode);
        }

        public ListNode reverseBetween(ListNode head, int left, int right) {
            if(head.next  == null || left == right){
                return head;
            }

            ListNode preStart = null;
            ListNode start  = null;
            ListNode preNode = null;
            ListNode curNode  = head;

            int index  = 1;
            //现在问题是谁如果是1 开始，会出现 head的头被替换掉的问题，解决方法可以加上一个多余的头
            while(curNode != null && index != left){
                preStart = curNode;
                curNode = curNode.next;
                index++;
            }

            start = curNode;

            while(curNode != null && index != right){
                ListNode next = curNode.next;
                curNode.next = preNode;
                preNode = curNode;
                curNode = next;
                index++;
            }

            curNode.next = preNode;

            if(curNode.next != null){
                start.next = curNode.next;
            }

            if(preStart != null){
                preStart.next = curNode;
            }


            return head;
        }

    public ListNode reverseBetween2(ListNode head, int left, int right) {
        if(head.next  == null || left == right){
            return head;
        }

        ListNode result = new ListNode(-1);
        result.next = head;
        ListNode preStart = null;
        ListNode preNode = null;
        ListNode curNode  = result;

        int index  = 0;
        while(curNode != null && index < left){
            preStart = curNode;
            curNode = curNode.next;
            index++;
        }

        while(curNode != null && index <= right){
            ListNode next = curNode.next;
            curNode.next = preNode;
            preNode = curNode;
            curNode = next;
            index++;
        }

        // 调转翻转链表链表头
        if(curNode != null){
            preStart.next.next = curNode;
        }
        // 调转翻转链表尾部
        if(preStart != null){
            preStart.next = preNode;
        }


        return result.next;
    }


}
