package cn.xy.algorithm;

import java.util.LinkedList;

/**
 * @author XiangYu
 * @create2021-04-01-21:47
 *
 * 给定一个字符串str， str表示一个公式， 公式里可能有整数、 加减乘除符号和
 * 左右括号， 返回公式的计算结果。
 * 【举例】
 *      str="48*((70-65)-43)+8*1"， 返回-1816。
 *      str="3+1*4"， 返回7。 str="3+(1*4)"， 返回7。
 * 【说明】
 *      1． 可以认为给定的字符串一定是正确的公式， 即不需要对str做公式有效性检查。
 *      2． 如果是负数， 就需要用括号括起来， 比如"4*(-3)"。 但如果负数作为公式的
 *        开头或括号部分的开头， 则可以没有括号， 比如"-3*4"和"(-3*4)"都是合法的。
 *      3． 不用考虑计算过程中会发生溢出的情况
 *
 *  思路：没有括号的情况下，使用一个栈讲每个数字和运算符依次压入，每次压入前检查栈顶是否为乘除，如果是，则弹栈和
 *     当前值计算后在压入栈中。
 *
 */
public class Code_01_ExpressionCompute {
    public static int getValue(String str) {
        return value(str.toCharArray(), 0)[0];
    }

    /**
     *
     * @param str
     * @param i
     * @return 一个长度为2的数组，1为计算结构，2为计算到了哪个位置
     */
    public static int[] value(char[] str, int i) {
        //负责运算加减乘除，
        LinkedList<String> que = new LinkedList<String>();
        //将一个或多个字符转换为数字
        int pre = 0;
        int[] bra = null;

        while (i < str.length && str[i] != ')') {
            if (str[i] >= '0' && str[i] <= '9') {
                pre = pre * 10 + str[i++] - '0';
            } else if (str[i] != '(') {
                //遇到了加减乘除
                addNum(que, pre);
                que.addLast(String.valueOf(str[i++]));
                pre = 0;
            } else {
                //遇到了左括号
                bra = value(str, i + 1);
                pre = bra[0];
                i = bra[1] + 1;
            }
        }
        addNum(que, pre);
        return new int[] { getNum(que), i };
    }

    /**
     * 计算目前的值
     * @param que
     * @param num
     */
    public static void addNum(LinkedList<String> que, int num) {
        if (!que.isEmpty()) {
            int cur = 0;
            String top = que.pollLast();
            if (top.equals("+") || top.equals("-")) {
                que.addLast(top);
            } else {
                cur = Integer.valueOf(que.pollLast());
                num = top.equals("*") ? (cur * num) : (cur / num);
            }
        }
        que.addLast(String.valueOf(num));
    }

    public static int getNum(LinkedList<String> que) {
        int res = 0;
        boolean add = true;
        String cur = null;
        int num = 0;
        while (!que.isEmpty()) {
            cur = que.pollFirst();
            if (cur.equals("+")) {
                add = true;
            } else if (cur.equals("-")) {
                add = false;
            } else {
                num = Integer.valueOf(cur);
                res += add ? num : (-num);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String exp = "48*((70-65)-43)+8*1";
        System.out.println(getValue(exp));

        exp = "4*(6+78)+53-9/2+45*8";
        System.out.println(getValue(exp));

        exp = "10-5*3";
        System.out.println(getValue(exp));

        exp = "-3*4";
        System.out.println(getValue(exp));

        exp = "3+1*4";
        System.out.println(getValue(exp));

    }

}
