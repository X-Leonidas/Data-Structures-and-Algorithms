package cn.xy.codinginterviews;

import java.util.ArrayList;

public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }



      public static ListNode getDemo(int[] array){
            ArrayList<ListNode> listNodes = new ArrayList<>();
            for (int i = 0; i < array.length; i++) {
                  listNodes.add(new ListNode(array[i]));
            }


            for (int i = 0; i < listNodes.size() -1; i++) {
                  listNodes.get(i).next = listNodes.get(i+1);
            }

            return  listNodes.get(0);
      }
}
