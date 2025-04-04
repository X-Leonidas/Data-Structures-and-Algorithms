package cn.xy.leetcode.middle.array;

import java.util.HashMap;

/**
 * @author xiangyu
 * @date 2024-01-31 22:01
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 * 子数组是数组中元素的连续非空序列。
 * <p>
 * 示例 1：
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 * <p>
 * 提示：
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 */
public class A0560SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        int result = 0;

        if (null == nums || nums.length < 1) {
            return result;
        }
        int sum = 0;
        HashMap<Integer, Integer> cache = new HashMap<>(nums.length);
        // 代表前缀和为0的个数，默认是1
        cache.put(0, 1);
        for (int num : nums) {
            sum = sum + num;
            if (cache.containsKey(sum - k)) {
                result = result + cache.get(sum - k);
            }
            cache.put(sum, cache.getOrDefault(sum, 0) + 1);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] ints = {1, -1, 0};
        int i = subarraySum2(ints, 0);
        System.out.println(i);
    }

    public static int subarraySum2(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; ++start) {
            int sum = 0;
            for (int end = start; end >= 0; --end) {
                sum += nums[end];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }


}