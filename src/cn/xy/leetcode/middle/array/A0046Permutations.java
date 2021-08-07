package cn.xy.leetcode.middle.array;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * @author XiangYu
 * @create2021-08-07-17:11
 */
public class A0046Permutations {
    private final List<List<Integer>> result = new ArrayList<>();
    private int bitSum = 0;


    public List<List<Integer>> permute(int[] nums) {
        if(nums == null || nums.length == 0 ){
            return result;
        }


        int length = nums.length;
        //长度最多为6，没有溢出的风险
        bitSum = (int) (Math.pow(2, length) - 1);

        process(nums, 0, new ArrayList<>());

        return result;
    }

    private void process(int[] nums, int bitNum, List<Integer> list) {
        //end
        if (bitNum == bitSum) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!isOne(bitNum, i)) {
                list.add(nums[i]);
                process(nums, setBit(bitNum, i), list);
                //del the last
                list.remove(list.size()-1);
            }
        }
    }

    /**
     * 将num的第i位置为1
     *
     * @param num
     * @param i
     * @return
     */
    private static int setBit(int num, int i) {
        return (num | (1 << i));
    }

    /**
     * 查看第i位是否为1
     *
     * @param num
     * @param i
     * @return
     */
    private static boolean isOne(int num, int i) {
        return (num & (1 << i)) != 0;
    }
}
