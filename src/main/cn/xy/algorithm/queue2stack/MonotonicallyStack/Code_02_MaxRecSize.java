package xy.algorithm.queue2stack.MonotonicallyStack;

import java.util.Stack;

/**
 * @author XiangYu
 * @create2021-03-23-21:32
 *
 * 给定一个整型矩阵 map，其中的值只有 0 和 1 两种，求其中全是1的所有矩形区域中，最大的矩形区域为1的数量。
 *
 * 　　例如：
 * 　　1　　1　　1　　0
 * 　　其中，最大的矩形区域有 3 个 1，所以返回3。
 * 　　再如：
 *
 * 　　1　　0　　1　　1
 * 　　1　　1　　1　　1
 * 　　1　　1　　1　　0
 *
 * 　　其中，最大的矩形区域有 6 个 1，所以返回6。
 *
 *     https://blog.csdn.net/diju7500/article/details/102445925
 */
public class Code_02_MaxRecSize {

    /**
     *
     * @param map
     * @return
     */
    public static int maxRecSize(int[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0) {
            return 0;
        }
        int maxArea = 0;
        int[] height = new int[map[0].length];
        // 以第i行做切割
        for (int i = 0; i < map.length; i++) {
            // 第j列位置往上的1的数量，即高度
            for (int j = 0; j < map[0].length; j++) {
                height[j] = map[i][j] == 0 ? 0 : height[j] + 1;
            }
            maxArea = Math.max(maxRecFromBottom(height), maxArea);
        }
        return maxArea;
    }

    /**
     * 计算以当前行做切割（以当前行为底）的情况下，最大的矩形是什么
     * @param height 更新后的 height 数组
     * @return
     */
    public static int maxRecFromBottom(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int maxArea = 0;

        // 单调栈  从小到大的单调栈
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
                int j = stack.pop();
                //左边界
                int k = stack.isEmpty() ? -1 : stack.peek();
                // =((i-1)-(k+1)+1)*height[j]，即，j 向右至少能扩展到 i-1，向左能精确扩展到 k+1，计算 (右边界-左边界+1)*高
                int curArea = (i - k - 1) * height[j];
                maxArea = Math.max(maxArea, curArea);
            }
            stack.push(i);
        }
        // 遍历结束，stack中仍有剩余的元素未经过扩展。需要将它们依次弹出，并计算扩展。
        while (!stack.isEmpty()) {
            int j = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            // 认为 i==leight.length
            int curArea = (height.length - k - 1) * height[j];
            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;
    }


    public static void main(String[] args) {
        int[][] map = {{1, 0, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 0},};
        System.out.println(maxRecSize(map));
    }
}
