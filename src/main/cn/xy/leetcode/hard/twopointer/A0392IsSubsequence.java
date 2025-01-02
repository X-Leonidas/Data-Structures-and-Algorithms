package cn.xy.leetcode.hard.twopointer;

/**
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 * <p>
 * 进阶：
 * 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 * <p>
 * 示例 1：
 * 输入：s = "abc", t = "ahbgdc"
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：s = "axc", t = "ahbgdc"
 * 输出：false
 * <p>
 * 提示：
 * 0 <= s.length <= 100
 * 0 <= t.length <= 10^4
 * 两个字符串都只由小写字符组成。
 *
 * @author xiangyu
 * @date 2024-12-29 16:33
 */
public class A0392IsSubsequence {

    public static void main(String[] args) {
        new A0392IsSubsequence().isSubsequence("abc","ahbgdc");
    }

    public boolean isSubsequence(String s, String t) {
        int fPoint = 0;
        int sPoint = 0;

        if(s.isEmpty()){
            return true;
        }

        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();

        while (sPoint < t.length() && fPoint < s.length()) {
            if (sArray[fPoint] == tArray[sPoint]) {
                sPoint++;
                fPoint++;
            } else {
                sPoint++;
            }
        }

        return fPoint == s.length();
    }
}
