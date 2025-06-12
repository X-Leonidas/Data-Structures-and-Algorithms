package cn.xy.leetcode.easy.array;
/*
 * [977] 有序数组的平方
 * https://leetcode.cn/problems/squares-of-a-sorted-array/description/
 *
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 *
 * 示例 1：
 * 输入：nums = [-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 解释：平方后，数组变为 [16,1,0,9,100]
 * 排序后，数组变为 [0,1,9,16,100]
 * 
 * 示例 2：
 * 输入：nums = [-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 *
 * 提示：
 * 1 <= nums.length <= 10^4
 * -10^4 <= nums[i] <= 10^4
 * nums 已按 非递减顺序 排序
 *
 * 进阶：
 * 请你设计时间复杂度为 O(n) 的算法解决本问题
 *
 */

// @lc code=start
public class A0977SquaresOfSortedArray {
    public int[] sortedSquares(int[] nums) {
        int[] ans = new int[nums.length];

        int left = 0;
        int right = nums.length - 1;
        int index = right;
        while (left <= right) { 
            if (Math.abs(nums[left]) > Math.abs(nums[right])) {
                ans[index] = nums[left] * nums[left];

                index--;
                left ++;
            } else {
                ans[index] = nums[right] * nums[right];

                index--;
                right--;
            }
        }

        return ans;
    }
}