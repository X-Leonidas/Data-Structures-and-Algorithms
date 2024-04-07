package cn.xy.leetcode.middle.string;

/**
 * @author xiangyu
 * @date 2024-04-01 21:45
 * 给你一个字符串 s，找到 s 中最长的回文子串
 * 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
 * 示例 1：
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * <p>
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出："bb"
 * <p>
 * 提示：
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母组成
 */
public class A0005LongestPalindromicSubstring {

    public static void main(String[] args) {
        A0005LongestPalindromicSubstring a0005LongestPalindromicSubstring = new A0005LongestPalindromicSubstring();
        a0005LongestPalindromicSubstring.longestPalindrome("babadada");
    }

    /**
     * manacher
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        char[] charArray = s.toCharArray();
        charArray = manacherHelp(charArray);
        int[] pArr = new int[charArray.length];

        int c = 0;
        // 最大右边界
        int pR = 0;

        int len = 0;
        int max = Integer.MIN_VALUE;
        int ansI = 0;
        for (int i = 0; i < charArray.length; i++) {
            len = pR > i ? Math.min(pArr[2 * c - i], pR - i) : 1;
            // 没有扩展到边界，并且还是回文
            while (i + len < charArray.length && i - len >= 0
                    && charArray[i + len] == charArray[i - len]) {
                len++;
            }

            if (i + len > pR) {
                c = i;
                pR = i + len;
            }
            if (max < len) {
                max = len;
                ansI = i;
            }
            pArr[i] = len;
        }
        // 从ansC 开始，左右各扩展len个
        StringBuilder ans = new StringBuilder();
        for (int i = ansI - (max-1); i <= ansI + (max-1); i++) {
            if (charArray[i] != '*') {
                ans.append(charArray[i]);
            }
        }
        return ans.toString();
    }


    private char[] manacherHelp(char[] chars) {
        char[] result = new char[chars.length * 2 + 1];
        int index = 0;
        for (int i = 0; i < result.length; i++) {
            if ((i & 1) == 1) {
                result[i] = chars[index++];
            } else {
                result[i] = '*';
            }
        }
        return result;
    }


}
