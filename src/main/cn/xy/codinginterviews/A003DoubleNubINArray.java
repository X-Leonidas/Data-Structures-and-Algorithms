package xy.codinginterviews;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author XiangYu
 * @create2021-01-26-16:14
 *
 *找出数组中重复的数字。
 *
 *
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 *
 * 示例 1：
 *
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 *  
 *
 * 限制：
 *
 * 2 <= n <= 100000
 *
 *
 */
public class A003DoubleNubINArray {


    /**
     * 执行用时：9 ms, 在所有 Java 提交中击败了16.63%的用户
     * 内存消耗：47.1 MB, 在所有 Java 提交中击败了39.01%的用户
     * @param nums
     * @return
     */
    public int findRepeatNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])){
                return  nums[i];
            }else {
                map.put(nums[i],0);
            }
        }
        return -1;
    }

    /**
     * 执行用时：3 ms, 在所有 Java 提交中击败了58.05%的用户
     * 内存消耗：46.4 MB, 在所有 Java 提交中击败了47.42%的用户
      * @param nums
     * @return
     */
    public int findRepeatNumber2(int[] nums) {
        int[] ints = new int[100000];

        for (int i = 0; i < nums.length; i++) {
            if(ints[nums[i]] == -1){
                return  nums[i];
            }else {
                ints[nums[i]] =-1;
            }
        }

        return  -1;
    }



    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了66.03%的用户
     * 内存消耗：45.7 MB, 在所有 Java 提交中击败了96.47%的用户
     * @param nums
     * @return
     */
    public int findRepeatNumber3(int[] nums) {
        byte[] ints = new byte[100000];

        for (int i = 0; i < nums.length; i++) {
            if(ints[nums[i]] == -1){
                return  nums[i];
            }else {
                ints[nums[i]] =-1;
            }
        }

        return  -1;
    }


    /**
     执行用时：2 ms, 在所有 Java 提交中击败了66.03%的用户
     内存消耗：46 MB, 在所有 Java 提交中击败了81.69%的用户
     * @param nums
     * @return
     */
    public int findRepeatNumber4(int[] nums) {
        boolean[] ints = new boolean[100000];

        for (int i = 0; i < nums.length; i++) {
            if(ints[nums[i]]){
                return  nums[i];
            }else {
                ints[nums[i]] = true;
            }
        }
        return  -1;
    }


    /**
     * @param nums
     * @return
     */
    public int findRepeatNumber5(int[] nums) {
        // HashSet
        HashSet<Integer> set = new HashSet<Integer>();
        for (int x : nums) {
            //发现某元素存在，返回
            if (set.contains(x)) {
                return x;
            }
            //存入哈希表
            set.add(x);
        }
        return -1;
    }

    /**
     * 原地置换
     * @param nums
     * @return
     */
    public int findRepeatNumber6(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                //发现重复元素
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                //置换，将指针下的元素换到属于他的索引处
                int temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
        }


        return -1;
    }


}
