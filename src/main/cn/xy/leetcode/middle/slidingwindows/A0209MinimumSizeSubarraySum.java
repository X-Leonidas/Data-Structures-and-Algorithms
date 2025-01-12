package cn.xy.leetcode.middle.slidingwindows;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * 找出该数组中满足其总和大于等于 target 的长度最小的子数组
 * [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 * <p>
 * 示例 1：
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * <p>
 * 示例 2：
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * <p>
 * 示例 3：
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 * <p>
 * 提示：
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 104
 * <p>
 * 进阶：
 * 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
 *
 * @author xiangyu
 * @date 2025-01-04 16:28
 */
public class A0209MinimumSizeSubarraySum {
    public static void main(String[] args) {
        new A0209MinimumSizeSubarraySum().minSubArrayLen(7,new int[]{2,3,1,2,4,3});
    }

    public int minSubArrayLen(int target, int[] nums) {
        int start = 0;
        int cur = 0;
        int length = nums.length;
        int result = Integer.MAX_VALUE;

        for(int end = 0; end < length; end++ ){
            cur = cur + nums[end];
            while(cur >= target && start <= end){
                result = Math.min(result, end - start + 1);
                cur = cur  - nums[start];
                start++;
            }
        }

        return result == Integer.MAX_VALUE ? 0 : result;
    }

}
