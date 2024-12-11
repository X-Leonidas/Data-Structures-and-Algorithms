package cn.xy.leetcode.easy.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

/**
 * @author XiangYu
 * @create2020-12-17-16:34
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于n/2 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 */
public class A0169MajorityElement {

    public static void main(String[] args) {
        int[] ints = {3,3,4};
        int i = majorityElement(ints);
        System.out.println(i);

    }

    /**
     * 执行用时：16 ms, 在所有 Java 提交中击败了25.17%的用户
     * 内存消耗：43.9 MB, 在所有 Java 提交中击败了16.48%的用户
     * 哈希表
     * @param nums
     * @return
     */
    public static int majorityElement(int[] nums) {

        if(nums.length == 1){
            return nums[0];
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if(!map.containsKey(nums[i])){
                map.put(nums[i],1);
            }else {
                int conunt = map.get(nums[i])+1;
                map.put(nums[i],conunt);
                if(conunt > (length >>1)){
                    return nums[i];
                }

            }
        }
        return -1;
    }

    /**
     * 排序
     * @param nums
     * @return
     */
    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }


    /**
     * 随机数
     * @param nums
     * @return
     */
    public int majorityElement3(int[] nums) {
        Random rand = new Random();

        int majorityCount = nums.length / 2;

        while (true) {
            int candidate = nums[randRange(rand, 0, nums.length)];
            if (countOccurences(nums, candidate) > majorityCount) {
                return candidate;
            }
        }
    }
    private int randRange(Random rand, int min, int max) {
        return rand.nextInt(max - min) + min;
    }

    private int countOccurences(int[] nums, int num) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }


    /**
     * 摩尔投票法思路
     * 候选人(cand_num)初始化为nums[0]，票数count初始化为1。
     * 当遇到与cand_num相同的数，则票数count = count + 1，否则票数count = count - 1。
     * 当票数count为0时，更换候选人，并将票数count重置为1。
     * 遍历完数组后，cand_num即为最终答案。
     *
     * 为何这行得通呢？
     * 投票法是遇到相同的则票数 + 1，遇到不同的则票数 - 1。
     * 且“多数元素”的个数> ⌊ n/2 ⌋，其余元素的个数总和<= ⌊ n/2 ⌋。
     * 因此“多数元素”的个数 - 其余元素的个数总和 的结果 肯定 >= 1。
     * 这就相当于每个“多数元素”和其他元素 两两相互抵消，抵消到最后肯定还剩余至少1个“多数元素”。
     *
     * 无论数组是1 2 1 2 1，亦或是1 2 2 1 1，总能得到正确的候选人。
     */

    public int majorityElement4(int[] nums) {
       int candNum = nums[0];
       int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] == candNum){
                count++;
            }else{
                count--;
                if(count == 0){
                    candNum = nums[i];
                    count = 1;
                }
            }

        }
        return candNum;
    }

}
