package cn.xy.leetcode.easy;

/**
 * @author XiangYu
 * @create2020-12-21-13:51 使用最小花费爬楼梯
 * <p>
 * 数组的每个索引作为一个阶梯，第 i个阶梯对应着一个非负数的体力花费值 cost[i](索引从0开始)。
 * <p>
 * 每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。
 * <p>
 * 您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。
 * <p>
 * 示例 1:
 * <p>
 * 输入: cost = [10, 15, 20]
 * 输出: 15
 * 解释: 最低花费是从cost[1]开始，然后走两步即可到阶梯顶，一共花费15。
 * 示例 2:
 * <p>
 * 输入: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
 * 输出: 6
 * 解释: 最低花费方式是从cost[0]开始，逐个经过那些1，跳过cost[3]，一共花费6。
 * 注意：
 * <p>
 * cost 的长度将会在 [2, 1000]。
 * 每一个 cost[i] 将会是一个Integer类型，范围为 [0, 999]。
 *
 *
 *
 *
 * DP
 */
public class A0746MinCostClimbingStairs {

    public static void main(String[] args) {
        int[] ints = {0,1,1,0};
        System.out.println(minCostClimbingStairs(ints));
    }

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.68%的用户
     * 内存消耗：37.8 MB, 在所有 Java 提交中击败了97.95%的用户
     * @param cost
     * @return
     */
    public static  int minCostClimbingStairs(int[] cost) {
        int length = cost.length;

        int[] dp = new int[length];
        if(cost.length == 1){
            return  cost[0];
        }

        if(cost.length == 2){
            return  Math.min(cost[0],cost[1]);
        }

        dp[0] = cost[0];
        dp[1] = cost[1];

        for (int i = 2; i < cost.length; i++) {
            dp[i] = Math.min(dp[i-1],dp[i-2])+ cost[i];
        }

        return  Math.min(dp[length-1],dp[length-2]);
    }
}
