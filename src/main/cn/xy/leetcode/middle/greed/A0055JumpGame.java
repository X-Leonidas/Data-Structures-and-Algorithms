package cn.xy.leetcode.middle.greed;


/**
 * 给定一个非负整数数组nums ，你最初位于数组的 第一个下标 。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 * <p>
 * 示例1：
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 * <p>
 * 示例2：
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 3 * 104
 * 0 <= nums[i] <= 105
 *
 * @author XiangYu
 * @date 2021-08-07-17:19
 */
public class A0055JumpGame {
    public static void main(String[] args) {
        int[] ints = {2, 5, 0, 0};
        System.out.println(new A0055JumpGame().canJump(ints));
    }

    /**
     * 思路： 贪心算法
     * 卡点： 时间限制
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        int maxStep = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i <= maxStep) {
                maxStep = Math.max(maxStep, i + nums[i]);
                if (maxStep > nums.length) {
                    return true;
                }
            }
        }
        return false;
    }


}
