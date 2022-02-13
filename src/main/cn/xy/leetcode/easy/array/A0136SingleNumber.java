package cn.xy.leetcode.easy.array;

import java.util.Arrays;

/**
 * @author XiangYu
 * @create2020-12-17-15:52 只出现一次的数字
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * <p>
 * 说明：
 * <p>
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,2,1]
 * 输出: 1
 * 示例 2:
 * <p>
 * 输入: [4,1,2,1,2]
 * 输出: 4
 */
public class A0136SingleNumber {
    //第一种
    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = nums.length - 1; i >= 0; i = i - 2) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                return nums[i];
            }
        }
        return -1;
    }


    /**
     * leetcod：异或算法
     *
     * @param nums
     * @return
     */
    public int singleNumber2(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }



}
