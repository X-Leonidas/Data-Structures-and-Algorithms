package cn.xy.leetcode.middle.backtracking;

import java.util.*;

/**
 * @author xiangyu
 * @date 2024-03-17 22:55
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 示例 1：
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * <p>
 * 示例 2：
 * 输入：n = 1
 * 输出：["()"]
 * <p>
 * 提示：
 * 1 <= n <= 8
 */
public class A0022GenerateParentheses {
    public static void main(String[] args) {
        A0022GenerateParentheses a0022GenerateParentheses = new A0022GenerateParentheses();
        System.out.println(a0022GenerateParentheses.generateParenthesis(3));
    }

    public List<String> generateParenthesis(int n) {
        StringBuilder output = new StringBuilder();
        List<String> ans = new ArrayList<>();
        backtrack(ans, output, 0, 0, n);
        return ans;
    }

    private void backtrack(List<String> ans, StringBuilder output, int left, int right, int n) {
        if (left == n && right == n) {
            ans.add(output.toString());
            return;
        }

        if (left < right) {
            return;
        }

        if (left < n) {
            output.append("(");
            backtrack(ans, output, left + 1, right, n);
            output.delete(output.length() - 1, output.length());
        }
        if (right < n) {
            output.append(")");
            backtrack(ans, output, left, right + 1, n);
            output.delete(output.length() - 1, output.length());
        }
    }
}
