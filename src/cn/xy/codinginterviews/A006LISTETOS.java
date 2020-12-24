package cn.xy.codinginterviews;

import javax.print.attribute.HashPrintRequestAttributeSet;
import java.util.ArrayList;
import java.util.Stack;

/**
 * @author XiangYu
 * @create2020-12-24-15:08 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 *  
 * <p>
 * 限制：
 * <p>
 * 0 <= 链表长度 <= 10000
 */
public class A006LISTETOS {
    public int[] reversePrint(ListNode head) {

        if (head == null) {
            return new int[0];
        }

        ArrayList<Integer> list = new ArrayList<>();
        ListNode temp = head;
        while (temp.next != null) {
            list.add(temp.val);
            temp = temp.next;
        }
        list.add(temp.val);

        int length = list.size();
        int[] ints = new int[length];


        for (int i = 0; i < length; i++) {
            ints[length - 1 - i] = list.get(i);
        }


        return ints;

    }


    public int[] reversePrint2(ListNode head) {

        if (head == null) {
            return new int[0];
        }

        Stack<Integer> stack = new Stack<>();
        ListNode temp = head;
        while (temp.next != null) {
            stack.push(temp.val);
            temp = temp.next;
        }
        stack.push(temp.val);

        int length = stack.size();
        int[] ints = new int[length];

        for (int i = 0; i < length; i++) {
            ints[i] = stack.pop();
        }

        return ints;
    }
}