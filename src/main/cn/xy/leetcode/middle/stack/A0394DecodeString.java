package cn.xy.leetcode.middle.stack;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xiangyu
 * @date 2024-03-20 0:20
 * <p>
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * <p>
 * 示例 1：
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 * <p>
 * 示例 2：
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 * <p>
 * 示例 3：
 * 输入：s = "2[abc]3[cd]ef"
 * 输出："abcabccdcdcdef"
 * <p>
 * 示例 4：
 * 输入：s = "abc3[cd]xyz"
 * 输出："abccdcdcdxyz"
 * <p>
 * 提示：
 * 1 <= s.length <= 30
 * s 由小写英文字母、数字和方括号 '[]' 组成
 * s 保证是一个 有效 的输入。
 * s 中所有整数的取值范围为 [1, 300]
 */
public class A0394DecodeString {

    public static void main(String[] args) {
        A0394DecodeString a0394DecodeString = new A0394DecodeString();
        a0394DecodeString.decodeString("3[a2[c]]");
    }

    public String decodeString(String s) {
        int k = 0;
        StringBuilder res = new StringBuilder();
        // 数量栈
        Stack<Integer> kstack = new Stack<>();
        // 字符串栈
        Stack<StringBuilder> restack = new Stack<>();
        for(char c : s.toCharArray()){
            if(c == '['){
                //碰到括号，记录K和当前res，归零。
                kstack.push(k);
                restack.push(res);
                k = 0;
                res = new StringBuilder();
            }else if(c ==']'){
                //出最近的一个左括号入的k,当前res进行计算不入栈
                int curk = kstack.pop();
                StringBuilder temp = new StringBuilder();
                for(int i = 0; i < curk; i++){
                    temp.append(res);
                }
                //与括号外合并
                res = restack.pop().append(temp);
            }else if(c >= '0' && c <= '9'){
                k = c - '0' + k * 10;
                //如果k是多位数需要x10
            }else{
                res.append(c);
            }
        }
        return res.toString();
    }
}
