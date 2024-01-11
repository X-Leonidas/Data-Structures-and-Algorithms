package cn.xy.utils;

import java.util.ArrayList;

public  class ListNode {
      public int val;
      public ListNode next;

      public ListNode(int x) {
            val = x;
      }

      public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
      }


      public static ListNode getDemo(int[] array) {
            ArrayList<ListNode> listNodes = new ArrayList<>();
          for (int j : array) {
              listNodes.add(new ListNode(j));
          }


            for (int i = 0; i < listNodes.size() - 1; i++) {
                  listNodes.get(i).next = listNodes.get(i + 1);
            }

            return listNodes.get(0);
      }
}

