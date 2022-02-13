package cn.xy.leetcode.middle;

/**
 * @author XiangYu
 * @create2020-12-15-11:03
 * 单调递增的数字
 * 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
 *
 * （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）
 * 示例 1:
 *
 * 输入: N = 10
 * 输出: 9
 * 示例 2:
 *
 * 输入: N = 1234
 * 输出: 1234
 * 示例 3:
 *
 * 输入: N = 332
 * 输出: 299
 * 说明: N 是在 [0, 10^9] 范围内的一个整数。
 */
public class A0738MonotoneIncreasingDigits {

    public static void main(String[] args) {
        int i = monotoneIncreasingDigits2(100);
        System.out.println(i);
    }

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了97.96%的用户
     * 内存消耗：35.2 MB, 在所有 Java 提交中击败了84.58%的用户
     * @param N
     * @return
     */
    public static int monotoneIncreasingDigits(int N) {
        //位数
        int count = String.valueOf(N).length();
        if(count == 1){
            return  N;
        }
        //取每一位最高位用数组保存
        int[] chars = new int[count];
        int temp = N;
        for (int i = count-1; i >=0 ; i--) {
            chars[i] = temp%10;
            temp = temp/10;
        }
        //如果当前位不满足递增，则将前一位减一，后面位数全部置为9
        //修改没有考虑100这种情况
        for (int i = count-1; i > 0 ; i--) {
            if(chars[i] < chars[i-1]){
                chars[i-1] = --chars[i-1];
                int index = i;
                while (index < count){
                    chars[index] = 9;
                    index++;
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < count ; i++) {
            sum = sum * 10 + chars[i];
        }
        return sum;
    }


    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了97.96%的用户
     * 内存消耗：35.2 MB, 在所有 Java 提交中击败了79.69%的用户
     * @param N
     * @return
     *
     *
     */
    public static int monotoneIncreasingDigits2(int N) {
        char[] chars = String.valueOf(N).toCharArray();
        int count = chars.length;
        if(count == 1){
            return  N;
        }
        //如果当前位不满足递增，则将前一位减一，后面位数全部置为9
        //修改没有考虑100这种情况
        for (int i = count-1; i > 0 ; i--) {
            if(chars[i] < chars[i-1]){
                chars[i-1] = --chars[i-1];
                int index = i;
                while (index < count){
                    chars[index] = '9';
                    index++;
                }
            }
        }
        return Integer.parseInt(new String(chars));
    }
}
