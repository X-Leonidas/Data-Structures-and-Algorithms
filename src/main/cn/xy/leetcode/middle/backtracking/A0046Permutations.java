package cn.xy.leetcode.middle.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 * @author XiangYu
 * @create2021-08-07-17:11 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * <p>
 * 示例 2：
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * <p>
 * 示例 3：
 * 输入：nums = [1]
 * 输出：[[1]]
 * <p>
 * 提示：
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 */
public class A0046Permutations {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        A0046Permutations a0046Permutations = new A0046Permutations();
        System.out.println(a0046Permutations.permute2(nums));
    }

    private final List<List<Integer>> result = new ArrayList<>();
    private int bitSum = 0;

    /**
     * 第一种方案， 使用bitNum 来确认数组中那些被使用了
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return result;
        }

        int length = nums.length;
        //生成结束标识 1111， 长度最多为6，没有溢出的风险
        bitSum = (int) (Math.pow(2, length) - 1);
        process(nums, 0, new ArrayList<>());
        return result;
    }

    private void process(int[] nums, int bitNum, List<Integer> list) {
        //end
        if (bitNum == bitSum) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!isOne(bitNum, i)) {
                list.add(nums[i]);
                process(nums, setBit(bitNum, i), list);
                //del the last
                list.remove(list.size() - 1);
            }
        }
    }

    /**
     * 将num的第i位置为1
     *
     * @param num
     * @param i
     * @return
     */
    private static int setBit(int num, int i) {
        return (num | (1 << i));
    }

    /**
     * 查看第i位是否为1
     *
     * @param num
     * @param i
     * @return
     */
    private static boolean isOne(int num, int i) {
        return (num & (1 << i)) != 0;
    }


    /**
     * 第二种方案， 交换
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        List<Integer> output = new ArrayList<>();
        for (int num : nums) {
            output.add(num);
        }
        backtrack(0, nums.length, output, res);
        return res;
    }

    public void backtrack(int index, int end, List<Integer> output, List<List<Integer>> res) {
        if (index == end) {
            res.add(new ArrayList<>(output));
            return;
        }

        for (int i = index; i < end; i++) {
            Collections.swap(output, index, i);
            backtrack(index + 1, end, output, res);
            Collections.swap(output, index, i);
        }
    }

    List<List<Integer>> result3;


    /**
     * 和方案2 类似
     * @param nums
     * @return
     */
    public List<List<Integer>> permute3(int[] nums) {
        result3 = new ArrayList<>();
        dfs(nums, 0);

        return result3;
    }

    private void dfs(int[] nums, int cur) {
        if (cur == nums.length) {
            List<Integer> subset = new ArrayList<>(nums.length);
            for (int num : nums) {
                subset.add(num);
            }
            result3.add(subset);
            return;
        }

        for (int i = cur; i < nums.length; i++) {
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