package cn.xy.leetcode.middle.dp;

import java.util.Arrays;

/**
 * @author xiangyu
 * @date 2024-03-31 21:25
 * <p>
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
 * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * <p>
 * 示例 1：
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 * <p>
 * 示例 2：
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * <p>
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 */
public class A0198HouseRobber {
    public static void main(String[] args) {
        A0198HouseRobber a0198HouseRobber = new A0198HouseRobber();
        a0198HouseRobber.rob(new int[]{1, 2, 1, 1});
    }

    /**
     * 每次小偷偷窃都要跳过一间或者跳过两间，并且可以从第一个开始或者从第二个开始
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        int one = dp(0, nums, dp);
        int two = dp(1, nums, dp);
        return Math.max(one, two);
    }


    public int dp(int index, int[] nums, int[] dp) {
        if (index >= dp.length) {
            return 0;
        }
        if (dp[index] != -1) {
            return dp[index];
        }

        // 跳过一个 或者 跳过 两个 加上后面比较大的结果
        dp[index] = nums[index] + Math.max(dp(index + 2, nums, dp), dp(index + 3, nums, dp));
        return dp[index];
    }

    public int rob2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            // 决定当前这个偷不偷
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[length - 1];
    }
}
