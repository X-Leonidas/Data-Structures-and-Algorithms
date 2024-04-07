package cn.xy.leetcode.middle.dp;

import java.util.Arrays;

/**
 * @author xiangyu
 * @date 2022-09-20 23:38
 * <p>
 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 * <p>
 * 示例1：
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 * <p>
 * 示例 2：
 * 输入：n = 13
 * 输出：2
 * 解释：13 = 4 + 9
 */
public class A00279PerfectSquares {
    public static void main(String[] args) {
        A00279PerfectSquares a00279PerfectSquares = new A00279PerfectSquares();
        System.out.println(a00279PerfectSquares.numSquares2(48));
    }

    public static int numSquares(int n) {
        int[] f = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int minn = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                minn = Math.min(minn, f[i - j * j]);
            }
            f[i] = minn + 1;
        }
        return f[n];
    }


    public int numSquares2(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return dp(0, n, 0, dp);
    }

    public int dp(int index, int n, int length, int[] dp) {
        if (index == n) {
            return length;
        }
        if (dp[index] != -1) {
            return dp[index];
        }
        int ans = Integer.MAX_VALUE;
        int sqrt = (int) Math.sqrt(n - index);
        for (int i = sqrt; i > 0; i--) {
            int temp = dp((int) (index + Math.pow(i, 2)), n, length + 1, dp);
            ans = Math.min(temp, ans);
        }
        dp[n] = ans;
        return ans;
    }

    public int numSquares3(int n) {
        int[] f = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int minn = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                minn = Math.min(minn, f[i - j * j]);
            }
            f[i] = minn + 1;
        }
        return f[n];
    }
}
