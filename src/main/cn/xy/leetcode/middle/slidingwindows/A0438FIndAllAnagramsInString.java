package cn.xy.leetcode.middle.slidingwindows;

import java.util.*;

/**
 * @author xiangyu
 * @date 2024-01-31 0:14
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * <p>
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 * 示例 2:
 * <p>
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= s.length, p.length <= 3 * 104
 * s 和 p 仅包含小写字母
 */
public class A0438FIndAllAnagramsInString {


    public static void main(String[] args) {
        System.out.println(findAnagrams("abaacbabc", "abc"));
    }

    /**
     * Your runtime beats 79.16 % of java submissions
     * Your memory usage beats 75.55 % of java submissions (43.9 MB)
     * @param s
     * @param p
     * @return
     */
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (p == null || s == null || p.length() > s.length()) {
            return result;
        }
        int sLen = s.length();
        int pLen = p.length();

        int[] sCount = new int[26];
        int[] pCount = new int[26];
        for (int i = 0; i < pLen; i++) {
            ++sCount[s.charAt(i) - 'a'];
            ++pCount[p.charAt(i) - 'a'];
        }

        if (Arrays.equals(pCount, sCount)) {
            result.add(0);
        }

        for (int i = pLen; i < sLen; i++) {
            --sCount[s.charAt(i - pLen) - 'a'];
            ++sCount[s.charAt(i) - 'a'];
            if (Arrays.equals(sCount, pCount)) {
                result.add(i - pLen + 1);
            }
        }


        return result;
    }

    /**
     * Your runtime beats 22.32 % of java submissions
     * Your memory usage beats 30.11 % of java submissions (44.4 MB)
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (p.length() > s.length()) {
            return result;
        }

        int windowsLength = p.length();
        char[] charArray = s.toCharArray();

        Map<Character, Integer> cache = initCache(charArray, p, windowsLength);
        if (cache.isEmpty()) {
            result.add(0);
        }

        for (int i = windowsLength; i < charArray.length; i++) {
            cache.put(charArray[i], cache.getOrDefault(charArray[i], 0) + 1);
            if (cache.get(charArray[i]) == 0) {
                cache.remove(charArray[i]);
            }

            cache.put(charArray[i - windowsLength], cache.getOrDefault(charArray[i - windowsLength], 0) - 1);
            if (cache.get(charArray[i - windowsLength]) == 0) {
                cache.remove(charArray[i - windowsLength]);
            }
            if (cache.isEmpty()) {
                result.add(i - windowsLength + 1);
            }
        }

        return result;
    }

    private Map<Character, Integer> initCache(char[] charArray, String p, int windowsLength) {
        Map<Character, Integer> cache = new HashMap<>();

        for (int i = 0; i < windowsLength; i++) {
            cache.put(charArray[i], cache.getOrDefault(charArray[i], 0) + 1);
        }

        for (char aChar : p.toCharArray()) {
            cache.put(aChar, cache.getOrDefault(aChar, 0) - 1);

            if (cache.get(aChar) == 0) {
                cache.remove(aChar);
            }
        }

        return cache;
    }
}