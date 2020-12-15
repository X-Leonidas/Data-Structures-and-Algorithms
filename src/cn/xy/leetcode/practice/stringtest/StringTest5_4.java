package cn.xy.leetcode.practice.stringtest;

/**
 * @author XiangYu
 * @create2020-12-09-17:12
 *寻找旋转排序数组中的最小值
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] 。

 * 请找出其中最小的元素。
 *  
 * 示例 1：
 *
 * 输入：nums = [3,4,5,1,2]
 * 输出：1
 * 示例 2：
 *
 * 输入：nums = [4,5,6,7,0,1,2]
 * 输出：0
 * 示例 3：
 *
 * 输入：nums = [1]
 * 输出：1
 *
 * 提示：
 *
 * 1 <= nums.length <= 5000
 * -5000 <= nums[i] <= 5000
 * nums 中的所有整数都是 唯一 的
 * nums 原来是一个升序排序的数组，但在预先未知的某个点上进行了旋转
 *
 *
 */
public class StringTest5_4 {

    public static void main(String[] args) {
        int [] nums = {3,4,5,1,2};
        System.out.println(findMin(nums));
    }

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：37.8 MB, 在所有 Java 提交中击败了84.58%的用户
     * @param nums
     * @return
     */
    public static int findMin(int[] nums) {
        //二分法查找翻转点
        int start  = 0;
        int end = nums.length -1;
        int middle = (start + end )/2;

        //判断是否翻转
        if(nums[end] > nums[start]){
            return  nums[start];
        }
//        仔细分析后，该段代码可以去掉
//        while(nums[middle] > nums[start]){
//            start  = middle;
//            middle = (start + end )/2;
//        }

        //middle已经到了翻转列中
        while(end - start > 1){
            if(nums[middle] <= nums[start]){
                end = middle;
            }else {
                start = middle;
            }
            middle = (start + end )/2;
        }
        return  nums[end];
    }

    /**
     * 别人的写法
     * @param nums
     * @return
     */
    public int findMin2(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int middle = (left + right) / 2;
            if (nums[middle] < nums[right]) {
                // middle可能是最小值
                right = middle;
            } else {
                // middle肯定不是最小值
                left = middle + 1;
            }
        }
        return nums[left];
    }
}
