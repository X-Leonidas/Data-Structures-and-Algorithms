package cn.xy.leetcode.middle.greed;

/**
 * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
 * 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，
 * 你可以跳转到任意 nums[i + j] 处:
 * 0 <= j <= nums[i]
 * i + j < n
 * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
 * <p>
 * 示例 1:
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 * 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * <p>
 * 示例 2:
 * 输入: nums = [2,3,0,1,4]
 * 输出: 2
 * <p>
 * 提示:
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 1000
 * 题目保证可以到达 nums[n-1]
 *
 * @author XiangYu
 * @date 2021-08-07-17:19
 */
public class A0045JumpGameII {
    public int jump(int[] nums) {
        // 当前位置
        int position = nums.length - 1;
        int steps = 0;
        while (position > 0) {
            for (int i = 0; i < position; i++) {
                // 说明i位置可以到达当前位置
                if (i + nums[i] >= position) {
                    position = i;
                    steps++;
                    break;
                }
            }
        }
        return steps;
    }

    public int jump2(int[] nums) {
        int length = nums.length;
        // 代表这一步能到达的最远的位置
        int end = 0;
        // 代表下一步可以到达的最远位置
        int maxPosition = 0;
        int steps = 0;
        // 不访问最后一个元素：访问最后一个元素之前，我们的边界一定大于等于最后一个位置，否则就无法跳到最后一个位置了
        for (int i = 0; i < length - 1; i++) {
            // 当前位置可以到达的最远的位置
            maxPosition = Math.max(maxPosition, i + nums[i]);
            //
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }

}
