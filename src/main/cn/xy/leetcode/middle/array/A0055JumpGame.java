package cn.xy.leetcode.middle.array;

/**
 *
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个下标。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 * 示例 2：
 *
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 3 * 104
 * 0 <= nums[i] <= 105
 *
 *
 *
 * @author XiangYu
 * @create2021-08-07-17:19
 */
public class A0055JumpGame {

    public static void main(String[] args) {
        int[] ints = {2,5,0,0};

        System.out.println(new A0055JumpGame().canJump(ints));


    }

    /**
     * 思路： 贪心算法
     * 卡点： 时间限制
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {

        if(nums == null || nums.length  == 0){
            return false;
        }

        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }

   
}
