package cn.xy.leetcode.easy.array;

import java.util.*;

/**
 * @author XiangYu
 * @create2020-12-18-15:27 找到所有数组中消失的数字
 * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
 * <p>
 * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 * <p>
 * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [4,3,2,7,8,2,3,1]
 * <p>
 * 输出:
 * [5,6]
 */
public class A0448FindAllNumbersDisappearedInAnArray {

    public static void main(String[] args) {
        int[] ints = {4,3,2,7,8,2,3,1};
        List<Integer> disappearedNumbers = findDisappearedNumbers2(ints);
        for (Integer disappearedNumber : disappearedNumbers) {
            System.out.println(disappearedNumber + "    ");
        }
    }

    public static List<Integer> findDisappearedNumbers(int[] nums) {
        HashMap<Integer, Boolean> hashTable = new HashMap<Integer, Boolean>();

        for (int i = 0; i < nums.length; i++) {
            hashTable.put(nums[i], true);
        }

        List<Integer> result = new LinkedList<Integer>();

        for (int i = 1; i <= nums.length; i++) {
            if (!hashTable.containsKey(i)) {
                result.add(i);
            }
        }

        return result;

    }



    public static List<Integer> findDisappearedNumbers2(int[] nums) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) -1;
            if(nums[index] > 0){
                //转换为负数
                nums[index] =  nums[index]  * -1;
            }
        }


        for (int i = 0; i < nums.length; i++) {
            if(nums[i] > 0){
                list.add(i+1);
            }
        }
        return list;
    }
}
