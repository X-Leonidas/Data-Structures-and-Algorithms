package cn.xy.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XiangYu
 * @create2020-12-28-14:39
 *
 *
 *  同构字符串
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 *
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 *
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 *
 * 示例 1:
 *
 * 输入: s = "egg", t = "add"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "foo", t = "bar"
 * 输出: false
 * 示例 3:
 *
 * 输入: s = "paper", t = "title"
 * 输出: true
 * 说明:
 * 你可以假设 s 和 t 具有相同的长度。
 */
public class A0205IsomorphicStrings {

    /**
     * 执行用时：3 ms, 在所有 Java 提交中击败了98.46%的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了98.66%的用户
     *
     * 创建两个字符表，判断两个字符串对应字符的出现次数是否相等。
     *
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {
        char[] chars = s.toCharArray();
        char[] chart = t.toCharArray();
        int[] preIndexOfs = new int[256];
        int[] preIndexOft = new int[256];
        for (int i = 0; i < chars.length; i++) {
            if (preIndexOfs[chars[i]] != preIndexOft[chart[i]]) {
                return false;
            }
            preIndexOfs[chars[i]] = i + 1;
            preIndexOft[chart[i]] = i + 1;
        }
        return true;
    }


     public boolean isIsomorphic2(String s, String t){
         Map<Character, Character> sMap = new HashMap<Character, Character>();
         Map<Character, Character> tMap = new HashMap<Character, Character>();
         int len = s.length();
         for (int i = 0; i < len; ++i) {
             char sChar = s.charAt(i);
             char tChar = t.charAt(i);
             if ((sMap.containsKey(sChar) && sMap.get(sChar) != tChar) || (tMap.containsKey(tChar) && tMap.get(tChar) != sChar)) {
                 return false;
             }
             sMap.put(sChar, tChar);
             tMap.put(tChar, sChar);
         }
         return true;




     }

}
