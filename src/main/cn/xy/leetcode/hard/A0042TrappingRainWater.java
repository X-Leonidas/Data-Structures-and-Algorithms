package cn.xy.leetcode.hard;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author xiangyu
 * @date 2024-02-29 19:27
 * <p>
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）
 * <p>
 * 示例 2：
 * <p>
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == height.length
 * 1 <= n <= 2 * 104
 * 0 <= height[i] <= 105
 * <p>
 * ** 双指针不一定只能end向右移动 left跟上，也可以left和right 往两边移动
 */
public class A0042TrappingRainWater {
    public static void main(String[] args) {
        int[] ints = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
//        int[] ints = {2, 0, 2};
        System.out.println(new A0042TrappingRainWater().trap2(ints));
    }

    /**
     * 超时
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }

        int maxGao = 0;
        for (int i : height) {
            if (i > maxGao) {
                maxGao = i;
            }
        }
        int ans = 0;
        // 层高
        int gao = 1;
        int start = 0;
        while (height[start] == 0) {
            start++;
        }
        while (gao <= maxGao) {
            int curstart = start;
            int end = height.length - 1;
            while (height[curstart] < gao) {
                curstart++;
            }

            while (height[end] < gao) {
                end--;
            }

            for (int i = curstart; i <= end; i++) {
                if (height[i] < gao) {
                    ans++;
                }
            }
            gao++;
        }
        return ans;
    }

    /**
     * 动态规划
     *
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        int ans = 0;
        // leftMax[i] 是 height[i]左边能取到的最大的值
        int[] leftMax = new int[height.length];
        leftMax[0] = height[0];
        // rightMax[i] 是 height[i] 右边能取到的最大的值
        int[] rightMax = new int[height.length];
        rightMax[rightMax.length - 1] = height[height.length - 1];
        for (int i = 1; i < height.length; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i - 1]);
        }

        for (int i = height.length - 2; i >= 0; i--) {
            rightMax[i] = Math.max(height[i], rightMax[i + 1]);
        }

        for (int i = 1; i < height.length - 1; i++) {
            ans = ans + Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        return ans;
    }


    /**
     * 单调栈
     *
     * @param height
     * @return
     */
    public int trap3(int[] height) {
        int ans = 0;
        Deque<Integer> stack = new LinkedList<>();
        int n = height.length;
        for (int i = 0; i < n; ++i) {
            //比栈顶小的直接压进去, 当前高度比栈顶大，计算高水量
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                // 最低的高度
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                // 最近的左墙
                int left = stack.peek();
                // i是右墙
                int currWidth = i - left - 1;
                int currHeight = Math.min(height[left], height[i]) - height[top];
                ans += currWidth * currHeight;
            }
            stack.push(i);
        }
        return ans;
    }
}
