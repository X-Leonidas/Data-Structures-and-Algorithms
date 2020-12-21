package cn.xy.leetcode.middle.string;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author XiangYu
 * @create2020-12-20-23:44
 * 去除重复字母
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 *
 * 注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters 相同
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "bcabc"
 * 输出："abc"
 * 示例 2：
 *
 * 输入：s = "cbacdcbc"
 * 输出："acdb"
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 104
 * s 由小写英文字母组成
 */
public class A0316RemoveDuplicateLetters {
    public static void main(String[] args) {
        String  s = "cbacdcbc";
        System.out.println(removeDuplicateLetters(s));

    }
    public static String removeDuplicateLetters(String s) {
        if(s == null || s.length() < 2 ){
            return  s;
        }
        char[] letters = new char[16];
        char[] chars = s.toCharArray();

        for (char aChar : chars) {
            letters[aChar - 'a']++;
        }


        StringBuilder sb = new StringBuilder();

        char index = 'a';

        for (char aChar : chars) {






          letters[aChar-'a']--;
        }
        return  sb.toString();
    }


    public String removeDuplicateLetters2(String s) {

        boolean[] vis = new boolean[26];
        int[] num = new int[26];

        //记录字符出现的次数
        for (int i = 0; i < s.length(); i++) {
            num[s.charAt(i) - 'a']++;
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            //当前字符还没有被加入到返回结果中
            if (!vis[ch - 'a']) {
                //如果字符串>0 并且最后一位大于当前字符
                while (sb.length() > 0 && sb.charAt(sb.length() - 1) > ch) {
                    //如果该字符串不是最后一次出现则删除该字符串
                    if (num[sb.charAt(sb.length() - 1) - 'a'] > 0) {
                        vis[sb.charAt(sb.length() - 1) - 'a'] = false;
                        sb.deleteCharAt(sb.length() - 1);
                    } else {
                        break;
                    }
                }
                //加入当前字符串
                vis[ch - 'a'] = true;
                sb.append(ch);
            }
            //字符数量减一
            num[ch - 'a'] -= 1;
        }
        return sb.toString();
    }




    public String removeDuplicateLetters3(String s) {
        char[] chars = s.toCharArray();
        int[] lastInx = new int[26];
        for (int i = 0; i < chars.length ; i++) {
            //记录每个元素最后一次出现的位置
            lastInx[chars[i] - 'a'] = i;
        }
        Deque<Character> stack = new ArrayDeque<>();
        //某一个字符是否在栈中出现
        boolean[] visited = new boolean[26];
        for (int i = 0; i < chars.length; i++) {
            //如果出现舍弃当前字符
            if (visited[chars[i] - 'a']) {
                continue;
            }
            //当前字符在栈顶元素之前，且栈顶元素在后面还有
            while (!stack.isEmpty() && stack.peekLast() > chars[i] && lastInx[stack.peekLast() - 'a'] > i) {
                //移除栈顶元素
                Character c = stack.removeLast();
                //表示该字符没有在栈中出现
                visited[c - 'a'] = false;
            }
            stack.addLast(chars[i]);
            visited[chars[i] - 'a'] = true;
        }
        StringBuilder sb = new StringBuilder();
        for(Character c : stack){
            sb.append(c);
        }
        return sb.toString();
    }

}
