package cn.xy.leetcode.hard.dp;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author xiangyu
 * @date 2025-04-08 23:23
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 * <p>
 * 示例 1：
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 * <p>
 * 示例 2：
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 * <p>
 * 示例 3：
 * 输入：s = ""
 * 输出：0
 * <p>
 * 提示：
 * 0 <= s.length <= 3 * 104
 * s[i] 为 '(' 或 ')'
 */
public class A0032LongestValidParentheses {

    public static void main(String[] args) {
        new A0032LongestValidParentheses().longestValidParentheses1("()(()()");
    }

    /**
     * 自己的写法
     * @param s
     * @return
     */
    public int longestValidParentheses1(String s) {
        Deque<Integer> stack = new LinkedList<>();
        int[] dp = new int[s.length()];
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '('){
                stack.addLast(i);
            } else if (!stack.isEmpty() && s.charAt(i) == ')') {

                int leftIndex = stack.removeLast();
                // 当前的长度
                int length = i - leftIndex + 1;
                // 解决 ()(()) 这种情况
                if(leftIndex - 1 >= 0){
                    length += dp[leftIndex - 1];
                }
                dp[i] = length;
                maxLen = Math.max(maxLen, length);
            }
        }
        return maxLen;
    }

    /**
     * 官方写法
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int maxans = 0;
        int[] dp = new int[s.length()];

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                // 第一种情况 。。。。。()
                if (s.charAt(i - 1) == '(') {
                    if(i >= 2){
                        dp[i] = dp[i-2] + 2;
                    }else{
                        dp[i] = 2;
                    }
                    //为了防止数组越界 i - dp[i - 1] > 0
                    // s.charAt(i - dp[i - 1] - 1) == '('
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    // 第二种情况 。。。。。((())
                    dp[i] = dp[i - 1] + 2;
                    // i - dp[i-1] - 1 的前一个的最大长度
                    if(i - dp[i - 1] >= 2){
                        dp[i]  = dp[i] + dp[i - dp[i - 1] - 2];
                    }
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }
}
