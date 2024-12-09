package cn.xy.leetcode.easy.array;

/**
 * @author xiangyu
 * @date 2024-12-05 22:04
 * 给你一个 非严格递增排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，
 * 返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。然后返回 nums 中唯一元素的个数。
 * 考虑 nums 的唯一元素的数量为 k ，你需要做以下事情确保你的题解可以被通过：
 * <p>
 * 更改数组 nums ，使 nums 的前 k 个元素包含唯一元素，并按照它们最初在 nums 中出现的顺序排列。
 * nums 的其余元素与 nums 的大小不重要。
 * <p>
 * 返回 k 。
 * <p>
 * 1 <= nums.length <= 3 * 104
 * -104 <= nums[i] <= 104
 * nums 已按 非严格递增 排列
 */
public class A0026RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {
        int[] arg = new int[]{0,0,1,1,1,2,2,3,3,4};
        new A0026RemoveDuplicatesFromSortedArray().removeDuplicates(arg);
        System.out.println(arg);
    }

    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int fast = 1, slow = 0;
        while (fast < n) {
            if (nums[slow] != nums[fast]) {
                nums[slow+1] = nums[fast];
                slow++;
            }
            ++fast;
        }
        return slow+1;
    }
}
