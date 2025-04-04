package cn.xy.leetcode.middle.dp;

import java.util.Arrays;

/**
 * @author xiangyu
 * @date 2025-04-04 23:19
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * <p>
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * <p>
 * 你可以认为每种硬币的数量是无限的。
 * <p>
 * 示例 1：
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * <p>
 * 示例 2：
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * <p>
 * 示例 3：
 * 输入：coins = [1], amount = 0
 * 输出：0
 * <p>
 * 提示：
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 231 - 1
 * 0 <= amount <= 104
 */
public class A0322CoinChange {
    /**
     * 可以和A00279PerfectSquares 结合起来看
     */
    public static void main(String[] args) {
        new A0322CoinChange().coinChange(new int[]{1, 2, 5}, 11);
    }

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                // dp[i - coin] == -1 代表当前位置达不到
                if (coin <= i && dp[i - coin] != -1) {
                    min = Math.min(min, dp[i - coin]);
                }
            }
            // 至少有一个可以达到当前位置才可以赋值
            if (min != Integer.MAX_VALUE) {
                dp[i] = min + 1;
            }else{
                dp[i] = -1;
            }
        }

        return dp[amount];
    }


}
