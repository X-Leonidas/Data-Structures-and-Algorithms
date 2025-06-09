package cn.xy.algorithm.lru;

import java.util.HashMap;

/**
 * @author XiangYu
 * @create2021-04-01-19:35 最不经常使用，如果一个数据在最近一段时间内使用次数很少，那么在将来一段时间内被使用的可能性也很小。
 * <p>
 * <p>
 * <p>
 * 请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。
 * <p>
 * 实现 LFUCache 类：
 * <p>
 * LFUCache(int capacity) - 用数据结构的容量 capacity 初始化对象
 * int get(int key) - 如果键存在于缓存中，则获取键的值，否则返回 -1。
 * void put(int key, int value) - 如果键已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量时，则应该在插入新项之前，使最不经常使用的项无效。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最久未使用 的键。
 * 注意「项的使用次数」就是自插入该项以来对其调用 get 和 put 函数的次数之和。使用次数会在对应项被移除后置为 0 。
 * <p>
 * 为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。使用计数最小的键是最久未使用的键。
 * <p>
 * 当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。对缓存中的键执行 get 或 put 操作，使用计数器的值将会递增。
 * <p>
 * 注意：  当次数相同但又要扔掉一个时，需要丢弃最不经常访问的。
 * <p>
 * <p>
 * 思路 ：  一个次数的双向链表，  每个次数下面挂着key  挂着的key也是双向链表
 */



public  class LFUCache {
    /**
     * 每个次数下挂着的双向链表
     */
    public static class Node {
        public Integer key;
        public Integer value;
        public Integer times;
        public Node up;
        public Node down;

        public Node(int key, int value, int times) {
            this.key = key;
            this.value = value;
            this.times = times;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }
    }

    /**
     * 次数的双向链表
     */
    public static class NodeList {
        public Node head;
        public Node tail;
        public NodeList last;
        public NodeList next;

        public NodeList(Node node) {
            head = node;
            tail = node;
        }

        //把一个节点放到该节点下的链表头部
        public void addNodeFromHead(Node newHead) {
            newHead.down = head;
            head.up = newHead;
            head = newHead;
        }

        public boolean isEmpty() {
            return head == null;
        }
        //删除任一个节点
        public void deleteNode(Node node) {
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                if (node == head) {
                    head = node.down;
                    head.up = null;
                } else if (node == tail) {
                    tail = node.up;
                    tail.down = null;
                } else {
                    node.up.down = node.down;
                    node.down.up = node.up;
                }
            }
            node.up = null;
            node.down = null;
        }
    }

    /**
     * 最大容量
     */
    private int capacity;
    /**
     * 实际元素个数
     */
    private int size;
    /**
     *  key和Node的表
     */
    private HashMap<Integer, Node> records;

    /**
     * 节点位于那个次数节点下
     */
    private HashMap<Node, NodeList> heads;
    /**
     * 整个Noodelist的头
     */
    private NodeList headList;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.records = new HashMap<>();
        this.heads = new HashMap<>();
        headList = null;
    }

    public void set(int key, int value) {
        //是否是首次进入
        if (records.containsKey(key)) {
            Node node = records.get(key);
            node.value = value;
            node.times++;
            NodeList curNodeList = heads.get(node);

            move(node, curNodeList);
        } else {
            //没包含并且已经到了阈值
            if (size == capacity) {
                Node node = headList.tail;
                headList.deleteNode(node);
                modifyHeadList(headList);
                records.remove(node.key);
                heads.remove(node);
                size--;
            }

            //没有超出阈值
            Node node = new Node(key, value, 1);
            if (headList == null) {
                headList = new NodeList(node);
            } else {
                //当前头部的词频是否是1，如果不是则新建一个词频为1的词频链表
                if (headList.head.times.equals(node.times)) {
                    headList.addNodeFromHead(node);
                } else {
                    NodeList newList = new NodeList(node);
                    newList.next = headList;
                    headList.last = newList;
                    headList = newList;
                }
            }
            records.put(key, node);
            heads.put(node, headList);
            size++;
        }
    }

    /**
     * 在次数链表中移除{@param oldNodeList}，并放到词频+1的链表中
     * @param node
     * @param oldNodeList
     */
    private void move(Node node, NodeList oldNodeList) {
        oldNodeList.deleteNode(node);
        //次数链表的前一个
        NodeList preList = modifyHeadList(oldNodeList) ? oldNodeList.last : oldNodeList;
        //次数链表的后一个
        NodeList nextList = oldNodeList.next;
        //是否为词频链表的尾
        if (nextList == null) {
            NodeList newList = new NodeList(node);
            if (preList != null) {
                preList.next = newList;
            }
            newList.last = preList;

            if (headList == null) {
                headList = newList;
            }
            heads.put(node, newList);
        } else {
            //词频加1后的次数链表已经存在
            if (nextList.head.times.equals(node.times)) {
                nextList.addNodeFromHead(node);
                heads.put(node, nextList);
            } else {
                NodeList newList = new NodeList(node);
                if (preList != null) {
                    preList.next = newList;
                }
                newList.last = preList;
                newList.next = nextList;
                nextList.last = newList;
                if (headList == nextList) {
                    headList = newList;
                }
                heads.put(node, newList);
            }
        }
    }

    /**
     * 如果次数链表中没有node，就删除该次数链表
     * @param nodeList
     * @return  是否删除
     */
    private boolean modifyHeadList(NodeList nodeList) {
        if (nodeList.isEmpty()) {
            if (headList == nodeList) {
                headList = nodeList.next;

                if (headList != null) {
                    headList.last = null;
                }
            } else {
                nodeList.last.next = nodeList.next;
                if (nodeList.next != null) {
                    nodeList.next.last = nodeList.last;
                }
            }
            return true;
        }
        return false;
    }

    public int get(int key) {
        if (!records.containsKey(key)) {
            return -1;
        }
        Node node = records.get(key);
        node.times++;
        NodeList curNodeList = heads.get(node);
        move(node, curNodeList);
        return node.value;
    }

}



