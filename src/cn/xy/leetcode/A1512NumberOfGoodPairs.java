package cn.xy.leetcode;

/**
 * @author XiangYu
 * @create2020-11-01-23:35
 *
 * 给你一个整数数组 nums 。
 *
 * 如果一组数字 (i,j) 满足 nums[i] == nums[j] 且 i < j ，就可以认为这是一组 好数对 。
 *
 * 返回好数对的数目。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,1,1,3]
 * 输出：4
 * 解释：有 4 组好数对，分别是 (0,3), (0,4), (3,4), (2,5) ，下标从 0 开始
 * 示例 2：
 *
 * 输入：nums = [1,1,1,1]
 * 输出：6
 * 解释：数组中的每组数字都是好数对
 * 示例 3：
 *
 * 输入：nums = [1,2,3]
 * 输出：0
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 */
public class A1512NumberOfGoodPairs {

    public static void main(String[] args) {
        int[] nums = {1,2,3,1,1,3};
        System.out.println(new A1512NumberOfGoodPairs().numIdenticalPairs2(nums));
    }
    public int numIdenticalPairs(int[] nums) {

        int sum = 0;

        for (int i = 0; i < nums.length-1; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if(nums[i] == nums[j]){
                    sum++;
                }
            }
        }

        return sum;
    }

    /**
     *
     * @param nums
     * @return
     *
     *这个题目可以进行化简, 化简后的题目可以描述为:
     *统计一个数组中重复的数字个数. (统计规则有特殊要求:第一次出现数字+0 第二次出现数字总数+1，第三次出现数字总数+2 )
     */
    public int numIdenticalPairs2(int[] nums) {
        int ans = 0;
        int[] temp =new int[100];

        for (int num : nums) {
            ans  += temp[num-1];
            temp[num-1]++;
        }
        return  ans;
    }
}
