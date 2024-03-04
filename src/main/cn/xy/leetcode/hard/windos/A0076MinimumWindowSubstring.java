package cn.xy.leetcode.hard.windos;

import java.util.HashMap;

/**
 * @author xiangyu
 * @date 2024-03-03 19:43
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * <p>
 * 注意：
 * <p>
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
 * 示例 2：
 * <p>
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 解释：整个字符串 s 是最小覆盖子串。
 * 示例 3:
 * <p>
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 * <p>
 * 提示：
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 105
 * s 和 t 由英文字母组成
 * <p>
 * 你能设计一个在 o(m+n) 时间内解决此问题的算法吗？
 */
public class A0076MinimumWindowSubstring {
    public static void main(String[] args) {
        System.out.println(new A0076MinimumWindowSubstring().minWindow("ADOBECODEBANC", "ABC"));
    }

    public String minWindow(String s, String t) {
        int minLen = Integer.MAX_VALUE;
        String result = "";
        int tLen = t.length();
        HashMap<Character, Integer> cache = new HashMap<>(t.length());
        for (int i = 0; i < t.length(); i++) {
            if (cache.containsKey(t.charAt(i))) {
                cache.put(t.charAt(i), cache.get(t.charAt(i)) + 1);
            } else {
                cache.put(t.charAt(i), 1);
            }
        }
        int start = 0;
        char[] charArray = s.toCharArray();
        for (int end = start; end < charArray.length; end++) {
            if (cache.containsKey(charArray[end])) {
                if (cache.get(charArray[end]) > 0) {
                    tLen--;
                }
                cache.put(charArray[end], cache.get(charArray[end]) - 1);
            }
            // 找到一个后
            if (tLen == 0) {
                while (start < s.length() && (!cache.containsKey(charArray[start]) || cache.get(charArray[start]) < 0)) {
                    if (cache.containsKey(charArray[start])) {
                        cache.put(charArray[start], cache.get(charArray[start]) + 1);
                    }
                    start++;
                }
                if (minLen > (end - start + 1)) {
                    result = s.substring(start, end + 1);
                    minLen = end - start + 1;
                }
                cache.put(charArray[start], cache.get(charArray[start]) + 1);
                tLen++;
                start++;
            }
        }

        return result;
    }

    public String minWindow2(String s, String t) {
        int n = s.length(), tot = 0;
        //c1、c2分别用于存储t中字符的次数和窗口内字符的次数，60是足够容纳所有字母种类
        int[] c1 = new int[60], c2 = new int[60];
        //遍历t中的每个字符，保存每个字符的次数
        for (char x : t.toCharArray()) {
            //tot表示字符的种类数
            if (++c1[getIdx(x)] == 1) {
                tot++;
            }
        }
        //初始化答案
        String ans = "";
        //j是窗口左边界，i是窗口右边界
        for (int i = 0, j = 0; i < n; i++) {
            //得到窗口右边界字符对应的数字下标
            int idx1 = getIdx(s.charAt(i));
            //每次遍历，就将当前窗口字符的数量保存下来，如果和t字符串的字符数量相等，tot-1表示剩余需要满足要求的字符种类
            if (++c2[idx1] == c1[idx1]) {
                tot--;
            }
            while (j < i) {
                //拿到窗口左边界的字符对应数字下标
                int idx2 = getIdx(s.charAt(j));
                //不断缩短窗口左边界
                if (c2[idx2] > c1[idx2] && --c2[idx2] >= 0) {
                    j++;
                } else {
                    break;
                }
            }
            //符合要求，保存答案
            //ans.length()（之前符合的窗口大小） > i - j + 1（当前符合窗口大小）
            //如果现在的窗口比之前还大，那没必要更新，因为是取最小的区间
            if (tot == 0 && (ans.isEmpty() || ans.length() > i - j + 1)) ans = s.substring(j, i + 1);
        }
        return ans;
    }
    //设定好字符对应的数字，作为数组的下标
    int getIdx(char x) {
        return x >= 'A' && x <= 'Z' ? x - 'A' + 26 : x - 'a';
    }
}
