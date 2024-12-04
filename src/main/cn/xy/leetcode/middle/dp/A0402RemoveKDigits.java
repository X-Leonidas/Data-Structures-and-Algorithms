package cn.xy.leetcode.middle.dp;

import java.util.*;

/**
 * 给你一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小。
 * 请你以字符串形式返回这个最小的数字。
 * <p>
 * 示例 1 ：
 * 输入：num = "1432219", k = 3
 * 输出："1219"
 * 解释：移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219 。
 * 示例 2 ：
 * 输入：num = "10200", k = 1
 * 输出："200"
 * 解释：移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * <p>
 * 示例 3 ：
 * 输入：num = "10", k = 2
 * 输出："0"
 * 解释：从原数字移除所有的数字，剩余为空就是 0 。
 * <p>
 * 提示：
 * 1 <= k <= num.length <= 105
 * num 仅由若干位数字（0 - 9）组成
 * 除了 0 本身之外，num 不含任何前导零
 *
 * @author xiangyu
 * @date 2024-11-19 22:51
 */
public class A0402RemoveKDigits {


    /**
     *  思路： 去掉1位的话， 从左到右，d_0, d_1, d_2 ,d_i， 依次比较，如果 d_i > d_i-1 则去掉d_i 如果不存在则删除最后一位  以此类推到k
     *  这样时间复杂度较高，所以使用单调栈来维护列表
     *  错误1 ： 没有考虑到 10 去除1， 剩下 0 的情况
     * @param num
     * @param k
     * @return
     */
    public String removeKdigits(String num, int k) {
        if(k >= num.length()){
            return "0";
        }

        LinkedList<Character> deque = new LinkedList<>();
        for (char aNum : num.toCharArray()) {
            while(deque.peekLast() != null && deque.peekLast() > aNum && k > 0){
                deque.pollLast();
                k--;
            }
            deque.addLast(aNum);
        }


        while(k > 0){
            deque.pollLast();
            k--;
        }

        while(deque.peekFirst() != null && deque.peekFirst() == '0'){
            deque.pollFirst();
        }

        StringBuilder result = new StringBuilder();

        while(deque.peekFirst() != null){
            result.append(deque.pollFirst());
        }

        return result.toString().isEmpty() ? "0" : result.toString();
    }

    public static void main(String[] args) {
        String s = new A0402RemoveKDigits().removeKdigits("1432219", 3);
        System.out.println(s);
    }
}
