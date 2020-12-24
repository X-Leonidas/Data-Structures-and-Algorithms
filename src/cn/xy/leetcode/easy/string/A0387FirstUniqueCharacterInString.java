package cn.xy.leetcode.easy.string;

import java.util.HashMap;

/**
 * @author XiangYu
 * @create2020-12-23-13:23
 *  字符串中的第一个唯一字符
 *
 *  给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 *
 *  
 *
 * 示例：
 *
 * s = "leetcode"
 * 返回 0
 *
 * s = "loveleetcode"
 * 返回 2
 *  
 *
 * 提示：你可以假定该字符串只包含小写字母。
 *
 *
 */
public class A0387FirstUniqueCharacterInString {
    /**
     * 执行用时：54 ms, 在所有 Java 提交中击败了5.64%的用户
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了54.71%的用户
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if(!map.containsKey(chars[i])){
                map.put(chars[i],1);
            }else {
                Integer integer = map.get(chars[i]);
                map.put(chars[i],++integer);
            }
        }

        for (int i = 0; i < chars.length; i++) {
            if(map.get(chars[i]).intValue() == 1){
                return i;
            }
        }
        return  -1;
    }


    /**
     * 执行用时：4 ms, 在所有 Java 提交中击败了97.02%的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了85.20%的用户
     * @param s
     * @return
     */
    public int firstUniqChar2(String s) {
        char[] letters = new char[26];
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
           letters[chars[i] - 'a']++;
        }

        for (int i = 0; i < chars.length; i++) {
            if(letters[chars[i] - 'a']  == 1){
                return  i;
            }
        }
        return  -1;
    }
}
