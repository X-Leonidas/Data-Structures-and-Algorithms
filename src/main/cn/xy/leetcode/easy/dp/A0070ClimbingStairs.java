package cn.xy.leetcode.easy.dp;

import java.util.Arrays;

/**
 * @author xiangyu
 * @date 2024-03-28 1:28
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 示例 1：
 * 输入：n = 2
 * 输出：2
 * 解释：有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 * <p>
 * 示例 2：
 * 输入：n = 3
 * 输出：3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 * <p>
 * 提示：
 * 1 <= n <= 45
 */
public class A0070ClimbingStairs {
    public int climbStairs(int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        dp(n, 0, dp);
        return dp[0];
    }

    public int dp(int n, int index, int[] dp) {
        if (index == n) {
            return 1;
        } else if (index > n) {
            return 0;
        }
        if (dp[index] != -1) {
            return dp[index];
        }
        int ans = dp(n, index + 1, dp) + dp(n, index + 2, dp);
        dp[index] = ans;
        return ans;
    }


    public int climbStairs2(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        dp[n] = 1;
        dp[n - 1] = 2;

        for (int i = n - 2; i > 0; i--) {
            dp[i] = dp[i + 1] + dp[i + 2];
        }

        return dp[1];
    }

    public static void main(String[] args) {
        A0070ClimbingStairs a0070ClimbingStairs = new A0070ClimbingStairs();
        a0070ClimbingStairs.climbStairs3(4);
    }
    public int climbStairs3(int n) {
        if (n == 2) {
            return 2;
        }
        int next = 2;
        int nextNext = 1;
        int cur = 1;
        for (int i = n - 2; i > 0; i--) {
            cur = next + nextNext;
            nextNext = next;
            next = cur;
        }

        return cur;
    }
}
