package cn.xy.leetcode;

import java.util.HashMap;

/**
 * 代码练习
 */
public class TestBase {


    static class LRUCache {
        private int capacity;
        private HashMap<Integer, Node> map;
        private NodeDoubleLinkedList queue;


        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<Integer, Node>();
            queue = new NodeDoubleLinkedList();

        }

        public int get(int key) {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                queue.moveNodeToTail(node);
                return node.v;
            }
            return -1;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                Node node = map.get(key);
                node.v = value;
                queue.moveNodeToTail(node);
            } else {
                Node node = new Node(key, value);
                queue.addNode(node);
                map.put(key, node);
                if (map.size() > capacity) {
                    Node heaNode = queue.removeHead();
                    map.remove(heaNode.k);
                }
            }
        }

        public static class Node {
            public Integer k;
            public Integer v;
            public Node last;
            public Node next;

            public Node(Integer k, Integer v) {
                this.k = k;
                this.v = v;
            }
        }

        //双端链表
        public static class NodeDoubleLinkedList {
            private Node head;
            private Node tail;

            public NodeDoubleLinkedList() {
                head = null;
                tail = null;
            }

            public void addNode(Node newNode) {
                if (newNode == null) {
                    return;
                }
                if (this.head == null) {
                    this.head = newNode;
                    this.tail = newNode;
                } else {
                    this.tail.next = newNode;
                    newNode.last = this.tail;
                    this.tail = newNode;
                }
            }

            //更新元素到链表尾部
            public void moveNodeToTail(Node node) {
                if (node == null || node == this.tail) {
                    return;
                }

                if (this.head == node) {
                    this.head = node.next;
                    this.head.last = null;
                } else {
                    node.last.next = node.next;
                    node.next.last = node.last;
                }
                //将node的下一个置为null
                node.next = null;
                node.last = this.tail;
                this.tail.next = node;
                this.tail = node;
            }

            //移除头部并把头部返回
            public Node removeHead() {
                if (this.head == null) {
                    return null;
                }
                Node oldHead = this.head;
                if (this.head == this.tail) {
                    this.head = null;
                    this.tail = null;
                } else {
                    this.head = oldHead.next;
                    oldHead.next = null;
                    this.head.last = null;
                }
                return oldHead;
            }
        }

        public static void main(String[] args) {
            //["LRUCache","put","put","put","put","get","get","get","get","put","get","get","get","get","get"]
            //[[3],[1,1],[2,2],[3,3],[4,4],[4],[3],[2],[1],[5,5],[1],[2],[3],[4],[5]]
            LRUCache lruCache = new LRUCache(3);
            lruCache.put(1, 1);
            lruCache.put(2, 2);
            lruCache.put(3, 3);
            lruCache.put(4, 4);
            lruCache.get(4);
            lruCache.get(3);
            lruCache.get(2);
            lruCache.get(1);
            lruCache.put(5, 5);
            lruCache.get(1);
            lruCache.get(2);
            lruCache.get(3);
            lruCache.get(4);
            lruCache.get(5);
        }
    }

}