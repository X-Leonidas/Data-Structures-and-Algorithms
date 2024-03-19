package cn.xy.leetcode.easy.binarysearch;

/**
 * @author xiangyu
 * @date 2024-03-18 22:32
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 请必须使用时间复杂度为 O(log n) 的算法。
 * <p>
 * 示例 1:
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 * <p>
 * 示例 2:
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 * <p>
 * 示例 3:
 * 输入: nums = [1,3,5,6], target = 7
 * 输出: 4
 * <p>
 * 提示:
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums 为 无重复元素 的 升序 排列数组
 * -104 <= target <= 104
 */
public class A0035SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int mid = start + (end - start) / 2;

        while (start <= end) {
            if (nums[mid] > target) {
                end = mid - 1;
                mid = start + (end - start) / 2;
            } else if (nums[mid] < target) {
                start = mid + 1;
                mid = start + (end - start) / 2;
            } else {
                return mid;
            }
        }
        return mid;
    }
}
