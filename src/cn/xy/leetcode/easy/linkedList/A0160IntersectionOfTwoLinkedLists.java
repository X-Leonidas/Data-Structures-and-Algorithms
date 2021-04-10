package cn.xy.leetcode.easy.linkedList;

import cn.xy.utils.ListNode;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author XiangYu
 * @create2021-04-10-20:33 编写一个程序，找到两个单链表相交的起始节点。
 * <p>
 * 如下面的两个链表：
 * <p>
 * <p>
 * <p>
 * 在节点 c1 开始相交。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Reference of the node with value = 8
 * 输入解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 *  
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * 输出：Reference of the node with value = 2
 * 输入解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
 *  
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * <p>
 * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * 输出：null
 * 输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
 * 解释：这两个链表不相交，因此返回 null。
 *  
 * <p>
 * 注意：
 * <p>
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 */
public class A0160IntersectionOfTwoLinkedLists {


    /**
     * 执行用时：4 ms, 在所有 Java 提交中击败了16.92%的用户
     * 内存消耗：41.7 MB, 在所有 Java 提交中击败了10.84%的用户
     *
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        //第一次错误，没有考虑第一个点就相交的情况
        if (headA == headB) {
            return headA;
        }

        ArrayDeque<ListNode> stackA = new ArrayDeque<>();
        ArrayDeque<ListNode> stackB = new ArrayDeque<>();

        ListNode tempA = headA;
        ListNode tempB = headB;
        while (tempA != null) {
            stackA.offer(tempA);
            tempA = tempA.next;
        }

        while (tempB != null) {
            stackB.offer(tempB);
            tempB = tempB.next;
        }

        ListNode res = null;
        while (stackA.peekLast() == stackB.peekLast()) {
            res = stackA.pollLast();
            stackB.pollLast();
        }
        return res;
    }


    /**
     * 执行用时：772 ms, 在所有 Java 提交中击败了5.00%的用户
     * 内存消耗：41 MB, 在所有 Java 提交中击败了89.25%的用户
     *
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        while (headA != null) {
            ListNode temp = headB;
            while (temp != null) {
                if (temp == headA) {
                    return headA;
                }
                temp = temp.next;
            }
            headA = headA.next;
        }


        return null;
    }

    /**
     * 哈希
     *
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        Set<ListNode> hashSet = new HashSet<>();
        ListNode pA = headA;
        ListNode pB = headB;

        while (pA != null) {
            hashSet.add(pA);
            pA = pA.next;
        }

        while (pB != null) {
            if (hashSet.contains(pB)) {
                return pB;
            } else {
                pB = pB.next;
            }
        }
        return null;
    }


    /**
     * 双指针
     *
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode4(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode p1 = headA;
        ListNode p2 = headB;

        while (p1 != p2) {
            p1 = p1 == null ? headB : p1.next;
            p2 = p2 == null ? headA : p2.next;
        }

        return p1;
    }


    public static void main(String[] args) {
        ListNode headA = new ListNode(3);
        ListNode headB = new ListNode(2);
        headB.next = headA;

        System.out.println(getIntersectionNode2(headA, headB).val);

    }
}
