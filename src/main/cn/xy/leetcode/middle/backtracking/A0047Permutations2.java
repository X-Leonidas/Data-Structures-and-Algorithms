package cn.xy.leetcode.middle.backtracking;

import java.util.*;

/**
 * @author xiangyu
 * @date 2024-03-13 21:55
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列
 * <p>
 * 示例 1：
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 * [1,2,1],
 * [2,1,1]]
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * <p>
 * 提示：
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 */
public class A0047Permutations2 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 2};
        A0047Permutations2 a0047Permutations2 = new A0047Permutations2();
        a0047Permutations2.permuteUnique(nums);
    }

    List<List<Integer>> result;

    public List<List<Integer>> permuteUnique(int[] nums) {
        result = new ArrayList<>();
        dfs(nums, 0);
        return result;
    }

    private void dfs(int[] nums, int cur) {
        if (cur == nums.length) {
            List<Integer> subset = new ArrayList<>(nums.length);
            for (int num : nums) {
                subset.add(num);
            }
            result.add(subset);
            return;
        }
        Set<Integer> cache = new HashSet<>();
        for (int i = cur; i < nums.length; i++) {
            if (cache.contains(nums[i])) {
                continue;
            }else{
                cache.add(nums[i]);
            }
            swap(nums, cur, i);
            dfs(nums, cur + 1);
            swap(nums, cur, i);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

}
