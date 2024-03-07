package cn.xy.leetcode.hard.linkedlist;

import cn.xy.utils.ListNode;

import java.util.Stack;

/**
 * @author XiangYu
 * @create2021-04-25-17:37 <P>
 * 给你一个链表，每k个节点一组进行翻转，请你返回翻转后的链表。
 * k是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 进阶：
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * 示例 1：
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 * <p>
 * 示例 2：
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 * <p>
 * 示例 3：
 * 输入：head = [1,2,3,4,5], k = 1
 * 输出：[1,2,3,4,5]
 * <p>
 * 示例 4：
 * 输入：head = [1], k = 1
 * 输出：[1]
 * <p>
 * 提示：
 * 列表中节点的数量在范围 sz 内
 * 1 <= sz <= 5000
 * 0 <= Node.val <= 1000
 * 1 <= k <= sz
 */
public class A0025ReverseNodesIn_K_Group {


    /**
     * 自己的写法，使用K的额外空间
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        Stack<ListNode> stack = new Stack<>();
        ListNode res = new ListNode(-1);
        ListNode per = res;
        if (k == 1 || head == null || head.next == null) {
            return head;
        }
        //遍历整个数组
        while (head != null) {
            int i = 0;
            //用栈来翻转
            while (i < k && head != null) {
                stack.push(head);
                i++;
                head = head.next;
            }
            if (stack.size() == k) {
                //上一个指针 和 下一个指针
                per = reverse(stack, per, head);
            }
        }
        return res.next;
    }

    public static ListNode reverse(Stack<ListNode> stack, ListNode per, ListNode next) {
        //倒叙连接
        while (!stack.isEmpty()) {
            per.next = stack.pop();
            per = per.next;
        }
        //已经到达结尾
        per.next = next;
        return per;
    }

    /**
     * 常数空间
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup2(ListNode head, int k) {
        //头节点
        ListNode hair = new ListNode(0);
        hair.next = head;
        ListNode pre = hair;
        while (head != null) {
            //先让tail节点走k个
            ListNode tail = pre;
            // 查看剩余部分长度是否大于等于 k
            for (int i = 0; i < k; ++i) {
                tail = tail.next;
                if (tail == null) {
                    return hair.next;
                }
            }
            //下一个接头的点
            ListNode nex = tail.next;
            //将k个节点反转
            ListNode[] reverse = myReverse(head, tail);
            head = reverse[0];
            tail = reverse[1];
            // 把子链表重新接回原链表
            pre.next = head;
            tail.next = nex;
            pre = tail;
            head = tail.next;
        }
        return hair.next;
    }

    public ListNode[] myReverse(ListNode head, ListNode tail) {
        ListNode prev = null;
        ListNode p = head;
        // 反转列表知道最后一个
        while (prev != tail) {
            ListNode next = p.next;
            // 翻转
            p.next = prev;
            prev = p;
            p = next;
        }
        return new ListNode[]{tail, head};
    }
}
