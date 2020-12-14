package cn.xy.leetcode.stringtest;

/**
 * @author XiangYu
 * @create2020-12-09-9:46 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。如果不存在符合条件的子数组，返回 0。
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：s = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 *  
 * 进阶：
 * 如果你已经完成了 O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
 */
public class StringTest4_6 {
    public static void main(String[] args) {
        int[] ints = {2, 3, 1, 2, 4, 3};
        int i = minSubArrayLen(7, ints);
        System.out.println(i);

    }

    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了85.53%的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了87.41%的用户
     * 自己的双指针
     * @param s
     * @param nums
     * @return
     */
    public static int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;

        if (n == 0) {
            return 0;
        }

        int start = 0;
        int end = 0;
        int sum = 0;
        //先设置为最大值加1
        int min = nums.length + 1;

        for (int i = 0; i < n; i++) {
            //每次循环都要加上一个元素，直到指针移动到数组末尾
            if (end != nums.length) {
                sum = sum + nums[end];
                end++;
            }
            while (sum >= s) {
                sum = sum - nums[start];
                min = Math.min(min, end - start);
                start++;
                i--;
            }
        }
        if (min == nums.length + 1) {
            return 0;
        }
        return min;

    }


    /**
     * 官方双指针
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen2(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int start = 0, end = 0;
        int sum = 0;
        while (end < n) {
            sum += nums[end];
            while (sum >= s) {
                ans = Math.min(ans, end - start + 1);
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

}
