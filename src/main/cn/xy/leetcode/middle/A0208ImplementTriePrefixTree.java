package cn.xy.leetcode.middle;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author xiangyu
 * @date 2024-02-26 21:19
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。
 * 这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 * <p>
 * 请你实现 Trie 类：
 * <p>
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 * <p>
 * 1 <= word.length, prefix.length <= 2000
 * word 和 prefix 仅由小写英文字母组成
 * insert、search 和 startsWith 调用次数 总计 不超过 3 * 104 次
 */
public class A0208ImplementTriePrefixTree {
    public static void main(String[] args) {
        A0208ImplementTriePrefixTree a0208ImplementTriePrefixTree = new A0208ImplementTriePrefixTree();
        a0208ImplementTriePrefixTree.test();
    }

    public void test() {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));  // 返回 True
        System.out.println(trie.search("app"));     // 返回 False
        System.out.println(trie.startsWith("app")); // 返回 True
        trie.insert("app");
        System.out.println(trie.search("app"));    // 返回 True
    }


    class Trie {
        private Trie head;

        private char value;

        private HashMap<Character, Trie> next;

        public Trie() {
            head = this;
            head.next = new HashMap<>();
        }

        private Trie(char value) {
            this.value = value;
            this.next = new HashMap<>();
        }

        public void insert(String word) {
            if (Objects.isNull(word) || word.isEmpty()) {
                return;
            }
            Trie temp = head;
            for (char aChar : word.toCharArray()) {
                if (!temp.next.containsKey(aChar)) {
                    temp.next.put(aChar, new Trie(aChar));
                }
                temp = temp.next.get(aChar);
            }
            // 添加结束标志
            temp.next.put('/', null);
        }

        public boolean search(String word) {
            if (Objects.isNull(word) || word.isEmpty()) {
                return true;
            }
            Trie temp = head;
            for (char aChar : word.toCharArray()) {
                if (!temp.next.containsKey(aChar)) {
                    return false;
                } else {
                    temp = temp.next.get(aChar);
                }
            }
            return temp.next.containsKey('/');
        }

        public boolean startsWith(String prefix) {
            if (Objects.isNull(prefix) || prefix.isEmpty()) {
                return true;
            }
            Trie temp = head;
            for (char aChar : prefix.toCharArray()) {
                if (!temp.next.containsKey(aChar)) {
                    return false;
                } else {
                    temp = temp.next.get(aChar);
                }
            }
            return true;
        }
    }
}
