package cn.xy.leetcode.middle.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XiangYu
 * @create2020-11-05-10:40
 *  给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
 *
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 说明:
 *
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 *
 * 示例 1:
 *
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * 输出: 5
 *
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 *      返回它的长度 5。
 * 示例 2:
 *
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * 输出: 0
 *
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 */
public class A0127WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //结尾不在字典中
        if(!wordList.contains(endWord)){
            return  0;
        }
        //双向排队
        for (int i = 0; i < wordList.size()/2; i++) {
            List<String> beginList = canLadder(beginWord, wordList);
        }

        //TODO：BFS


        return 0;
    }


    /**
     *
     * @param word  字符
     * @param wordList 字典
     * @return 从字典中获取所有可以接龙的字符串集合
     */
    public List<String> canLadder(String word,List<String> wordList){
        List<String> canLadderList = new ArrayList<>();

        for (String s : wordList) {
            char flag = 0;
            for (int i = 0; i < word.length(); i++) {
                if(word.charAt(i) != s.charAt(i)){
                    flag++;
                }
            }
            if(flag == 1){
                canLadderList.add(s);
            }
        }
        return canLadderList;
    }



}
