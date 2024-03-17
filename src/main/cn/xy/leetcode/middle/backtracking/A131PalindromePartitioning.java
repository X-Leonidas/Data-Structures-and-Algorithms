package cn.xy.leetcode.middle.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiangyu
 * @date 2024-03-18 0:15
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文串
 * 返回 s 所有可能的分割方案。
 * <p>
 * 示例 1：
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * 示例 2：
 * <p>
 * 输入：s = "a"
 * 输出：[["a"]]
 * <p>
 * 提示：
 * 1 <= s.length <= 16
 * s 仅由小写英文字母组成
 */
public class A131PalindromePartitioning {
    /**
     * 最直接的回溯
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> path = new ArrayList<>();
        dfs(res, 0, s, path);
        return res;
    }

    public boolean isPalindrome(int left, int right, String s) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    public void dfs(List<List<String>> res, int i, String s, List<String> path) {
        if (i == s.length()) {
            res.add(new ArrayList<>(path));
        }
        for (int j = i; j < s.length(); j++) {
            // 如果i到j 是回文， 判断下一段回文，这样可以拿到所有的子回文串
            if (isPalindrome(i, j, s)) {
                path.add(s.substring(i, j + 1));
                dfs(res, j + 1, s, path);
                path.remove(path.size() - 1);
            }
        }
    }
}
