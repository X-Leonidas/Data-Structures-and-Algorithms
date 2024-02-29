package cn.xy.leetcode.middle.doublepointer;

import java.util.*;

/**
 * @author xiangyu
 * @date 2024-02-29 16:40
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
 * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
 * <p>
 * 你返回所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 解释：
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
 * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
 * 注意，输出的顺序和三元组的顺序并不重要。
 * 示例 2：
 * <p>
 * 输入：nums = [0,1,1]
 * 输出：[]
 * 解释：唯一可能的三元组和不为 0 。
 * 示例 3：
 * <p>
 * 输入：nums = [0,0,0]
 * 输出：[[0,0,0]]
 * 解释：唯一可能的三元组和为 0 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 3 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 */
public class A0015ThreeSum {

    /**
     * 416ms击败6.39%使用 Java 的用户
     * 54.71MB击败5.05%使用 Java 的用户
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        Set<String> cache = new HashSet<>();
        for (int i = 1; i < nums.length - 1; i++) {
            int start = i - 1;
            int end = i + 1;
            int target = nums[i];
            while (start >= 0 && end < nums.length) {
                if (nums[start] + nums[end] + target > 0) {
                    if (start == 0) {
                        break;
                    }
                    start--;
                } else if (nums[start] + nums[end] + target < 0) {
                    if (end == nums.length - 1) {
                        break;
                    }
                    end++;
                } else {
                    String s = "" + nums[start] + target + nums[end];
                    if (!cache.contains(s)) {
                        cache.add(s);
                        result.add(List.of(nums[start], target, nums[end]));
                    }
                    start--;
                    end++;
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
       int[] ints =  new int[]{-1,0,1,2,-1,-4};
       new A0015ThreeSum().threeSum2(ints);
    }

    /**
     * 优化版本，
     * 不使用hash保证唯一性，而是使用排序保证
     * 修改双指针的位置，减少移动的范围
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            // 剪枝
            if (nums[i] > 0) {
                break;
            }
            // 去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int start = i + 1;
            int end = nums.length - 1;
            while (start < end) {
                int sum = nums[i] + nums[start] + nums[end];
                if (sum > 0) {
                    end--;
                } else if (sum < 0) {
                    start++;
                } else {
                    result.add(Arrays.asList(nums[i], nums[start], nums[end]));
                    // 去重
                    while (start < end && nums[start] == nums[start + 1]) {
                        start++;
                    }
                    // 去重
                    while (start < end && nums[end] == nums[end - 1]) {
                        end--;
                    }
                    start++;
                    end--;
                }
            }
        }
        return result;
    }
}
