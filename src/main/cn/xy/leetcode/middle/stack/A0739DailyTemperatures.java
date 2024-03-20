package cn.xy.leetcode.middle.stack;

import cn.xy.algorithm.windows.Windows;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiangyu
 * @date 2024-03-20 21:56
 * <p>
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，
 * 其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * <p>
 * 示例 1:
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 * <p>
 * 示例 2:
 * 输入: temperatures = [30,40,50,60]
 * 输出: [1,1,1,0]
 * <p>
 * 示例 3:
 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 * <p>
 * 提示：
 * 1 <= temperatures.length <= 105
 * 30 <= temperatures[i] <= 100
 */
public class A0739DailyTemperatures {

    public static void main(String[] args) {

    }

    public int[] dailyTemperatures(int[] temperatures) {
        if (temperatures.length == 1) {
            return new int[]{0};
        }
        Deque<Integer> cache = new ArrayDeque<>();
        int[] ans = new int[temperatures.length];
        // 这个是不必要的
        Deque<Integer> stack = new ArrayDeque<>();
        cache.addFirst(0);
        stack.addFirst(temperatures[0]);
        for (int i = 1; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > stack.peekFirst()) {
                ans[cache.peekFirst()] = i - cache.peekFirst();
                stack.pollFirst();
                cache.pollFirst();
            }
            cache.addFirst(i);
            stack.addFirst(temperatures[i]);
        }
        // 初始化后默认为0，可以省略
//        while (!stack.isEmpty()) {
//            stack.pollFirst();
//            ans[cache.pollFirst()] = 0;
//        }
        return ans;
    }

    public int[] dailyTemperatures2(int[] temperatures) {
        int length = temperatures.length;
        int[] ans = new int[length];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < length; i++) {
            int temperature = temperatures[i];
            while (!stack.isEmpty() && temperature > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                ans[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return ans;
    }


}
