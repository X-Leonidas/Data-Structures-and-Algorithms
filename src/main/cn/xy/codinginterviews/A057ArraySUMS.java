package cn.xy.codinginterviews;

import java.util.ArrayList;
import java.util.List;

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
    /**
     *
     * @param target
     * @return
     *
     * 执行用时：3 ms, 在所有 Java 提交中击败了83.07%的用户
     * 内存消耗：36.7 MB, 在所有 Java 提交中击败了31.55%的用户
     */
    public int[][] findContinuousSequence(int target) {
        int length = target/2+1;
        if(length < 3){
            return new int[0][];
        }
        //获取
        ArrayList<int[]> list = new ArrayList<>();

        for (int i = 1; i < length; i++) {
            int sum = 0;
            int temp = i;

            while(sum < target){
                sum = temp + sum;
                temp ++;
            }
            if(sum == target){
                int index  = 0 ;
                int[] ints = new int[temp-i];
                for (int i1 = i; i1 < temp; i1++) {
                   ints[index] = i;
                   index++;
                }
                list.add(ints);
            }
        }
        
        int size = list.size();
        int[][] ints = new int[size][];
        for (int i = 0; i < list.size(); i++) {
            ints[i]  = list.get(i);
        }

        return  ints;
    }


    /**
     * 滑动窗口
     * @param target
     * @return
     */

    public int[][] findContinuousSequence2(int target) {
        // 滑动窗口的左边界
        int i = 1;
        // 滑动窗口的右边界
        int j = 1;
        // 滑动窗口中数字的和
        int sum = 0;
        List<int[]> res = new ArrayList<>();

        while (i <= target / 2) {
            if (sum < target) {
                // 右边界向右移动
                sum += j;
                j++;
            } else if (sum > target) {
                // 左边界向右移动
                sum -= i;
                i++;
            } else {
                // 记录结果
                int[] arr = new int[j-i];
                for (int k = i; k < j; k++) {
                    arr[k-i] = k;
                }
                res.add(arr);
                // 左边界向右移动
                sum -= i;
                i++;
            }
        }

        return res.toArray(new int[res.size()][]);
    }

}
