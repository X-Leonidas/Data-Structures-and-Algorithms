package cn.xy.leetcode.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author XiangYu
 * @create2020-12-16-9:56 给定一种规律 pattern和一个字符串str，判断 str 是否遵循相同的规律。
 * <p>
 * 这里的遵循指完全匹配，例如，pattern里的每个字母和字符串str中的每个非空单词之间存在着双向连接的对应规律。
 * <p>
 * 示例1:
 * <p>
 * 输入: pattern = "abba", str = "dog cat cat dog"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:pattern = "abba", str = "dog cat cat fish"
 * 输出: false
 * 示例 3:
 * <p>
 * 输入: pattern = "aaaa", str = "dog cat cat dog"
 * 输出: false
 * 示例4:
 * <p>
 * 输入: pattern = "abba", str = "dog dog dog dog"
 * 输出: false
 * 说明:
 * 你可以假设pattern只包含小写字母，str包含了由单个空格分隔的小写字母。 
 */
public class A0290WordPattern {
    public boolean wordPattern(String pattern, String s) {

        HashSet<Integer> integers = new HashSet<>();
        int len = pattern.length();
        String[] strs = s.split(" ");
        char[] patt = pattern.toCharArray();
        if (len != strs.length) {
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < len; i++) {
            char x = patt[i];
            String str = strs[i];
            //如果map中存在pattern中一个字符的key,校验是否有对应的value
            if (map.containsKey(x)) {
                if (!map.get(x).equals(str)) {
                    return false;
                }
            } else {
                //如果不存在，则将该值放入map中
                map.put(x, str);
                //如果value值已经出现过，则不通过
                if (set.contains(str)) {
                    return false;
                } else {
                    set.add(str);
                }
            }
        }
        return true;

    }
}
