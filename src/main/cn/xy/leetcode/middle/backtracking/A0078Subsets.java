package cn.xy.leetcode.middle.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiangyu
 * @date 2024-03-15 1:51
 * <p>
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的
 * 子集
 * （幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * <p>
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[[],[0]]
 * <p>
 * 提示：
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 */
public class A0078Subsets {
    public List<List<Integer>> subsets(int[] nums) {
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
        for (int i = index; i < nums.length; i++) {
            output.add(nums[i]);
            ans.add(new ArrayList<>(output));
            backtrack(nums, i + 1, output, ans);
            output.remove(output.size() - 1);
        }
    }
}
