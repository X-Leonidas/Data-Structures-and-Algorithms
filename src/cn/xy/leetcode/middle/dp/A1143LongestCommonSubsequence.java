package cn.xy.leetcode.middle.dp;

/**
 * @author XiangYu
 * @create2021-04-03-11:57
 *
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 *
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 *
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 * 示例 1：
 *
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
 * 示例 2：
 *
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
 * 示例 3：
 *
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0 。
 *  
 *
 * 提示：
 *
 * 1 <= text1.length, text2.length <= 1000
 * text1 和 text2 仅由小写英文字符组成。
 */
public class A1143LongestCommonSubsequence {


    public int longestCommonSubsequence(String text1, String text2) {
        int n1 = text1.length(), n2 = text2.length();
        char[] cs1 = text1.toCharArray();
        char[] cs2 = text2.toCharArray();
        int[][] dp = new int[n1 + 1][n2 + 1];
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (cs1[i - 1] == cs2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n1][n2];
    }


    /**
     *
     * @param s1
     * @param s2
     * @return 输出公共的子序列
     */
    public String LCS (String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();
        char[] cs1 = s1.toCharArray();
        char[] cs2 = s2.toCharArray();
        int[][] dp = new int[n1 + 1][n2 + 1];
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (cs1[i - 1] == cs2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        //找出一个最长的公共子序列
        StringBuilder sb = new StringBuilder();
        int s1L = n1;
        int s2L = n2;
        while(s1L > 0 && s2L > 0){
            if (cs1[s1L-1] == cs2[s2L-1]){
                sb.append(cs1[s1L - 1]);
                s1L--;
                s2L--;
            }else{
                if (dp[s1L-1][s2L] > dp[s1L][s2L-1]){
                    s1L--;
                }else{
                    s2L--;
                }
            }
        }
        if(sb.length() == 0){
            return "-1";
        }

        return sb.reverse().toString();
    }
}
