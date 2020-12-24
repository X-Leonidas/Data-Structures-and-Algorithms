package cn.xy.leetcode.middle.dp;

/**
 * @author XiangYu
 * @create2020-12-23-14:40 最长递增子序列
 * <p>
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * <p>
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序
 * 。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * <p>
 *  
 * 示例 1：
 * <p>
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * 示例 2：
 * <p>
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 * 示例 3：
 * <p>
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 */
public class A0300LongestIncreasingSubsequence {

    public static void main(String[] args) {
        int[] ints = {1,3,5,4,7};
        int l = findNumberOfLIS2(ints);
    }


    public static int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }


    /**
     * 给定一个未排序的整数数组，找到最长递增子序列的个数。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [1,3,5,4,7]
     * 输出: 2
     * 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
     * 示例 2:
     * <p>
     * 输入: [2,2,2,2,2]
     * 输出: 5
     * 解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
     * 注意: 给定的数组长度不超过 2000 并且结果一定是32位有符号整数。
     *
     * @param nums
     * @return
     */
    public static int findNumberOfLIS2(int[] nums) {
        int length = nums.length;
        int[] dp = new int[length];
        dp[0] = 1;

        int sum = 0;
        int sum2 = 0;
        int maxans = 1;
        int maxansIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = i; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            if(maxans < dp[i]){
                maxans = dp[i];
                maxansIndex  = i;
            }
        }


        for (int i = 0; i < dp.length; i++) {
            if(maxans == dp[i]+1 && nums[maxansIndex] > nums[i]){
                sum++;
            }else if(maxans == dp[i]){
                sum2++;
            }
        }


        if(sum == 0){
            return  sum2;
        }else{
            return sum * sum2;
        }

    }


}
