package cn.xy.leetcode.array;

import java.util.*;

/**
 * @author XiangYu
 * @create2020-11-06-13:26
 * 给你一个整数数组 arr 。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。
 *
 * 如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。
 *
 * 请你返回排序后的数组。
 *
 * 示例 1：
 *
 * 输入：arr = [0,1,2,3,4,5,6,7,8]
 * 输出：[0,1,2,4,8,3,5,6,7]
 * 解释：[0] 是唯一一个有 0 个 1 的数。
 * [1,2,4,8] 都有 1 个 1 。
 * [3,5,6] 有 2 个 1 。
 * [7] 有 3 个 1 。
 * 按照 1 的个数排序得到的结果数组为 [0,1,2,4,8,3,5,6,7]
 * 示例 2：
 *
 * 输入：arr = [1024,512,256,128,64,32,16,8,4,2,1]
 * 输出：[1,2,4,8,16,32,64,128,256,512,1024]
 * 解释：数组中所有整数二进制下都只有 1 个 1 ，所以你需要按照数值大小将它们排序。
 * 示例 3：
 *
 * 输入：arr = [10000,10000]
 * 输出：[10000,10000]
 * 示例 4：
 *
 * 输入：arr = [2,3,5,7,11,13,17,19]
 * 输出：[2,3,5,17,7,11,13,19]
 * 示例 5：
 *
 * 输入：arr = [10,100,1000,10000]
 * 输出：[10,100,10000,1000]
 *  
 *
 * 提示：
 *
 * 1 <= arr.length <= 500
 * 0 <= arr[i] <= 10^4
 */
public class A1356SortIntegersByTheNumberOf1Bits {


    public static void main(String[] args) {
        int[] arr =  {2,3,5,7,11,13,17,19};
        int[] ints = sortByBits(arr);
        for (int i : ints) {
            System.out.print(i+" ");
        }
    }

    /**
     * 使用比较器，暴力破解
     * @param arr
     * @return
     */
    public static int[] sortByBits(int[] arr) {
        int[] bit = new int[10000];
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
            bit[arr[i]] = get(arr[i]);
        }

        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(bit[o1] != bit[o2]){
                    return bit[o1] - bit[o2];
                }else {
                    return  o1-o2;
                }
            }
        });

        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
        

        return arr;
    }

    /**
     * 使用bitCount来获取1的数量，然后做权重处理
     * @param arr
     * @return
     */
    public int[] sortByBits3(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] += (Integer.bitCount(arr[i]) << 16);
        }

        Arrays.sort(arr);

        for (int i = 0; i < arr.length; i++) {
            arr[i] &= 16383;
        }

        return arr;
    }


    private static int get(int i) {
        int rd = 0;
        while(i != 0){
            rd += i%2;
            i = i/2;
        }

        return  rd;
    }

    /**
     * 通过位运算获取1的个数
     * @param i
     * @return
     */
    private static int get2(int i) {
        int rd = 0;
        while(i != 0){
           i = i & i-1;
           rd++;
        }


        return  rd;
    }


    /**
     * 神仙写法
     * @param arr
     * @return
     */
    public int[] sortByBits2(int[] arr) {
        int[] array = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            array[i] = Integer.bitCount(arr[i]) * 10000000 + arr[i];
        }
        Arrays.sort(array);
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] % 10000000;
        }
        return array;

    }




}
