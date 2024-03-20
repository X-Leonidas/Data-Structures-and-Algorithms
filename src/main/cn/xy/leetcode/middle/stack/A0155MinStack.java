package cn.xy.leetcode.middle.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * @author xiangyu
 * @date 2024-03-19 23:49
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * 实现 MinStack 类:
 * MinStack() 初始化堆栈对象。
 * void push(int val) 将元素val推入堆栈。
 * void pop() 删除堆栈顶部的元素。
 * int top() 获取堆栈顶部的元素。
 * int getMin() 获取堆栈中的最小元素。
 * <p>
 * 示例 1:
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 * <p>
 * 提示：
 * -231 <= val <= 231 - 1
 * pop、top 和 getMin 操作总是在 非空栈 上调用
 * push, pop, top, and getMin最多被调用 3 * 104 次
 */
public class A0155MinStack {

    public static void main(String[] args) {

    }

    class MinStack {
        private Deque<Integer> stack;

        private Integer minValue;

        public MinStack() {
            stack = new ArrayDeque<>();
        }

        public void push(int val) {
            if (stack.isEmpty()) {
                minValue = val;
            } else {
                if (val < minValue) {
                    minValue = val;
                }
            }
            stack.addFirst(val);
        }

        public void pop() {
            stack.pop();
            int size = stack.size();
            minValue = Integer.MAX_VALUE;
            for (int i = 0; i < size; i++) {
                Integer pop = stack.pollFirst();
                stack.addLast(pop);
                if (minValue > pop) {
                    minValue = pop;
                }
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minValue;
        }
    }
    class MinStack2 {
        private Deque<Integer> stack;

        private Deque<Integer> minStack;

        public MinStack2() {
            stack = new ArrayDeque<>();
            minStack = new ArrayDeque<>();
        }

        public void push(int val) {
            if (minStack.isEmpty()) {
                minStack.addFirst(val);
            } else {
                if (minStack.peek() > val) {
                    minStack.addFirst(val);
                } else {
                    minStack.addFirst(minStack.peek());
                }
            }
            stack.addFirst(val);
        }

        public void pop() {
            stack.pop();
            minStack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }
}
