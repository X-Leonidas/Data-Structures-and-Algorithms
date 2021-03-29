package cn.xy.algorithm.BinaryTree.treeDP;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XiangYu
 * @create2021-03-29-22:17
 *
 *
 * 与Code_09_MaxDistanceInTree是一种类型的题 树形DP
 *
 *
 * 一个公司的上下节关系是一棵多叉树， 这个公司要举办晚会， 你作为组织者已经摸清了大家的心理： 一个员工的直
 * 接上级如果到场， 这个员工肯定不会来。 每个员工都有一个活跃度的值， 决定谁来你会给这个员工发邀请函， 怎么
 * 让舞会的气氛最活跃？ 返回最大的活跃值。
 * 举例：
 * 给定一个矩阵来表述这种关系
 * matrix =
 * { 1
 * ,6
 * 1,5
 * 1,4
 * } 这
 * 个矩阵的含义是：
 * matrix[0] = {1 , 6}， 表示0这个员工的直接上级为1,0这个员工自己的活跃度为6
 * matrix[1] = {1 , 5}， 表示1这个员工的直接上级为1（他自己是这个公司的最大boss） ,1这个员工自己的活跃度
 * 为5
 * matrix[2] = {1 , 4}， 表示2这个员工的直接上级为1,2这个员工自己的活跃度为4
 * 为了让晚会活跃度最大， 应该让1不来， 0和2来。 最后返回活跃度为10
 *
 *
 *  信息体：头节点来的最大活跃度    头节点不来的最大活跃度
 *
 */
public class Code_03_MaxHappy {


    public static class Node{
        public  int huo;
        public List<Node> next;
        public Node(int huo){
            this.huo = huo;
            next = new ArrayList<>();

        }
    }


    public static class ReturnData{
        public int lai_huo;
        public int bu_lai_huo;

        public ReturnData(int lai_huo,int bu_lai_huo){
            this.lai_huo = lai_huo;
            this.bu_lai_huo = bu_lai_huo;
        }
    }

    public static int getMaxHappy(Node head){
        ReturnData data = process(head);
        return Math.max(data.lai_huo,data.bu_lai_huo);
    }

    public static ReturnData process(Node head){
        int lai_huo = head.huo ;
        int bu_lai_huo = 0;
        for (int i = 0; i < head.next.size(); i++) {
            Node next = head.next.get(i);
            ReturnData nextData = process(next);
            lai_huo += nextData.bu_lai_huo;
            bu_lai_huo += Math.max(nextData.lai_huo,nextData.bu_lai_huo);
        }
        return  new ReturnData(lai_huo,bu_lai_huo);
    }
















    public static int maxHappy(int[][] matrix) {
        int[][] dp = new int[matrix.length][2];
        boolean[] visited = new boolean[matrix.length];
        int root = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (i == matrix[i][0]) {
                root = i;
            }
        }
        process(matrix, dp, visited, root);
        return Math.max(dp[root][0], dp[root][1]);
    }

    public static void process(int[][] matrix, int[][] dp, boolean[] visited, int root) {
        visited[root] = true;
        dp[root][1] = matrix[root][1];
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == root && !visited[i]) {
                process(matrix, dp, visited, i);
                dp[root][1] += dp[i][0];
                dp[root][0] += Math.max(dp[i][1], dp[i][0]);
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 8 }, { 1, 9 }, { 1, 10 } };
        System.out.println(maxHappy(matrix));
    }

}
