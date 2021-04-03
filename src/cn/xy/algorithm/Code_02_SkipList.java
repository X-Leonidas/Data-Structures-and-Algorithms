package cn.xy.algorithm;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author XiangYu
 * @create2021-04-02-21:37
 *
 * 跳表： O(LogN)
 * 跳表实际上是一种增加了前向指针的链表，是一种随机化的数据结构，实质上是可以进行二分查找的有序链表；
 * 跳表在原来的有序链表上加上了多级索引，通过索引来快速查找；可以支持快速的删除、插入和查找操作。
 */
public class Code_02_SkipList {
    public static class SkipListNode {
        public Integer value;
        /**
         * 每一层下的当前节点的下一个节点
         */
        public ArrayList<SkipListNode> nextNodes;

        public SkipListNode(Integer value) {
            this.value = value;
            nextNodes = new ArrayList<SkipListNode>();
        }
    }

    public static class SkipListIterator implements Iterator<Integer> {
        SkipList list;
        SkipListNode current;

        public SkipListIterator(SkipList list) {
            this.list = list;
            this.current = list.getHead();
        }

        @Override
        public boolean hasNext() {
            return current.nextNodes.get(0) != null;
        }

        @Override
        public Integer next() {
            current = current.nextNodes.get(0);
            return current.value;
        }
    }

    public static class SkipList {
        /**
         * 最小值，链表的起点
         */
        private SkipListNode head;

        /**
         * 最大层
         */
        private int maxLevel;
        /**
         * 数据量
         */
        private int size;
        /**
         * 分层概率
         */
        private static final double PROBABILITY = 0.5;

        public SkipList() {
            size = 0;
            maxLevel = 0;
            head = new SkipListNode(null);
            head.nextNodes.add(null);
        }

        public SkipListNode getHead() {
            return head;
        }

        public void add(Integer newValue) {
            if (!contains(newValue)) {
                size++;
                int level = 0;
                while (Math.random() < PROBABILITY) {
                    level++;
                }
                while (level > maxLevel) {
                    head.nextNodes.add(null);
                    maxLevel++;
                }
                SkipListNode newNode = new SkipListNode(newValue);
                SkipListNode current = head;
                do {
                    current = findNext(newValue, current, level);
                    newNode.nextNodes.add(0, current.nextNodes.get(level));
                    current.nextNodes.set(level, newNode);
                } while (level-- > 0);
            }
        }

        public void delete(Integer deleteValue) {
            if (contains(deleteValue)) {
                SkipListNode deleteNode = find(deleteValue);
                size--;
                int level = maxLevel;
                SkipListNode current = head;
                do {
                    current = findNext(deleteNode.value, current, level);
                    if (deleteNode.nextNodes.size() > level) {
                        current.nextNodes.set(level, deleteNode.nextNodes.get(level));
                    }
                } while (level-- > 0);
            }
        }

        // Returns the skiplist node with greatest value <= e
        private SkipListNode find(Integer e) {
            return find(e, head, maxLevel);
        }

        // Returns the skiplist node with greatest value <= e
        // Starts at node start and level
        private SkipListNode find(Integer e, SkipListNode current, int level) {
            do {
                current = findNext(e, current, level);
            } while (level-- > 0);
            return current;
        }

        // Returns the node at a given level with highest value less than e
        private SkipListNode findNext(Integer e, SkipListNode current, int level) {
            SkipListNode next = current.nextNodes.get(level);
            while (next != null) {
                Integer value = next.value;
                if (lessThan(e, value)) { // e < value
                    break;
                }
                current = next;
                next = current.nextNodes.get(level);
            }
            return current;
        }

        public int size() {
            return size;
        }

        public boolean contains(Integer value) {
            SkipListNode node = find(value);
            return node != null && node.value != null && equalTo(node.value, value);
        }

        public Iterator<Integer> iterator() {
            return new SkipListIterator(this);
        }

        /******************************************************************************
         * Utility Functions *
         ******************************************************************************/

        private boolean lessThan(Integer a, Integer b) {
            return a.compareTo(b) < 0;
        }

        private boolean equalTo(Integer a, Integer b) {
            return a.compareTo(b) == 0;
        }

    }

    public static void main(String[] args) {

    }
}
