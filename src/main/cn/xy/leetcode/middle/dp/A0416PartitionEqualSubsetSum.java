package cn.xy.leetcode.middle.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author xiangyu
 * [416] 分割等和子集
 * https://leetcode.cn/problems/partition-equal-subset-sum/description/
 * <p>
 * 给你一个 只包含正整数 的 非空 数组nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * <p>
 * 示例 1：
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：数组不能分割成两个元素和相等的子集。
 * <p>
 * 提示：
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 * @date 2025/4/7 15:04
 */
public class A0416PartitionEqualSubsetSum {
    /**
     * Your runtime beats 5.12 % of java submissions
     * Your memory usage beats 20.41 % of java submissions (45.8 MB)
     *
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        if (nums.length <= 1) {
            return false;
        }

        HashSet<Integer> cache = new HashSet<>();
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            List<Integer> temp = new ArrayList<>();

            for (Integer value : cache) {
                temp.add(value + nums[i]);
            }

            cache.addAll(temp);
            cache.add(nums[0]);
            sum = sum + nums[i];
        }

        if (sum % 2 != 0) {
            return false;
        }

        return cache.contains(sum / 2);
    }


    public boolean canPartition2(int[] nums) {
        int n = nums.length;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }

        if (sum % 2 == 1) {
            return false;
        }
        int target = sum / 2;

        /**
         * 从0 到 target 是否在数组中nums中存在
         */
        boolean[] dp = new boolean[target + 1];
        Arrays.fill(dp, false);
        dp[0] = true;
        // 找出nus 每一个元素可以到达的位置
        for (int i = 0; i < n; i++) {
            // j - nums[i] 代表 nums[i]是否可以到达j位置
            for (int j = target; j >= nums[i]; j--) {
                if (dp[j - nums[i]]) {
                    dp[j] = true;
                }
            }
        }

        return dp[target];
    }

    /**
     * 二维动态规划
     *
     * @param nums
     * @return
     */
    public boolean canPartition3(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        if (maxNum > target) {
            return false;
        }
        boolean[][] dp = new boolean[n][target + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        dp[0][nums[0]] = true;
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            for (int j = 1; j <= target; j++) {
                if (j >= num) {
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - num];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n - 1][target];
    }

}