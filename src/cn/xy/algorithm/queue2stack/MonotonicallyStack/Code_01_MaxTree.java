package cn.xy.algorithm.queue2stack.MonotonicallyStack;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author XiangYu
 * @create2021-03-23-0:36 * 定义数组的MaxTree:
 * * 1）数组没有重复元素
 * * 2）MaxTree是一棵二叉树，数组的每个值对应一个节点
 * * 3）MaxTree及每一棵子树，满足值最大的值都是根节点
 * *
 * * 给定一个没有重复元素的数组，生成MaxTree，要求时间复杂度O(n), 空间复杂度O(n)。
 * <p>
 * <p>
 * <p>
 * <p>
 * 大根堆 建立过程是O(N)的时间复杂度
 */
public class Code_01_MaxTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }


    public static Node  buildMaxTree(int[] arr) {
        Node[] nArr = new Node[arr.length];

        for (int i = 0; i != arr.length; i++) {
            nArr[i] = new Node(arr[i]);
        }


        Stack<Node> stack = new Stack<Node>();
        HashMap<Node, Node> lBigMap = new HashMap<>();
        HashMap<Node, Node> rBigMap = new HashMap<>();


        for (int i = 0; i != nArr.length; i++) {
            Node curNode = nArr[i];
            while ((!stack.isEmpty()) && stack.peek().value < curNode.value) {
                popStackSetMap(stack, lBigMap);
            }
            stack.push(curNode);
        }


        while (!stack.isEmpty()) {
            popStackSetMap(stack, lBigMap);
        }


        for (int i = nArr.length - 1; i != -1; i--) {
            Node curNode = nArr[i];
            while ((!stack.isEmpty()) && stack.peek().value < curNode.value) {
                popStackSetMap(stack, rBigMap);
            }
            stack.push(curNode);
        }
        while (!stack.isEmpty()) {
            popStackSetMap(stack, rBigMap);
        }


        Node head = null;
        for (int i = 0; i != nArr.length; i++) {
            Node curNode = nArr[i];


            Node left = lBigMap.get(curNode);
            Node right = rBigMap.get(curNode);

            if (left == null && left == null) {
                head = curNode;
            }else  if ( left == null ){
                if(right.left == null){
                    right.left = curNode;
                }else {
                    right.right = curNode;
                }
            }else if ( right == null){
                if(left.left == null){
                    left.left = curNode;
                }else {
                    left.right = curNode;
                }

            }

        }


        return  head;
    }


    private static void popStackSetMap (Stack < Node > stack, HashMap < Node, Node > map){
        Node popNode = stack.pop();

        if(stack.isEmpty()){
            map.put(popNode,null);
        }else {
            map.put(popNode,stack.peek());
        }

    }








    public static void main(String[] args) {
        int[] uniqueArr = {3, 4, 5, 1, 2};

        Node node = buildMaxTree(uniqueArr);

    }
}
