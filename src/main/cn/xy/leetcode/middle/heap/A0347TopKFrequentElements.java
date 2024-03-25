package cn.xy.leetcode.middle.heap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiangyu
 * @date 2024-03-22 23:00
 */
public class A0347TopKFrequentElements {
    public static void main(String[] args) {
        A0347TopKFrequentElements a0347TopKFrequentElements = new A0347TopKFrequentElements();
        a0347TopKFrequentElements.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2);
    }


    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> cache = new HashMap<>();
        for (int num : nums) {
            Integer count = cache.getOrDefault(num, 0);
            cache.put(num, ++count);
        }

        Integer[] onlyNums = cache.keySet().toArray(new Integer[0]);
        for (int i = 0; i < onlyNums.length; i++) {
            heapInset(onlyNums, i, cache);
        }

        int size = onlyNums.length - 1;
        swap(onlyNums, 0, size);
        int tempK = k;
        tempK--;
        while (tempK > 0) {
            heapify(onlyNums, 0, size, cache);
            tempK--;
            size--;
            swap(onlyNums, 0, size);
        }

        int[] ans = new int[k];
        int index = 0;
        for (int i = onlyNums.length - 1; i > onlyNums.length - 1 - k; i--) {
            ans[index] = onlyNums[i];
            index++;
        }
        return ans;
    }


    public void heapify(Integer[] nums, int index, int size, Map<Integer, Integer> cache) {
        int left = index * 2 + 1;
        while (left < size) {
            int larger = left + 1 < size && cache.get(nums[left + 1]) > cache.get(nums[left]) ? left + 1 : left;
            if (cache.get(nums[larger]) <= cache.get(nums[index])) {
                break;
            }
            swap(nums, larger, index);
            index = larger;
            left = index * 2 + 1;
        }
    }


    public void heapInset(Integer[] nums, int index, Map<Integer, Integer> cache) {
        int head = (index - 1) / 2;
        while (cache.get(nums[index]) > cache.get(nums[head])) {
            swap(nums, index, head);
            index = head;
            head = (index - 1) / 2;
        }
    }

    public void swap(Integer[] nums, int i, int j) {
        Integer temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
