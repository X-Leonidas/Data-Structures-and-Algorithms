package cn.xy.leetcode.easy;

import java.awt.font.TextAttribute;

/**
 * @author XiangYu
 * @create2020-12-15-16:53
 * 汉明距离
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 *
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 *
 * 注意：
 * 0 ≤ x, y < 231.
 *
 * 示例:
 *
 * 输入: x = 1, y = 4
 *
 * 输出: 2
 *
 * 解释:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 *        ↑   ↑
 *
 * 上面的箭头指出了对应二进制位不同的位置。
 */
public class A0461HammingDistance {
    public static void main(String[] args) {
        hammingDistance(4,2);
    }


    /**
     * 执行用时：5 ms, 在所有 Java 提交中击败了11.18%的用户
     * 内存消耗：36.1 MB, 在所有 Java 提交中击败了5.09%的用户
     * @param x
     * @param y
     * @return
     */
    public static  int hammingDistance(int x, int y) {
        String s = Integer.toBinaryString(x ^ y);
        int length = s.length();
        s = s.replaceAll("1","");
        return length - s.length();
    }

    /**
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.3 MB, 在所有 Java 提交中击败了68.68%的用户
     * 官方解法1：自己只思考到了异或
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance2(int x, int y) {
        return Integer.bitCount(x ^ y);
    }

    /**
     * 移位操作
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.1 MB, 在所有 Java 提交中击败了82.41%的用户
     * @param x
     * @param y
     * @return
     */
    public static  int hammingDistance3(int x, int y) {
       int target = x ^ y;

       int count = 0;
       while (target != 0){
           if(target%2 == 1){
               count++;
           }
           target = target >> 1;
       }
       return  count;
    }

    /**
     * 布赖恩·克尼根算法
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance4(int x, int y) {
        int xor = x ^ y;
        int distance = 0;

        //使用 布赖恩·克尼根算法，有多少个1循环多少次就可以得出结果
        while (xor != 0) {
            distance += 1;
            // remove the rightmost bit of '1'

            xor = xor & (xor - 1);
        }
        return distance;
    }

}
