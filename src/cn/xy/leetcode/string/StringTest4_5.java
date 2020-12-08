package cn.xy.leetcode.string;

/**
 * @author XiangYu
 * @create2020-12-08-9:53
 * 给定一个二进制数组， 计算其中最大连续1的个数。
 *
 * 示例 1:
 *
 * 输入: [1,1,0,1,1,1]
 * 输出: 3
 * 解释: 开头的两位和最后的三位都是连续1，所以最大连续1的个数是 3.
 * 注意：
 *
 * 输入的数组只包含 0 和1。
 * 输入数组的长度是正整数，且不超过 10,000。
 *
 */
public class StringTest4_5 {


    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了92.98%的用户
     * 内存消耗：40.1 MB, 在所有 Java 提交中击败了68.70%的用户
     * @param nums
     * @return
     */
    public int findMaxConsecutiveOnes(int[] nums) {

        int max = 0;
        int temp = 0;
        for (int num : nums) {
            if(num == 1){
                temp ++;
            }else{
                max = Math.max(temp,max);
                temp =0;
            }

        }

        return  Math.max(temp,max);
    }
}
