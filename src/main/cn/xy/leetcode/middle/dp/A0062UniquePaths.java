package cn.xy.leetcode.middle.dp;

import java.util.Arrays;

/**
 * @author xiangyu
 * @date 2025-03-24 0:01
 * <p>
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 * <p>
 * 示例 1：
 * 输入：m = 3, n = 7
 * 输出：28
 * <p>
 * 示例 2：
 * 输入：m = 3, n = 2
 * 输出：3
 * 解释：
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向下
 * <p>
 * 示例 3：
 * 输入：m = 7, n = 3
 * 输出：28
 * <p>
 * 示例 4：
 * 输入：m = 3, n = 3
 * 输出：6
 * <p>
 * 提示：
 * 1 <= m, n <= 100
 * 题目数据保证答案小于等于 2 * 109
 */
public class A0062UniquePaths {
    public int uniquePaths(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        }
        int[][] dp = new int[m][n];

        for (int x = 0; x < n; x++) {
            dp[0][x] = 1;
        }

        for (int y = 0; y < m; y++) {
            dp[y][0] = 1;
        }

        for(int x = 1; x < n; x++){
            for(int y = 1; y < m; y++){
                dp[y][x] = dp[y - 1][x] + dp[y][x - 1];
            }
        }
        return dp[m-1][n-1];
    }

    /**
     * 空间优化的版本
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths2(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        }
        int[] dp = new int[m];

        Arrays.fill(dp, 1);

        for(int x = 1; x < n; x++){
            int lastX = 1;
            for(int y = 1; y < m; y++){
                lastX = dp[y] + lastX;
                dp[y] = lastX;
            }
        }
        return dp[m-1];
    }


}
