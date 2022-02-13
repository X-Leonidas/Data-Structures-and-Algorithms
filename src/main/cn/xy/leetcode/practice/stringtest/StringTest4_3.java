package xy.leetcode.practice.stringtest;

/**
 * @author XiangYu
 * @create2020-12-04-15:54
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 *
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 *
 * 说明:
 *
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * 示例:
 *
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 *
 *
 */
public class StringTest4_3 {
    public static void main(String[] args) {
        int[]  numbers = {2,3,4};
        int target = 6;
        int[] ints = twoSum(numbers, target);
        System.out.print(ints[0] + " -------- ");
        System.out.println(ints[1]);
    }

    /**
     * 双指针
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了95.05%的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了45.18%的用户
     * @param numbers
     * @param target
     * @return
     */
    public static int[] twoSum(int[] numbers, int target) {
        int low = 0, high = numbers.length - 1;
        while (low < high) {
            int sum = numbers[low] + numbers[high];
            if (sum == target) {
                return new int[]{low + 1, high + 1};
            } else if (sum < target) {
                ++low;
            } else {
                --high;
            }
        }
        return new int[]{-1, -1};
    }



    /**
     * 执行用时：209 ms, 在所有 Java 提交中击败了11.00%的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了91.97%的用户
     * @param numbers
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] numbers, int target) {
        int[] targetInts = new int[2];
        for (int i = 0; i < numbers.length; i++) {
            if(numbers[i] > target){
                return  new int[3];
            }
            int different = target - numbers[i];
            for (int j = i+1; j < numbers.length; j++) {
                if(different <= numbers[j]){

                }
                if(different == numbers[j]){
                    targetInts[0] = i+1;
                    targetInts[1] = j+1;
                    return  targetInts;
                }


            }
        }
        return  targetInts;

    }


    /**
     * 二分查找
     * @param num
     * @param target
     * @return
     */
    public int[] twoSum3(int[] num, int target) {
        int length = num.length;
        for (int i = 0; i < length; i++) {
            int left = i + 1;
            int right = length - 1;
            int val = target - num[i];
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (num[mid] == val)
                    return new int[]{i + 1, mid + 1};
                else if (num[mid] < val)
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }
        return new int[]{0, 0};
    }

}
