package cn.xy.leetcode.hard.heap;

import java.util.*;

/**
 * @author xiangyu
 * @date 2024-03-22 23:55
 * <p>
 * 中位数是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。
 * <p>
 * 例如 arr = [2,3,4] 的中位数是 3 。
 * 例如 arr = [2,3] 的中位数是 (2 + 3) / 2 = 2.5 。
 * 实现 MedianFinder 类:
 * <p>
 * MedianFinder() 初始化 MedianFinder 对象。
 * <p>
 * void addNum(int num) 将数据流中的整数 num 添加到数据结构中。
 * <p>
 * double findMedian() 返回到目前为止所有元素的中位数。与实际答案相差 10-5 以内的答案将被接受。
 * <p>
 * 示例 1：
 * <p>
 * 输入
 * ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
 * [[], [1], [2], [], [3], []]
 * 输出
 * [null, null, null, 1.5, null, 2.0]
 * <p>
 * 解释
 * MedianFinder medianFinder = new MedianFinder();
 * medianFinder.addNum(1);    // arr = [1]
 * medianFinder.addNum(2);    // arr = [1, 2]
 * medianFinder.findMedian(); // 返回 1.5 ((1 + 2) / 2)
 * medianFinder.addNum(3);    // arr[1, 2, 3]
 * medianFinder.findMedian(); // return 2.0
 * 提示:
 * <p>
 * -105 <= num <= 105
 * 在调用 findMedian 之前，数据结构中至少有一个元素
 * 最多 5 * 104 次调用 addNum 和 findMedian
 */
public class A0295FindMedianFromDataStream {
    // 维护一个大根堆和一个小根堆，先加大根堆，如果两个size是奇数，则直接弹出值，如果是偶数则最小值和最大值取平均数


    class MedianFinder {
        // 大根堆
        List<Integer> bigHeap;
        // 小根堆
        List<Integer> smallHeap;

        public MedianFinder() {
            bigHeap = new ArrayList<>();
            smallHeap = new ArrayList<>();
        }

        public void addNum(int num) {
            if (bigHeap.size() == 0 || bigHeap.get(0) > num) {
                bigHeap.add(num);
                insertBigHeap(bigHeap);
                if (smallHeap.size() + 1 < bigHeap.size()) {
                    Integer temp = bigHeap.get(0);
                    // 弹出一个给 small
                    swap(bigHeap, 0, bigHeap.size() - 1);
                    bigHeap.remove(bigHeap.size() - 1);
                    heapifyBigHeap(bigHeap);
                    smallHeap.add(temp);
                    insertSmallHeap(smallHeap);
                }
            } else {
                smallHeap.add(num);
                insertSmallHeap(smallHeap);
                if (smallHeap.size() > bigHeap.size()) {
                    Integer temp = smallHeap.get(0);
                    // 弹出一个给 small
                    swap(smallHeap, 0, smallHeap.size() - 1);
                    smallHeap.remove(smallHeap.size() - 1);
                    heapifySmaillHeap(smallHeap);
                    bigHeap.add(temp);
                    insertBigHeap(bigHeap);
                }
            }
        }

        public double findMedian() {
            if ((bigHeap.size() + smallHeap.size()) % 2 == 1) {
                return bigHeap.get(0);
            } else {
                return (bigHeap.get(0) + smallHeap.get(0)) / (double) 2;
            }
        }

        private void insertBigHeap(List<Integer> heap) {
            int index = heap.size() - 1;
            int root = (index - 1) / 2;
            while (heap.get(root) < heap.get(index)) {
                swap(heap, root, index);
                index = root;
                root = (index - 1) / 2;
            }
        }


        private void heapifyBigHeap(List<Integer> heap) {
            int root = 0;
            int left = root * 2 + 1;
            while (left < heap.size()) {
                int larger = left + 1 < heap.size() && heap.get(left + 1) > heap.get(left) ? left + 1 : left;
                if (heap.get(larger) <= heap.get(root)) {
                    break;
                }
                swap(heap, root, larger);
                root = larger;
                left = root * 2 + 1;
            }
        }


        private void heapifySmaillHeap(List<Integer> heap) {
            int root = 0;
            int left = root * 2 + 1;
            while (left < heap.size()) {
                int larger = left + 1 < heap.size() && heap.get(left + 1) < heap.get(left) ? left + 1 : left;
                if (heap.get(larger) >= heap.get(root)) {
                    break;
                }
                swap(heap, root, larger);
                root = larger;
                left = root * 2 + 1;
            }
        }

        private void insertSmallHeap(List<Integer> heap) {
            int index = heap.size() - 1;
            int root = (index - 1) / 2;
            while (heap.get(root) > heap.get(index)) {
                swap(heap, root, index);
                index = root;
                root = (index - 1) / 2;
            }
        }

        private void swap(List<Integer> heap, int i, int j) {
            Integer temp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, temp);
        }

    }

    class MedianFinder2 {
        PriorityQueue<Integer> queMin;
        PriorityQueue<Integer> queMax;

        public MedianFinder2() {
            queMin = new PriorityQueue<Integer>((a, b) -> (b - a));
            queMax = new PriorityQueue<Integer>((a, b) -> (a - b));
        }

        public void addNum(int num) {
            if (queMin.isEmpty() || num <= queMin.peek()) {
                queMin.offer(num);
                if (queMax.size() + 1 < queMin.size()) {
                    queMax.offer(queMin.poll());
                }
            } else {
                queMax.offer(num);
                if (queMax.size() > queMin.size()) {
                    queMin.offer(queMax.poll());
                }
            }
        }

        public double findMedian() {
            if (queMin.size() > queMax.size()) {
                return queMin.peek();
            }
            return (queMin.peek() + queMax.peek()) / 2.0;
        }
    }
}
