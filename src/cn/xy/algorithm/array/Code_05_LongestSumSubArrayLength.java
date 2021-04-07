package cn.xy.algorithm.array;

import java.util.HashMap;

/**
 * @author XiangYu
 * @create2021-03-26-20:43
 *
 *
 *  给定一个无序数组arr,其中元素可正可负，给定一个整数k,求arr中所有子数组中累加为和为k的最长子数组长度
 *
 *
 *  思路：
 *      记录当前索引能达到的最大累加和Sum
 *      用(sum-k)在map中校验是否出现过，
 *          如果出现过，则说明从出现过的位置到当前位置能得到k的长度
 *          如果没出现过，则加入到map中
 */
public class Code_05_LongestSumSubArrayLength {
    /**
     *
     * @param arr
     * @param k  目标值
     * @return
     */
    public static int maxLength(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        //塞入0,-1是为了确认在0是在-1上最早出现的，区别sum-aim也有可能得到0的情况。
        map.put(0, -1);
        int len = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum - k)) {
                len = Math.max(i - map.get(sum - k), len);
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return len;
    }


    public static int[] generateArray(int size) {
        int[] result = new int[size];
        for (int i = 0; i != size; i++) {
            result[i] = (int) (Math.random() * 11) - 5;
        }
        return result;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = generateArray(20);
        printArray(arr);
        System.out.println(maxLength(arr, 10));

    }
}


/**
 *   扩展
 *   一个数组中都是整数，求数组中奇数偶数相等的子数组的最长长度
 *   思路：将奇数改为1 偶数改为-1，求累加和为0的最长子数组长度
 *
 *   本质就是让需要相等的元素和为0，可以变形出很多提醒
 */




