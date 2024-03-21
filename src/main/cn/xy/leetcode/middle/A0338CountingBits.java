package cn.xy.leetcode.middle;

/**
 * @author XiangYu
 * @create2021-04-21-22:27
 *
 *  给定一个非负整数num。对于0 ≤ i ≤ num 范围中的每个数字i，计算其二进制数中的 1 的数目并将它们作为数组返回。
 *
 * 示例 1:
 *
 * 输入: 2
 * 输出: [0,1,1]
 * 示例2:
 *
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 * 进阶:
 *
 * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 * 要求算法的空间复杂度为O(n)。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的__builtin_popcount）来执行此操作。
 *
 */
public class A0338CountingBits {

    /**
     * 思路：
     * 奇数：二进制表示中，奇数一定比前面那个偶数多一个 1，因为多的就是最低位的 1。
     *           举例：
     *          0 = 0       1 = 1
     *          2 = 10      3 = 11
     * 偶数：二进制表示中，偶数中 1 的个数一定和除以 2 之后的那个数一样多。因为最低位是 0，除以 2 就是右移一位，也就是把那个 0 抹掉而已，所以 1 的个数是不变的。
     *            举例：
     *           2 = 10       4 = 100       8 = 1000
     *           3 = 11       6 = 110       12 = 1100
     * @param num
     * @return
     */
    public  int[] countBits(int num) {
        int sum = 0;
        int[] ints = new int[num + 1];

        ints[0] = 0;
        for (int i = 1; i < ints.length; i++) {
            if(i % 2 == 0){
                ints[i] = ints[i/2];
            }else {
                ints[i] = ints[i-1] + 1;
            }
        }

        return  ints;
    }

}
