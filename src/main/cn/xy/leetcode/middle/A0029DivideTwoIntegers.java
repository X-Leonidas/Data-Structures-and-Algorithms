package cn.xy.leetcode.middle;

/**
 * @author xiangyu
 * @date 2024-01-25 0:42
 * <p>
 * 给你两个整数，被除数 dividend 和除数 divisor。将两数相除，要求 不使用 乘法、除法和取余运算。
 * 整数除法应该向零截断，也就是截去（truncate）其小数部分。例如，8.345 将被截断为 8 ，-2.7335 将被截断至 -2 。
 * 返回被除数 dividend 除以除数 divisor 得到的 商 。
 * 注意：假设我们的环境只能存储 32 位 有符号整数，其数值范围是 [−231,  231 − 1] 。本题中，如果商 严格大于 231 − 1 ，则返回 231 − 1 ；
 * 如果商 严格小于 -231 ，则返回 -231 。
 * <p>
 * 示例 1:
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 解释: 10/3 = 3.33333.. ，向零截断后得到 3 。
 * 示例 2:
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 解释: 7/-3 = -2.33333.. ，向零截断后得到 -2 。
 * <p>
 * 提示：
 * -2 31 <= dividend, divisor <= 2 31 - 1
 * divisor != 0
 */
public class A0029DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        // 因为1的指数无论是多少，结果都是1，所以要首先排除，dividend == 0 剪枝
        if (divisor == 1 || dividend == 0) {
            return dividend;
        }
        // 越界的情况
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        boolean sign = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);
        dividend = dividend < 0 ? dividend : -dividend;
        divisor = divisor < 0 ? divisor : -divisor;
        int ant = 0;
        while (dividend <= divisor) {
            int temp = divisor;
            int cnt = 1;
            // 判断条件这么卡，是因为 最大为 Integer.MIN_VALUE， dividend << 1 有可能越界，temp << 1 也有可能越界
            while(temp >= Integer.MIN_VALUE >> 1 && temp << 1 >= dividend){
                // 每次翻倍增长，所以以divisor为底的指数也要翻一倍
                temp = temp << 1;
                cnt = cnt << 1;
            }
            ant = ant + cnt;
            dividend = dividend - temp;
        }

        return sign ? ant : -ant;
    }

}
