package cn.xy.leetcode.middle.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author XiangYu
 * @create2021-04-24-18:12 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * 示例 4:
 * <p>
 * 输入: s = ""
 * 输出: 0
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 */
public class A0003LongestSubstringWithoutRepeatingCharacters {


    public static int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int length = s.length();
        // 右指针，初始值为 -1
        int right = -1, ans = 0;
        for (int i = 0; i < length; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                set.remove(s.charAt(i - 1));
            }
            //右指针没到结尾，并且子串中不包含当前字符
            while (right + 1 < length && !set.contains(s.charAt(right + 1))) {
                // 不断地移动右指针
                set.add(s.charAt(right + 1));
                ++right;
            }
            // 第 i 到 right 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, right - i + 1);
        }
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring2("abba"));
    }

    public static int lengthOfLongestSubstring2(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(64);
        int start = 0;
        for (int end = 0; end < n; end++) {
            char alpha = s.charAt(end);
            // 如果存在，则拿到第一次出现这个字符的位置 + 1做为开始
            if (map.containsKey(alpha)) {
                // 解决abba的问题，start只能越来越大，不能越来越小
                start = Math.max(map.get(alpha) + 1, start);
            }
            map.put(s.charAt(end), end);
            ans = Math.max(ans, end - start + 1);

        }
        return ans;
    }

    /**
     * 数组实现
     */
    public int maxLength(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        int lenth = arr.length;
        int res = 0;
        int right = -1;
        for (int i = 0; i < lenth; i++) {
            if (i != 0) {
                set.remove(arr[i - 1]);
            }
            while (right < lenth - 1 && !set.contains(arr[right + 1])) {
                set.add(arr[right + 1]);
                right++;

            }
            res = Math.max(res, right - i + 1);
        }

        return res;
    }
}
