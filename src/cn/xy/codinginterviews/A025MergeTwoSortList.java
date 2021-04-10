package cn.xy.codinginterviews;

import cn.xy.utils.ListNode;

import java.util.ArrayList;

/**
 * @author XiangYu
 * @create2020-12-25-15:15
 * 剑指 Offer 25. 合并两个排序的链表
 * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
 *
 * 示例1：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 * 限制：
 *
 * 0 <= 链表长度 <= 1000
 *
 * 注意：本题与主站 21 题相同：https://leetcode-cn.com/problems/merge-two-sorted-lists/
 */
public class A025MergeTwoSortList {


    public static void main(String[] args) {
        int[] ints1 ={1,2,4};
        int[] ints2 ={1,3,4};
        ListNode l1 = ListNode.getDemo(ints1);
        ListNode l2 = ListNode.getDemo(ints2);

        mergeTwoLists2(l1,l2);

    }

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了98.61%的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了73.45%的用户
     * @param l1
     * @param l2
     * @return
     */
    public static  ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if(l1 == null){
            return  l2;
        }else if(l2 == null){
            return  l1;
        }

        //开头可以使用伪指针优化
        ListNode l1Temp = l1;
        ListNode l2Temp = l2;
        ListNode target;
        if(l1.val >= l2.val){
            target = l2;
            l2Temp = l2Temp.next;
        }else {
            target = l1;
            l1Temp = l1Temp.next;
        }
        ListNode t1 = target;
        while (l1Temp !=null && l2Temp != null){
            if(l1Temp.val >= l2Temp.val ){
                target.next = l2Temp;
                l2Temp  = l2Temp.next;
            }else {
                target.next = l1Temp;
                l1Temp  = l1Temp.next;
            }

            target = target.next;
        }

//        if(l1Temp == null){
//            while (l2Temp !=null){
//                target.next = l2Temp;
//                l2Temp  = l2Temp.next;
//                target = target.next;
//            }
//        }
//
//        if(l2Temp == null){
//            while (l1Temp !=null){
//                target.next = l1Temp;
//                l1Temp  = l1Temp.next;
//                target = target.next;
//            }
//        }
        //这思路错了，当一个链表为空时，另一个链表不需要全部遍历，指要指向一次就ok
        //优化后的写法
        target.next = l1Temp == null ? l2Temp:l1Temp;


        return t1;
    }


    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了98.61%的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了65.14%的用户
     * @param l1
     * @param l2
     * @return
     */
    public static  ListNode mergeTwoLists2(ListNode l1, ListNode l2) {

        if(l1 == null){
            return  l2;
        }else if(l2 == null){
            return  l1;
        }


        ArrayList<ListNode> listNodes = new ArrayList<>();
        ListNode l1Temp = l1;
        ListNode l2Temp = l2;
        while (l1Temp !=null && l2Temp != null){
            if(l1Temp.val >= l2Temp.val ){
                listNodes.add(l2Temp);
                l2Temp = l2Temp.next;
            }else {
                listNodes.add(l1Temp);
                l1Temp = l1Temp.next;
            }
        }

        if(l1Temp == null){
            while (l2Temp !=null){
                listNodes.add(l2Temp);
                l2Temp  = l2Temp.next;
            }
        }

        if(l2Temp == null){
            while (l1Temp !=null){
                listNodes.add(l1Temp);
                l1Temp  = l1Temp.next;
            }
        }

        for (int i = 0; i < listNodes.size() -1; i++) {
            listNodes.get(i).next = listNodes.get(i+1);
            listNodes.get(i+1).next = null;
        }

        return listNodes.get(0);
    }

    /**
     * 官方优雅写法，使用伪节点
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists3(ListNode l1, ListNode l2) {
        //使用伪节点
        ListNode dum = new ListNode(0);
        ListNode cur = dum;
        while(l1 != null && l2 != null) {
            if(l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 != null ? l1 : l2;
        //伪节点优化操作后续
        return dum.next;
    }


}
