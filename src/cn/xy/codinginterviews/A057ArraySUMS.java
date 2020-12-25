package cn.xy.codinginterviews;

import java.util.ArrayList;

/**
 * @author XiangYu
 * @create2020-12-25-16:49
 *  剑指 Offer 57 - II. 和为s的连续正数序列
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 *
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 *
 * 示例 1：
 *
 * 输入：target = 9
 * 输出：[[2,3,4],[4,5]]
 * 示例 2：
 *
 * 输入：target = 15
 * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
 *
 *
 * 限制：
 *
 * 1 <= target <= 10^5
 */
public class A057ArraySUMS {
    public int[][] findContinuousSequence(int target) {
        int length = target/2+1;
        if(length < 3){
            return new int[0][];
        }
        ArrayList<int[]> list = new ArrayList<>();



        int size = list.size();
        int[][] ints = new int[size][];
        for (int i = 0; i < list.size(); i++) {
            ints[0]  = list.get(0);
        }

        return  ints;
    }
}
