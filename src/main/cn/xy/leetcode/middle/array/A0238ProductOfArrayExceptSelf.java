package cn.xy.leetcode.middle.array;

/**
 * @author xiangyu
 * @date 2024-01-31 23:54
 * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
 * <p>
 * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
 * <p>
 * 请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,2,3,4]
 * 输出: [24,12,8,6]
 * 示例 2:
 * <p>
 * 输入: nums = [-1,1,0,-3,3]
 * 输出: [0,0,9,0,0]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= nums.length <= 105
 * -30 <= nums[i] <= 30
 * 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内
 * <p>
 * <p>
 * 进阶：你可以在 O(1) 的额外空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组 不被视为 额外空间。）
 */
public class A0238ProductOfArrayExceptSelf {
    //假如nums为[1,2,3,4]，那么answer的值分别为[(2,3,4),(1,3,4),(1,2,4),(1,2,3)]
    //如果吧i当前值相乘的时候看做是1那么就有如下样式
    //  1, 2, 3, 4
    //  1, 1, 3, 4
    //  1, 2, 1, 4
    //  1, 2, 3, 1
    // 他的对角线1将他们分割成了两个三角形，对于answer的元素，
    //我们可以先计算一个三角形每行的乘积，然后再去计算另外一个三角形每行的乘积，
    //然后各行相乘，就是answer每个对应的元素

    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return new int[0];
        }
        //先初始化一个answer数组,但是很多解答都没说明的是这个answer数组，
        //并不是一次计算就得出的结果,而是两次乘积之后的结果
        int[] ans = new int[len];
        //初始化一个初始值，作为三角乘积计算的开始
        ans[0] = 1;
        int tmp = 1;

        //先计算左边三角的乘积
        for (int i = 1; i < len; i++) {
            ans[i] = ans[i - 1] * nums[i - 1];
        }
        //再计算右边三角的乘积
        for (int i = len - 2; i >= 0; i--) {
            tmp *= nums[i + 1];
            ans[i] *= tmp;
        }
        return ans;
    }


    public int[] productExceptSelf2(int[] nums) {
        if (nums == null || nums.length == 1) {
            return nums;
        }

        int[] ans = new int[nums.length];
        ans[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            ans[i] = ans[i - 1] * nums[i - 1];
        }
        int temp = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            temp = temp * nums[i + 1];
            ans[i] = ans[i] * temp;
        }
        return ans;
    }
}
