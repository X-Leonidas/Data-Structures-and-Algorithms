package cn.xy.leetcode.stringtest;

/**
 * @author XiangYu
 * @create2020-12-02-16:02 给定一个字符串，逐个翻转字符串中的每个单词。
 * <p>
 * 说明：
 * <p>
 * 无空格字符构成一个 单词 。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入："the sky is blue"
 * 输出："blue is sky the"
 * 示例 2：
 * <p>
 * 输入："  hello world!  "
 * 输出："world! hello"
 * 解释：输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 示例 3：
 * <p>
 * 输入："a good   example"
 * 输出："example good a"
 * 解释：如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * 示例 4：
 * <p>
 * 输入：s = "  Bob    Loves  Alice   "
 * 输出："Alice Loves Bob"
 * 示例 5：
 * <p>
 * 输入：s = "Alice does not even like bob"
 * 输出："bob like even not does Alice"
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 104
 * s 包含英文大小写字母、数字和空格 ' '
 * s 中 至少存在一个 单词
 *  
 * <p>
 * 进阶：
 * <p>
 * 请尝试使用 O(1) 额外空间复杂度的原地解法。
 */
public class StringTest3_3 {


    public static void main(String[] args) {
        String s = " asdasd df f";

        System.out.println(reverseWords(s));
    }


    /**
     * 执行用时：3 ms, 在所有 Java 提交中击败了86.72%的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了77.75%的用户
     *
     * @param s
     * @return
     */
    public static String reverseWords(String s) {


        return  null;
    }
    /**
     * 执行用时：3 ms, 在所有 Java 提交中击败了86.72%的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了77.75%的用户
     *
     * @param s
     * @return
     */
    public static String reverseWords2(String s) {
        char[] chars = s.toCharArray();
        StringBuffer sb = new StringBuffer();
        //标志，1开始，2结束
        char flag = 0;
        int start = 0;
        int end = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (flag == 0) {
                if (chars[i] != ' ') {
                    flag = 1;
                    end = i;
                }
            }
            if (flag == 1) {

                if (chars[i] == ' ' || i == 0) {
                    flag = 2;
                    start = i;
                }
            }

            if (flag == 2) {
                flag = 0;
                if (start != 0 || chars[i] == ' ') {
                    start++;
                }
                //翻转字符串
                for (int j = start; j <= end; j++) {
                    sb.append(chars[j]);
                }
                sb.append(" ");
            }

        }
        return sb.substring(0, sb.length() - 1);
    }
}
