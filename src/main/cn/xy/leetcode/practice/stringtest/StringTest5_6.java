package cn.xy.leetcode.practice.stringtest;

/**
 * @author XiangYu
 * @create2020-12-11-16:20\ 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 * <p>
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 */
public class StringTest5_6 {

    public static void main(String[] args) {
        int[] ints = {1, 1, 1, 0, 12};
        moveZeroes(ints);
        for (int anInt : ints) {
            System.out.print(anInt + "    ");
        }
    }

    /**
     * 执行用时：0 ms , 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了23.02%的用户
     *
     * @param nums
     */
    public static void moveZeroes(int[] nums) {

        //慢指针
        int index = 0;

        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != 0){
                int temp = nums[index];
                nums[index] = nums[i];
                index++;
                nums[i] = temp;
            }
        }
    }


    /**
     *执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了89.61%的用户
     * @param nums
     */
    public static void moveZeroes2(int[] nums) {

        //慢指针
        int index = 0;

        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != 0){
                if(index != i){
                    nums[index] = nums[i];
                    nums[i] = 0;
                }
                index++;
            }
        }
    }


}
