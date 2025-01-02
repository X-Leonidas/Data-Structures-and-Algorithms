package cn.xy.leetcode.middle.twopointer;


/**
 * @author XiangYu
 * @Create 2021年7月16日23点35分
 * <p>
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点(i,ai) 。
 * 在坐标内画 n 条垂直线，垂直线 的两个端点分别为(i,ai) 和 (i, 0) 。找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为49。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：height = [1,1]
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：height = [4,3,2,1,4]
 * 输出：16
 * 示例 4：
 * <p>
 * 输入：height = [1,2,1]
 * 输出：2
 * <p>
 * 提示：
 * <p>
 * n = height.length
 * 2 <= n <= 3 * 104
 * 0 <= height[i] <= 3 * 104
 */
public class A0011ContainerWithMostWater {

    public static void main(String[] args) {
        int[] ints = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        maxArea(ints);
    }

    /**
     * 使用双指针，每次移动的指针的条件判断为：移动当前数值较小的指针，因为如果移动大的，不可能出现更大的面积
     *
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        int max = -1;
        int start = 0;
        int end = height.length - 1;

        while (start < end) {
            max = Math.max(max, Math.min(height[start], height[end]) * (end - start));
            if (height[end] > height[start]) {
                start++;
            } else {
                end--;
            }
        }
        return max;
    }
}
