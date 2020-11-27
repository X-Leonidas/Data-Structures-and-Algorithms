package cn.xy.leetcode.string;

/**
 * @author XiangYu
 * @create2020-11-26-14:45
 *
 * 搜索插入位置
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 你可以假设数组中无重复元素。
 *
 * 示例 1:
 *
 * 输入: [1,3,5,6], 5
 * 输出: 2
 * 示例 2:
 *
 * 输入: [1,3,5,6], 2
 * 输出: 1
 * 示例 3:
 *
 * 输入: [1,3,5,6], 7
 * 输出: 4
 * 示例 4:
 *
 * 输入: [1,3,5,6], 0
 * 输出: 0
 *
 *
 *
 */
public class StringTest1 {
    public static void main(String[] args) {
        int[] nums = {1,3};
        int tartget = 2;

        System.out.println(searchInsert(nums, tartget));

    }

    /**
     * 执行用时：0 ms在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了90.05%的用户
     *
     *
     *
     *
     * 解题思路：因为是有序的，先用二分法找到最接近目标元素的索引，然后根据索引上的值与目标元素进行判断，
     * 如果大于索引则放值在索引前面，如果小则顶替索引的位置
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int middle = 0;
        while (start <= end) {
            middle = (start + end) / 2;
            if (nums[middle] == target) {
                return middle;
            }else if (nums[middle] > target) {
                end = middle -1;
            } else {
                start = middle + 1;
            }
        }

        if(nums[middle] > target){
           return  middle == 0 ? 0 : middle;
        }
        if (nums[middle] < target) {
           return  middle + 1;
        }
        return  middle;
    }
}
