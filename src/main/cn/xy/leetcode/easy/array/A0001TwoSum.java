package cn.xy.leetcode.easy.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiangyu
 * @date 2024-02-29 0:48
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * <p>
 * 你可以按任意顺序返回答案。
 */
public class A0001TwoSum {

    public static void main(String[] args) {
        int[] ints = {3, 2, 4};
        System.out.println(Arrays.toString(new A0001TwoSum().twoSum(ints, 6)));
    }


    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> cache = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; ++i) {
            if (cache.containsKey(target - nums[i])) {
                return new int[]{cache.get(target - nums[i]), i};
            }
            cache.put(nums[i], i);
        }
        return new int[0];
    }
}
