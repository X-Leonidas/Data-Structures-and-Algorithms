package cn.xy.leetcode.easy.stack;

import java.util.ArrayDeque;

/**
 * @author XiangYu
 * @create2021-04-10-21:55
 *
 *   设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 *  
 *
 * 示例:
 *
 * 输入：
 *  * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 *  * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 *
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 *  
 *
 * 提示：
 *
 * pop、top 和 getMin 操作总是在 非空栈 上调用。
 *
 */

/**
 * 执行用时：7 ms, 在所有 Java 提交中击败了79.77%的用户
 * 内存消耗：40.2 MB, 在所有 Java 提交中击败了57.52%的用户
 */
public class A0155MinStack {

    ArrayDeque<Integer> stack;
    ArrayDeque<Integer> minStack;

    public A0155MinStack() {
        stack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
        minStack.offer(Integer.MAX_VALUE);
    }

    public void push(int val) {
        stack.offer(val);
        if(minStack.peekLast() >= val){
            minStack.offer(val);
        }

    }

    public void pop() {
        Integer integer = stack.pollLast();
        if(minStack.peekLast().intValue() == integer){
            minStack.removeLast();
        }
    }

    public int top() {
        Integer integer = stack.peekLast();
        return integer;
    }

    public int getMin() {
        return minStack.peekLast();
    }


    public static void main(String[] args) {
        A0155MinStack minStack = new A0155MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin();
        minStack.pop();
        minStack.top();
        minStack.getMin();

    }
}
