package cn.xy.leetcode.string;

import java.util.Arrays;

/**
 * @author XiangYu
 * @create2020-12-02-9:45 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 * <p>
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 * <p>
 * 所有输入只包含小写字母 a-z 。
 */
public class StringTest3_1 {

    public static void main(String[] args) {


        String[] strings = {"asd","asdc"};
        System.out.println(longestCommonPrefix2(strings));
    }


    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了31.91%的用户
     * 内存消耗：36.3 MB, 在所有 Java 提交中击败了94.47%
     * 的用户
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        StringBuilder sb = new StringBuilder("");

        if (strs.length == 0) {
            return sb.toString();
        }

        if(strs.length == 1){
            return  strs[0];
        }

        boolean flag = true;
        int publicStr = 0;
        while (flag) {

            for (int i = 0; i < strs.length - 1; i++) {
                //长度为0
                if(strs[i].length() == 0){
                    flag = false;
                    break;
                }

                //终止条件1:公共字符串长度等于最小长度字符串
                if(publicStr == strs[i].length() || publicStr == strs[i+1].length()){
                    flag = false;
                    break;
                }

                //终止条件2：没有公共字符串
                if (strs[i].charAt(publicStr) != strs[i + 1].charAt(publicStr)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                sb.append(strs[0].charAt(publicStr));
                publicStr++;
            }
        }

        return sb.toString();
    }


    /**
     * 大佬写法，给跪了 orz
     * @param strs
     * @return
     */
    public static String longestCommonPrefix2(String[] strs) {
        String a = "";
        try {
            a = strs[0];
            for (String str : strs) {
                // 若a已经减为""，则说明无公共前缀，直接返回
                if (a == "") {
                    return a;
                }
                // 若a在当前str中匹配不上，则减少字符串a的长度，再次尝试匹配
                while (!str.startsWith(a)) {
                    a = a.substring(0,a.length() - 1);
                }
            }
            return a;
        } catch (Exception e) {
            return a;
        }

    }
}
