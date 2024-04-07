package cn.xy.leetcode.easy.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author XiangYu
 * @create 2021-04-02-16:39
 * <p>
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * <p>
 * 示例 1：
 * 输入：s = "()"
 * 输出：true
 * <p>
 * 示例2：
 * 输入：s = "()[]{}"
 * 输出：true
 * <p>
 * 示例3：
 * 输入：s = "(]"
 * 输出：false
 * <p>
 * 示例4：
 * 输入：s = "([)]"
 * 输出：false
 * <p>
 * 示例5：
 * 输入：s = "{[]}"
 * 输出：true
 */
public class A0020ValidParentheses {
    public boolean isValid(String s) {
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<Character>();
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if (aChar == '{' || aChar == '[' || aChar == '(') {
                stack.push(aChar);
            } else {

                if (stack.isEmpty()) {
                    return false;
                }
                if (aChar == ')' && '(' != stack.peek()) {
                    return false;
                } else if (aChar == '}' && '{' != stack.peek()) {
                    return false;
                } else if (aChar == ']' && '[' != stack.peek()) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }


    //优化代码
    public boolean isValid2(String s) {
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }

        Map<Character, Character> pairs = new HashMap<>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};

        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if (pairs.containsKey(aChar)) {
                if (stack.isEmpty()) {
                    return false;
                }
                if (!(pairs.get(aChar).equals(stack.peek()))) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(aChar);
            }

        }
        return stack.isEmpty();
    }
}
