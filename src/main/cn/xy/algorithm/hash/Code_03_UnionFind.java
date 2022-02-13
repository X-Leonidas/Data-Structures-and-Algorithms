package xy.algorithm.hash;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;


/**
 * 并查集结构
 */
public class Code_03_UnionFind {

    public static class Node {
        // whatever you like
    }

    public static class UnionFindSet {


        //第一个为当前节点，第二个为父节点
        public HashMap<Node, Node> fatherMap;

        //第一个为当前节点，第二个为当前node所在集合的元素个数
        public HashMap<Node, Integer> sizeMap;

        public UnionFindSet() {
            fatherMap = new HashMap<Node, Node>();
            sizeMap = new HashMap<Node, Integer>();
        }

        public void makeSets(List<Node> nodes) {
            fatherMap.clear();
            sizeMap.clear();
            for (Node node : nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        private Node findHead(Node node) {
            Node father = fatherMap.get(node);
            if (father != node) {
                father = findHead(father);
            }
            fatherMap.put(node, father);
            return father;
        }


        //非递归版本
        private Node findHead2(Node node) {
            Stack<Node> stack = new Stack<>();
            Node cur = node;
            Node parent = fatherMap.get(cur);

            while (cur != parent) {
                stack.push(cur);
                cur = parent;
                parent = fatherMap.get("cur");
            }

            while (!stack.isEmpty()) {
                fatherMap.put(stack.pop(), parent);
            }
            return parent;
        }

        public boolean isSameSet(Node a, Node b) {
            return findHead(a) == findHead(b);
        }

        public void union(Node a, Node b) {
            if (a == null || b == null) {
                return;
            }
            Node aHead = findHead(a);
            Node bHead = findHead(b);
            if (aHead != bHead) {
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                if (aSetSize <= bSetSize) {
                    fatherMap.put(aHead, bHead);
                    sizeMap.put(bHead, aSetSize + bSetSize);
                } else {
                    fatherMap.put(bHead, aHead);
                    sizeMap.put(aHead, aSetSize + bSetSize);
                }
            }
        }

    }

    public static void main(String[] args) {

    }

}
