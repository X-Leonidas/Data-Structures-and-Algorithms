package cn.xy.leetcode.middle.array;

/**
 * 注意到下一个排列总是比当前排列要大，除非该排列已经是最大的排列。我们希望找到一种方法，能够找到一个大于当前序列的新序列，且变大的幅度尽可能小。具体地：
 *
 * 我们需要将一个左边的「较小数」与一个右边的「较大数」交换，以能够让当前排列变大，从而得到下一个排列。
 *
 * 同时我们要让这个「较小数」尽量靠右，而「较大数」尽可能小。当交换完成后，「较大数」右边的数需要按照升序重新排列。这样可以在保证新排列大于原来排列的情况下，使变大的幅度尽可能小。
 *
 * 以排列 [4,5,2,6,3,1][4,5,2,6,3,1] 为例：
 *
 * 我们能找到的符合条件的一对「较小数」与「较大数」的组合为 22 与 33，满足「较小数」尽量靠右，而「较大数」尽可能小。
 *
 * 当我们完成交换后排列变为 [4,5,3,6,2,1][4,5,3,6,2,1]，此时我们可以重排「较小数」右边的序列，序列变为 [4,5,3,1,2,6][4,5,3,1,2,6]。
 */
public class A0031NextPermutation{
    /**
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int length = nums.length;
        if (length <= 1) {
            return;
        }
        int little = -1;
        int bigger = 0;

        for (int i = length-1; i >= 1; i--) {
            if(nums[i-1] < nums[i]){
                little = i-1;
                break;
            }
        }
        if(little != -1){
            for(int i = length-1; i >= little; i --){
                if(nums[i] > nums[little]){
                    bigger = i;
                    break;
                }
            }

            swap(nums,bigger,little);
        }
        //双指针重新排列后续数据，使得数字尽量的小
        reverse(nums,little+1,length-1);
    }
    private static void swap(int[] nums,int index1, int index2){
        int temp  = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    private  static void reverse(int[] nums,int start, int end){
        while(start < end){
            swap(nums,start,end);
            start++;
            end--;
        }
    }
}
