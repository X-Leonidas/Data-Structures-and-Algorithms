package cn.xy.leetcode.easy.array;

/**
 * @author XiangYu
 * @create2021-04-14-15:40
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 */
public class A0053MaximumSubarray {
    /**
     * 倒叙 dp
     * 执行用时：1 ms, 在所有 Java 提交中击败了94.82%的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了16.99%的用户
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int size  = nums.length;
        int[] dp  = new int[size];


        dp[size -1 ] = nums[size-1];
        int max =dp[size -1 ];
        for (int i = size-1; i > 0; i--) {
            //数组前一个数
            int per = i -1;
            dp[per] = Math.max(nums[per]+dp[i],nums[per]);

            max  = Math.max(max,dp[per]);
        }

        return max;
    }


    /**
     * 优化空间
     *  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了87.13%的用户
     *
     * @param nums
     * @return
     */
    public int maxSubArray2(int[] nums) {
        int size  = nums.length;

        int max = nums[size-1];
        int index = nums[size-1];
        for (int i = size-1; i > 0; i--) {
            //数组前一个数
            int per = i -1;

            index = Math.max(nums[per]+index,nums[per]);
            max  = Math.max(max,index);
        }

        return max;
    }




}
