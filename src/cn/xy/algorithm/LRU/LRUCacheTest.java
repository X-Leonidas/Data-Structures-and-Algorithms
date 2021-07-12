package cn.xy.algorithm.LRU;

import cn.xy.utils.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author XiangYu
 * @create2021-05-03-12:17
 */
public class LRUCacheTest {

   class DlinkedNode{
       int key;
       int value;

       DlinkedNode prev;
       DlinkedNode next;

       public DlinkedNode() {
       }

       public DlinkedNode(int key, int value) {
           this.key = key;
           this.value = value;
       }
   }



   private  Map<Integer,DlinkedNode> cache = new HashMap<Integer,DlinkedNode>();
   private int capacity;
   private  int size;
   private  DlinkedNode head;
   private DlinkedNode tail;


   public LRUCacheTest(int capacity){
       this.capacity = capacity;
       size  = 0;
       head = new DlinkedNode();
       tail = new DlinkedNode();

       head.next = tail;
       tail.prev = head;
   }





   public int get(int key){
       DlinkedNode node = cache.get(key);

       if(node != null){
           moveToHead(node);
           return cache.get(key).value;
       }else {
           return -1;
       }
   }



   public void put(int key, int value){
       DlinkedNode node = cache.get(key);

        if(node == null){
            DlinkedNode nNode = new DlinkedNode(key, value);
            addHead(nNode);
            cache.put(key,nNode);

            size++;
            if(capacity < size){
                DlinkedNode res = removeTail();
                cache.remove(res.key);
                size--;
            }
        }else {
            node.value  = value;
            moveToHead(node);
        }

   }


   private  void moveToHead(DlinkedNode node){
        removeHead(node);
        addHead(node);
   }

   private  void removeHead(DlinkedNode node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
   }


   private void addHead(DlinkedNode node){
       node.next = head.next;
       node.prev = head;
       head.next = node;
       head.next.prev = node;
   }

   private DlinkedNode removeTail(){
       DlinkedNode res = tail.prev;
       removeHead(res);
       return  res;
   }

}

