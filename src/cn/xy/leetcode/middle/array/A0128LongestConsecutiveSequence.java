package cn.xy.leetcode.middle.array;

import org.omg.CORBA.MARSHAL;

import java.util.HashSet;

/**
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * <p>
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 示例 2：
 * <p>
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 *
 * @author XiangYu
 * @create2021-08-20-21:46
 */
class A0128LongestConsecutiveSequence {


    // TODO:https://leetcode-cn.com/problems/longest-consecutive-sequence/solution/zui-chang-lian-xu-xu-lie-by-leetcode-solution/
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {

            set.add(num);
        }

        int longerst = 1;
        for (Integer cur : set) {
            if (!set.contains(cur - 1)) {
                int curnum = cur;
                int curSize = 1;
                while (set.contains(curnum + 1)) {
                    curnum++;
                    curSize++;
                }

                longerst = Math.max(curSize, longerst);
            }
        }

        return longerst;
    }
}