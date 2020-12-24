package cn.xy.codinginterviews;

import java.util.Stack;

/**
 * @author XiangYu
 * @create2020-12-24-16:00
 * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 *
 *  
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 */
public class A024fanzhuanlianbiao {

    public static  ListNode reverseList(ListNode head) {

        if(head == null){
            return  null;
        }


        Stack<ListNode> stack = new Stack<>();

        ListNode temp =head;

        while(temp != null){
            stack.push(temp);
            temp = temp.next;
        }

        int size = stack.size();
        head = stack.pop();
        temp = head;
        for (int i = 1; i < size; i++) {
            temp.next = stack.pop();
            temp = temp.next;
            temp.next = null;
        }
        temp.next = null;
        return  head;
    }


    /**
     * 双指针
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了67.49%的用户
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        ListNode pre  = null;
        ListNode now  = head;


        while (now != null){
            ListNode temp = now.next;
            now.next = pre;
            pre = now;
            now = temp;
        }


        return pre;
    }


    public static void main(String[] args) {
        ListNode demo = ListNode.getDemo();
        reverseList3(demo);
    }

    /**
     * 递归
     * @param head
     * @return
     */
    public static ListNode reverseList3(ListNode head) {
        ListNode temp = null;
        ListNode reverse = reverse(temp,head);
        return reverse;

    }


    public static  ListNode reverse(ListNode temp ,ListNode node){

        if(node.next == null){
            return node;
        }else {
            temp = reverse(temp,node.next);
            node.next = null;
            temp.next = node;
        }

        return  temp;
    }

}
