package cn.xy.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author XiangYu
 * @create2020-11-04-15:28 如果字符串满足一下条件之一，则可以称之为 有效括号字符串（valid parentheses string，可以简写为 VPS）：
 * 字符串是一个空字符串 ""，或者是一个不为 "(" 或 ")" 的单字符。
 * 字符串可以写为 AB（A 与 B 字符串连接），其中 A 和 B 都是 有效括号字符串 。
 * 字符串可以写为 (A)，其中 A 是一个 有效括号字符串 。
 * 类似地，可以定义任何有效括号字符串 S 的 嵌套深度 depth(S)：
 *
 * depth("") = 0
 * depth(C) = 0，其中 C 是单个字符的字符串，且该字符不是 "(" 或者 ")"
 * depth(A + B) = max(depth(A), depth(B))，其中 A 和 B 都是 有效括号字符串
 * depth("(" + A + ")") = 1 + depth(A)，其中 A 是一个 有效括号字符串
 * 例如：""、"()()"、"()(()())" 都是 有效括号字符串（嵌套深度分别为 0、1、2），而 ")(" 、"(()" 都不是 有效括号字符串 。
 *
 * 给你一个 有效括号字符串 s，返回该字符串的 s 嵌套深度 。
 *
 * 示例 1：
 *
 * 输入：s = "(1+(2*3)+((8)/4))+1"
 * 输出：3
 * 解释：数字 8 在嵌套的 3 层括号中。
 *
 * 1 <= s.length <= 100
 *
 * 题目数据保证括号表达式 s 是 有效的括号表达式
 */
public class A1614MaximumNestingDepthOfTheParentheses {

    public static void main(String[] args) {
        String s = "(1+(2*3)+((8)/4))+1";
        System.out.println(maxDepth(s));
    }

    //速度最快
    public static int maxDepth(String s) {
        char[] chars = s.toCharArray();
        int maxDepth = 0;
        int tempDepth = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                tempDepth += 1;
            }else if (chars[i] == ')') {
                maxDepth = Math.max(maxDepth,tempDepth);
                tempDepth -= 1;
            }


        }
        return maxDepth;
    }

    //空间较少
    public int maxDepth6(String s) {
        int md = 0;
        int count = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                count ++;
            }
            if(s.charAt(i) == ')') {
                if(count > md) {
                    md = count;
                }
                count --;
            }
        }
        return md;
    }

    //栈的写法
    public static int maxDepth3(String s) {
        Deque<Character> stack = new LinkedList<>();
        int max = 0;
        char[] arr = s.toCharArray();
        for (char c : arr) {
            if (c == '(') {
                stack.offer(c);
            } else if (c == ')') {
                max = Math.max(max, stack.size());
                stack.pollLast();
            }
        }
        return max;
    }


}
