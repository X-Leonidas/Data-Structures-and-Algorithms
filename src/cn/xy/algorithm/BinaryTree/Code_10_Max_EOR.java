package cn.xy.algorithm.BinaryTree;

import java.nio.channels.FileChannel;

/**
 * @author XiangYu
 * @create2021-04-03-22:09
 * 给定一个数组， 求子数组的最大异或和。
 * 一个数组的异或和为， 数组中所有的数异或起来的结果。
 *
 * 前缀树
 *
 * 思路： 首先明确一个概念：   （0^.....^n）  ^ (0^..start-1) =  start^ .... ^n;
 */
public class Code_10_Max_EOR {


    /**
     * 通过这个概念优化暴力求解
     *  （0^.....^n）  ^ (0^..start-1) =  start^ .... ^n;
     *
     *  O(n^2)
     * @param arr
     * @return
     */
    public static  int getMax(int[] arr){
        int max = Integer.MIN_VALUE;
        int[] dp  = new int[arr.length];
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            //当前0-i的异或值
            eor ^= arr[i];
            max = Math.max(max,eor);
            for(int start = 1; start<=i; start++){
                //start到i的异或值
                int curRor = eor ^ dp[start -1];
                max = Math.max(max,curRor);
            }
            dp[i] = eor;
        }
        return  max;
    }


    //-----------------------------------------------------前缀树-------------------------------------------------------






    public static class Node {
        /**
         * 两条路，0和1
         */
        public Node[] nexts = new Node[2];
    }

    public static class NumTrie {
        public Node head = new Node();

        public void add(int num) {
            Node cur = head;
            for (int move = 31; move >= 0; move--) {
                //提取出每一位上的数字
                int path = ((num >> move) & 1);

                cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
                cur = cur.nexts[path];
            }
        }

        /**
         *
         * @param num 从0到i的异或结果
         * @return
         */
        public int maxXor(int num) {
            Node cur = head;
            int res = 0;
            for (int move = 31; move >= 0; move--) {
                //提取出每一位上的数字
                int path = (num >> move) & 1;
                //期望值   符号位期望异或结果为0,其他位置期望异或结果为1
                int best = move == 31 ? path : (path ^ 1);
                //实际值   有期望路径则走期望路径，否则就走有的路
                best = cur.nexts[best] != null ? best : (best ^ 1);
                 
                res |= (path ^ best) << move;
                cur = cur.nexts[best];
            }
            return res;
        }

    }

    public static int maxXorSubarray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int eor = 0;
        NumTrie numTrie = new NumTrie();
        numTrie.add(0);
        for (int i = 0; i < arr.length; i++) {
            //
            eor ^= arr[i];
            max = Math.max(max, numTrie.maxXor(eor));
            numTrie.add(eor);
        }
        return max;
    }

    // for test
    public static int comparator(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int eor = 0;
            for (int j = i; j < arr.length; j++) {
                eor ^= arr[j];
                max = Math.max(max, eor);
            }
        }
        return max;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 30;
        int maxValue = 50;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int res = maxXorSubarray(arr);
            int comp = comparator(arr);
            if (res != comp) {
                succeed = false;
                printArray(arr);
                System.out.println(res);
                System.out.println(comp);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
