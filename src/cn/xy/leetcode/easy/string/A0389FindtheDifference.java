package cn.xy.leetcode.easy.string;

import java.util.HashMap;

/**
 * @author XiangYu
 * @create2020-12-18-9:48 找不同
 * 给定两个字符串 s 和 t，它们只包含小写字母。
 * <p>
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 * <p>
 * 请找出在 t 中被添加的字母。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "abcd", t = "abcde"
 * 输出："e"
 * 解释：'e' 是那个被添加的字母。
 * 示例 2：
 * <p>
 * 输入：s = "", t = "y"
 * 输出："y"
 * 示例 3：
 * <p>
 * 输入：s = "a", t = "aa"
 * 输出："a"
 * 示例 4：
 * <p>
 * 输入：s = "ae", t = "aea"
 * 输出："a"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= s.length <= 1000
 * t.length == s.length + 1
 * s 和 t 只包含小写字母
 */
public class A0389FindtheDifference {
    public static void main(String[] args) {

        char theDifference = findTheDifference("adbd", "adbde");
        System.out.println(theDifference);
    }

    /**
     * 哈希
     * @param s
     * @param t
     * @return
     */
    public static char findTheDifference(String s, String t) {

        int l = s.length();

        HashMap<Character, Integer> map = new HashMap<>(l);


        for (int i = 0; i < l; i++) {
            char c = s.charAt(i);

            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {

                Integer integer = map.get(c);
                map.put(c, ++integer);
            }
        }

        int sl = t.length();
        for (int i = 0; i < sl; i++) {
            char c = t.charAt(i);

            if (map.containsKey(c)) {
                Integer integer = map.get(c);
                if (integer != 0) {
                    map.put(c, --integer);
                } else {
                    return c;
                }
            } else {
                return c;
            }

        }

        return '0';
    }


    public char findTheDifference2(String s, String t) {
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            t = t.replaceFirst(String.valueOf(aChar),"");
        }
        return t.charAt(0);
    }






    /**
     * 异或
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference3(String s, String t) {
        int ret = 0;
        for (int i = 0; i < s.length(); ++i) {
            ret ^= s.charAt(i);
        }
        for (int i = 0; i < t.length(); ++i) {
            ret ^= t.charAt(i);
        }
        return (char) ret;
    }


    /**
     *ASCII 码的值求和
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference4(String s, String t) {
        int as = 0, at = 0;
        for (int i = 0; i < s.length(); ++i) {
            as += s.charAt(i);
        }
        for (int i = 0; i < t.length(); ++i) {
            at += t.charAt(i);
        }
        return (char) (at - as);
    }


}
