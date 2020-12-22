package cn.xy.codinginterviews;

/**
 * @author XiangYu
 * @create2020-12-22-14:49
 *
 *  输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，
 *  本题从1开始计数，即链表的尾节点是倒数第1个节点。
 *  例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。
 *
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 k = 2.
 *
 * 返回链表 4->5.
 */
public class A022ListNodeK {

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.3 MB, 在所有 Java 提交中击败了71.62%的用户
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd(ListNode head, int k) {


        ListNode temp = head;
        int length = 1;
        while(true){
            temp = temp.next;
            if(temp == null){
                break;
            }else {
                length++;
            }
        }

        temp = head;
        for (int i = 0; i < length - k; i++) {
                temp = temp.next;
        }

        return  temp;
    }


    /**
     * 双指针
     * @param head
     * @param k
     * @return
     */

    public ListNode getKthFromEnd2(ListNode head, int k) {
        ListNode former = head;
        ListNode latter = head;
        for(int i = 0; i < k; i++){
            former = former.next;
        }
        while(former != null) {
            former = former.next;
            latter = latter.next;
        }
        return latter;
    }
}

class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
}
