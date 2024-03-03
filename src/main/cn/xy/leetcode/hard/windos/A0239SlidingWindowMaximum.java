package cn.xy.leetcode.hard.windos;

import java.util.*;

/**
 * @author xiangyu
 * @date 2024-03-01 23:19
 * <p>
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * <p>
 * 返回 滑动窗口中的最大值 。
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * 1 <= k <= nums.length
 */
public class A0239SlidingWindowMaximum {

    public static void main(String[] args) {
        int[] ints = new int[]{1,3,1,2,0,5};
        System.out.println(Arrays.toString(new A0239SlidingWindowMaximum().maxSlidingWindow(ints, 3)));
    }

    /**
     * 用单调栈无法解决后面来一个比较小的问题
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        Stack<Integer> maxStack = new Stack<>();
        HashMap<Integer, Integer> cache = new HashMap<>();
        for (int i = 0; i < k; i++) {
            if (cache.containsKey(nums[i])) {
                cache.put(nums[i], cache.get(nums[i]) + 1);
            } else {
                cache.put(nums[i], 1);
            }
        }

        maxStack.push(Integer.MIN_VALUE);
        cache.put(Integer.MIN_VALUE, 1);
        reBuildStack(nums, 0, k, maxStack);
        int start = 0;
        ans[start] = maxStack.peek();

        for (int end = k; end < nums.length; end++) {
            // 去掉start的
            cache.put(nums[start], cache.get(nums[start]) - 1);
            // 加上end的计数
            if (cache.containsKey(nums[end])) {
                cache.put(nums[end], cache.get(nums[end]) + 1);
            } else {
                cache.put(nums[end], 1);
            }
            start++;
            while (!cache.containsKey(maxStack.peek()) || cache.get(maxStack.peek()) == 0) {
                maxStack.pop();
            }
            if (maxStack.peek() == Integer.MIN_VALUE) {
                reBuildStack(nums, start, end, maxStack);
            }

            if (nums[end] >= maxStack.peek()) {
                maxStack.push(nums[end]);
            }
            ans[start] = maxStack.peek();
        }
        return ans;
    }


    private void reBuildStack(int[] nums, int start, int end, Stack<Integer> maxStack) {
        for (int i = start; i < end; i++) {
            if (nums[i] >= maxStack.peek()) {
                maxStack.push(nums[i]);
            }
        }
    }

    /**
     * 如果当前的滑动窗口中有两个下标 i 和 j，其中 i 在 j 的左侧（i < j）
     * 当滑动窗口向右移动时，只要 i 还在窗口中，那么 j 一定也还在窗口中，
     * 这是 i 在 j 的左侧所保证的。因此，由于 nums[j]的存在，nums[i] 一定不会是滑动窗口中的最大值了，
     * 我们可以将 nums[i]永久地移除。

     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; ++i) {
            // deque.peekLast() 存在的话 num[i] 一定存在，
            // 所以num[i]大的话可以直接丢掉deque.peekLast(),小的话则需要加入队列中
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }

        int[] ans = new int[n - k + 1];
        ans[0] = nums[deque.peekFirst()];
        for (int i = k; i < n; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            // 最大值已经滑过的话，也要丢掉
            while (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }

    /**
     * 大根堆
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow3(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (pair1, pair2) -> pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1]);
        for (int i = 0; i < k; ++i) {
            pq.offer(new int[]{nums[i], i});
        }
        int[] ans = new int[n - k + 1];
        ans[0] = pq.peek()[0];
        for (int i = k; i < n; ++i) {
            pq.offer(new int[]{nums[i], i});
            // 已经划过去掉
            while (pq.peek()[1] <= i - k) {
                pq.poll();
            }
            ans[i - k + 1] = pq.peek()[0];
        }
        return ans;
    }

}
