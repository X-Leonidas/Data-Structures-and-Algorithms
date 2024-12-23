package cn.xy.leetcode.easy.string;

/**
 * @author xiangyu
 * @date 2024-12-18 22:09
 * <p>
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1：
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * <p>
 * 示例 2：
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 * <p>
 * 提示：
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] 仅由小写英文字母组成
 */
public class A0014LongestCommonPrefix {

    public static void main(String[] args) {
        new A0014LongestCommonPrefix().longestCommonPrefix(new String[]{"acc", "ac", "abb"});
    }

    public String longestCommonPrefix(String[] strs) {
        String result = strs[0];
        for (int i = 1; i < strs.length; i++) {
            String str = strs[i];
            int length = Math.min(result.length(), str.length());
            result = result.substring(0, length);
            for (int j = 0; j < length; j++) {
                if (result.charAt(j) != str.charAt(j)) {
                    if (j == 0) {
                        return "";
                    }
                    result = str.substring(0, j);
                    break;
                }
            }
        }

        return result;
    }
}
