package cn.xy.leetcode.easy.array;

/**
 * @author XiangYu
 * @create2020-12-31-14:00
 *
 * 88. 合并两个有序数组
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * 说明：
 *
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *
 * 示例：
 *
 * 输入：
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * 输出：[1,2,2,3,5,6]
 *
 */
public class A0088MergeSortedArray {

    public static void main(String[] args) {
        int[] nums1 = {1,2,3,0,0,0,};
        int[] nums2 = {2,5,6};
        merge(nums1,3,nums2,3);
    }


    /**
     *
     * [1,2,3,0,0,0]
     * 3
     * [2,5,6]
     * 3
     *
     * [1,2,2,0,6,0]
     * 预期结果
     * [1,2,2,3,5,6]
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int nums1Index = 0;
        int nums2Index = 0;
        for (int i = nums2Index; i < nums2.length; i++) {
            boolean flag = false;
            for (int j = nums1Index; j < nums1.length; j++) {
                if(nums1[j] > nums2[i]){
                    //当前nums1总长度
                    int tempL = nums2Index  + m -1;
                    while(tempL >= j){
                        nums1[tempL+1] = nums1[tempL];
                        tempL--;
                    }
                    nums1[j] = nums2[i];
                    nums1Index = j + 1;
                    nums2Index++;
                    flag = true;
                    break;
                }
            }

            if(!flag){
                break;
            }
        }

        for (int i = nums2Index; i < nums2.length; i++) {
            nums1[m+i] = nums2[i];
        }
    }




}
