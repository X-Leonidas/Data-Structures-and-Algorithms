package cn.xy.leetcode.middle.heap;

/**
 * @author xiangyu
 * @date 2024-03-22 0:25
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * <p>
 * 示例 1:
 * 输入: [3,2,1,5,6,4], k = 2
 * 输出: 5
 * <p>
 * 示例 2:
 * 输入: [3,2,3,1,2,4,5,5,6], k = 4
 * 输出: 4
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= k <= nums.length <= 105
 * -104 <= nums[i] <= 104
 */
public class A0215KthLargestElementInArray {


    public static void main(String[] args) {
        A0215KthLargestElementInArray a0215KthLargestElementInArray = new A0215KthLargestElementInArray();
        a0215KthLargestElementInArray.findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4);
    }

    /**
     * 堆排序
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            heapInsert(nums, i);
        }
        int size = nums.length - 1;
        swap(nums, 0, size);
        int curK = k;
        curK--;
        while (curK > 0) {
            heapify(nums, 0, size);
            size--;
            curK--;
            swap(nums, 0, size);
        }

        return nums[nums.length - k];
    }

    public void heapInsert(int[] nums, int index) {
        while (nums[(index - 1) / 2] < nums[index]) {
            swap(nums, (index - 1) / 2, index);
            index = (index - 1) / 2;
        }
    }

    public void heapify(int[] nums, int index, int size) {
        int left = index * 2 + 1;
        while (left < size) {
            int larger = left + 1 < size && nums[left] < nums[left + 1] ? left + 1 : left;
            if (nums[larger] <= nums[index]) {
                break;
            }
            swap(nums, larger, index);
            // 自己错误点，丢了这一句，导致结果错误
            index = larger;
            left = larger * 2 + 1;
        }
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 桶排序
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest2(int[] nums, int k) {
        int[] buckets = new int[20001];
        for (int num : nums) {
            buckets[num + 10000]++;
        }
        for (int i = 20000; i >= 0; i--) {
            k = k - buckets[i];
            if (k <= 0) {
                return i - 10000;
            }
        }
        return 0;
    }
}
