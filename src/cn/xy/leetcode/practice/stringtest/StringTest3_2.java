package cn.xy.leetcode.practice.stringtest;

/**
 * @author XiangYu
 * @create2020-12-02-14:18 最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 */
public class StringTest3_2 {


    public static void main(String[] args) {
        String s = "aacabdkacaa";


        System.out.println(longestPalindrome(s));
    }

    /**
     * 执行用时：
     * 185 ms, 在所有 Java 提交中击败了34.23%的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了76.54%的用户
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }

        if (s.length() == 1) {
            return s;
        }
        //滑块

        //数组的长度，回文数的长度
        int length = s.length();
        char[] chars = s.toCharArray();

        for (int j = length; j > 0; j--) {
            int i = 0;
            while (i + length - 1 < s.length()) {
                int star = i;
                int end = i + length - 1;
                //校验长度
                int tempLength = (end - star + 1) / 2;
                boolean flag = true;
                for (int k = 0; k < tempLength; k++) {
                    if (chars[star] == chars[end]) {
                        star++;
                        end--;
                    } else {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    return s.substring(i, i + length);
                } else {
                    i++;
                }
            }
            //未匹配到，长度减1
            length--;
        }


        return s.substring(0, 1);
    }

}
