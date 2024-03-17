package cn.xy.leetcode.middle.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author xiangyu
 * @date 2024-03-15 1:57
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 * 示例 1：
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 * <p>
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[[],[0]]
 * <p>
 * 提示：
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 */
public class A0090SubsetsII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> output = new ArrayList<>();
        ans.add(new ArrayList<>());
        backtrack(nums, 0, output, ans);
        return ans;
    }

    private void backtrack(int[] nums, int index, List<Integer> output, List<List<Integer>> ans) {
        if (index == nums.length) {
            return;
        }
        // [4,4,4,1,4] 不通过， 因为子集的原因，所以可能会出现1，4 和4，1 存在
        HashSet<Integer> cache = new HashSet<>();
        for (int i = index; i < nums.length; i++) {
            if (cache.contains(nums[i])) {
                continue;
            } else {
                cache.add(nums[i]);
            }
            output.add(nums[i]);
            ans.add(new ArrayList<>(output));
            backtrack(nums, i + 1, output, ans);
            output.remove(output.size() - 1);
        }
    }

    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> output = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        ans.add(new ArrayList<>());
        backtrack2(used, nums, 0, output, ans);
        return ans;
    }

    private void backtrack2(boolean[] used, int[] nums, int index, List<Integer> output, List<List<Integer>> ans) {
        if (index == nums.length) {
            return;
        }
        for (int i = index; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            output.add(nums[i]);
            used[i] = true;
            ans.add(new ArrayList<>(output));
            backtrack2(used, nums, i + 1, output, ans);
            output.remove(output.size() - 1);
            used[i] = false;
        }
    }
}
