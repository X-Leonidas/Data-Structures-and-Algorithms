package cn.xy.leetcode.middle.linkedlist;

import cn.xy.utils.ListNode;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author XiangYu
 * @create2021-05-01-11:10
 *
 *
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * <p>
 * 进阶：你能尝试使用一趟扫描实现吗？
 * <p>
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
        ListNode hair = new ListNode(0);
        hair.next = head;
        ListNode first = head;
        ListNode slow =  hair;

        for(int i=0; i < n; i++){
            first = first.next;
        }

        while(first != null){
            slow = slow.next;
            first = first.next;
        }

        slow.next = slow.next.next;


        return hair.next;
    }

    /**
     * 输出倒数第K个节点
     * @param head
     * @param n
     * @return
     */
    public ListNode FindKthToTail (ListNode head, int n) {
        if(head == null){
            return null;
        }
        ListNode hair = new ListNode(0);
        hair.next = head;
        ListNode first = head;
        ListNode slow =  hair;
        for(int i=0; i < n; i++){
            if(first == null){
                return  null;
            }
            first = first.next;
        }

        while(first != null){
            slow = slow.next;
            first = first.next;
        }

        return slow.next;
    }



}
