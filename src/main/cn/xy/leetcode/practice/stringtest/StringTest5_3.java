package cn.xy.leetcode.practice.stringtest;

/**
 * @author XiangYu
 * @create2020-12-09-16:30
 * 反转字符串中的单词 III
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 *
 *  
 *
 * 示例：
 *
 * 输入："Let's take LeetCode contest"
 * 输出："s'teL ekat edoCteeL tsetnoc"
 *  
 *
 * 提示：
 *
 * 在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格
 *
 */

public class StringTest5_3 {

    public static void main(String[] args) {
        String s = "Let's take LeetCode contest";
        System.out.println(reverseWords_1(s));

    }

    /**
     * 执行用时：9 ms, 在所有 Java 提交中击败了47.36%的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了94.03%的用户
     * @param s
     * @return
     */
    public static String reverseWords(String s) {

        StringBuilder sb= new StringBuilder();
        char[] chars = s.toCharArray();

        int start  = 0;

        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == ' '){
                //倒叙一个单词
                for (int j = i-1; j >= start ; j--) {
                    sb.append(chars[j]);
                }
                //重置start
                start = i + 1;
                //添加空格
                sb.append(' ');
                continue;
            }
            //处理末尾
            if(i == chars.length -1){
                for (int j = chars.length -1; j >= start; j--) {
                    sb.append(chars[j]);
                }
            }
        }
        return sb.toString();
    }





    /**
     * 第一种解法优化在原数组上进行操作
     *
     * 执行用时：3 ms, 在所有 Java 提交中击败了99.96%的用户
     * 内存消耗：39.2 MB, 在所有 Java 提交中击败了81.83%的用户
     *
     * @param s
     * @return
     */
    public static String reverseWords_1(String s) {
        char[] chars = s.toCharArray();
        int start  = 0;
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == ' '){
                //倒叙一个单词
                reverse(chars, start, i-1);
                //重置start
                start = i + 1;
                //添加空格
                continue;
            }
            //处理末尾
            if(i == chars.length -1){
                reverse(chars, start, i);
            }
        }

        return  String.valueOf(chars);
    }


    /**
     *
     * @param s
     * @return
     */
    public static  String reverseWords2(String s) {
        char[] sentence = s.toCharArray();
        int i = 0, j = 0;
        while(j < sentence.length) {
            while(i < sentence.length && sentence[i] == ' ') {
                ++i;
            }
            while(j < sentence.length && sentence[j] != ' ') {
                ++j;
            }
            reverse(sentence, i, j - 1);
            i = j;
            ++j;
        }
        s = String.valueOf(sentence);
        return s;
    }

    private static void reverse(char[] arr, int start, int end) {
        if (arr.length == 0){
            return;
        }
        while (start < end) {
            char temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            ++start;
            --end;
        }
    }
}
