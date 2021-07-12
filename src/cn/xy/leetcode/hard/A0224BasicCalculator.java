package cn.xy.leetcode.hard;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author XiangYu
 * @create2021-05-04-18:25
 *  给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "1 + 1"
 * 输出：2
 * 示例 2：
 *
 * 输入：s = " 2-1 + 2 "
 * 输出：3
 * 示例 3：
 *
 * 输入：s = "(1+(4+5+2)-3)+(6+8)"
 * 输出：23
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 3 * 105
 * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
 * s 表示一个有效的表达式
 *
 *
 */
public class A0224BasicCalculator {
    public static int calculate(String s) {
        s= s.replace(" ","");

        char[] chars = s.toCharArray();
        LinkedList<String> stack1 = new LinkedList<>();
        LinkedList<String> stack2 = new LinkedList<>();
        int res = 0;
        StringBuilder sb = new StringBuilder();
        for (char aChar : chars) {
            //非数字
            if(aChar < 48 || aChar > 57){
                if(sb.length() > 0){
                    stack1.offer(sb.toString());
                }
                sb.delete(0,sb.length());
                if(aChar == ')'){
                    while(!stack1.peekLast().equals("(")) {
                        stack2.offer(stack1.pollLast());
                    }
                    //去掉左括号
                   stack1.pollLast();
                    int temp = 0;
                    while(!stack2.isEmpty()){
                       String  ts = stack2.pollLast();
                       if(ts.equals("+")){
                            temp = temp + Integer.parseInt(stack2.pollLast());
                       }else  if(ts.equals("-")){
                            temp = temp - Integer.parseInt(stack2.pollLast());
                       }else {
                           temp = Integer.parseInt(ts);
                       }
                    }

                    stack1.offer(String.valueOf(temp));
                }else{
                    stack1.offer(String.valueOf(aChar));
                }

            }else{
                sb.append(aChar);
            }
        }
        if(sb.length() > 0){
            stack1.offer(sb.toString());
        }


        while(!stack1.isEmpty()){
            String  ts = stack1.pollFirst();
            if(ts.equals("+")){
                res = res + Integer.parseInt(stack1.pollFirst());
            }else  if(ts.equals("-")){
                res = res - Integer.parseInt(stack1.pollFirst());
            }else {
                res = Integer.parseInt(ts);
            }
        }


        return res;
    }


    /**
     * 官方题解
     * @param s
     * @return
     */
    public static int calculate2(String s) {
        Deque<Integer> ops = new LinkedList<Integer>();
        ops.push(1);
        //当前符号表示
        int sign = 1;
        //结果
        int ret = 0;
        int n = s.length();
        int i = 0;
        while (i < n) {
            if (s.charAt(i) == ' ') {
                i++;
            } else if (s.charAt(i) == '+') {
                sign = ops.peek();
                i++;
            } else if (s.charAt(i) == '-') {
                sign = -ops.peek();
                i++;
            } else if (s.charAt(i) == '(') {
                ops.push(sign);
                i++;
            } else if (s.charAt(i) == ')') {
                ops.pop();
                i++;
            } else {
                //拿到数字
                long num = 0;
                while (i < n && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                ret += sign * num;
            }
        }
        return ret;
    }



    public static void main(String[] args) {

        calculate2("1-(-1)");

    }
}
