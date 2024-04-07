package cn.xy.leetcode.middle.dp;

import java.util.Arrays;

/**
 * @author xiangyu
 * @date 2024-03-26 23:08
 * 条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
 * "AAJF" ，将消息分组为 (1 1 10 6)
 * "KJF" ，将消息分组为 (11 10 6)
 * 注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
 * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
 * 题目数据保证答案肯定是一个 32 位 的整数。
 * <p>
 * 示例 1：
 * 输入：s = "12"
 * 输出：2
 * 解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
 * <p>
 * 示例 2：
 * 输入：s = "226"
 * 输出：3
 * 解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 * <p>
 * 示例 3：
 * 输入：s = "06"
 * 输出：0
 * 解释："06" 无法映射到 "F" ，因为存在前导零（"6" 和 "06" 并不等价）。
 * <p>
 * 提示：
 * 1 <= s.length <= 100
 * s 只包含数字，并且可能包含前导零。
 */
public class A0091DecodeWays {
    public int numDecodings(String s) {
        // 左神例题
        int[] dp = new int[s.length()];
        Arrays.fill(dp, -1);
        return dp(s.toCharArray(), 0, dp);
    }


    private int dp(char[] charArray, int i, int[] dp) {
        if (i == charArray.length) {
            return 1;
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        int ans;
        if (charArray[i] == '0') {
            return 0;
        } else {
            ans = dp(charArray, i + 1, dp);
            if (i + 1 < charArray.length && ((charArray[i] - '0') * 10 + charArray[i + 1] - '0') <= 26) {
                ans = ans + dp(charArray, i + 2, dp);
            }
        }
        dp[i] = ans;
        return ans;
    }
}
