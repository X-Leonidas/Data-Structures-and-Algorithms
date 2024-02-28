package cn.xy.leetcode.easy.array;

/**
 * @author xiangyu
 * @date 2024-02-29 1:07
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 * <p>
 * 提示:
 * <p>
 * 1 <= nums.length <= 104
 * -231 <= nums[i] <= 231 - 1
 * <p>
 * <p>
 * 进阶：你能尽量减少完成的操作次数吗？
 */
public class A0283MoveZeroes {


    public void moveZeroes(int[] nums) {
        int start = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                while (start < nums.length && nums[start] == 0) {
                    start++;
                }
                if (start == nums.length) {
                    return;
                }
                nums[i] = nums[start];
                nums[start] = 0;

            } else {
                start++;
            }
        }
    }
}
