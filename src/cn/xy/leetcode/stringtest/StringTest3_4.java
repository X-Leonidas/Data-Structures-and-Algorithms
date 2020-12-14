package cn.xy.leetcode.stringtest;

/**
 * @author XiangYu
 * @create2020-12-04-9:53
 *实现 strStr() 函数。
 *
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 *
 * 示例 1:
 *
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 *
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 说明:
 *
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 *
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 *
 */
public class StringTest3_4 {


    public static void main(String[] args) {
        System.out.println(strStr("a", "a"));

    }


    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了50.37%的用户
     * 内存消耗：37.3 MB, 在所有 Java 提交中击败了66.92%的用户
     *
     *
     *
     * 全部改为数组后，时间上升到了70%，空间下降到了40%
     * @param haystack
     * @param needle
     * @return
     */
    public static int strStr(String haystack, String needle) {



        if(needle == null || needle.length() == 0 ){
            return  0;
        }
        if(haystack.length() == 0 || needle.length() > haystack.length()){
            return  -1;
        }


        int length = needle.length();

        char[] chars = haystack.toCharArray();

        char[] chars1 = needle.toCharArray();

        for (int i = 0; i + length <= chars.length;  i++) {

            //匹配开头
            if(chars[i] == needle.charAt(0)){
                boolean flag = true;
                for (int j = i; j < length + i; j++) {
                    if (chars[j] != chars1[j-i]) {
                        flag = false;
                        break;
                    }
                }
                while (flag){
                    return  i;
                }
            }
        }

        return  -1;
    }

}
