package cn.xy.leetcode.middle.array;

import java.util.*;

/**
 * @author xiangyu
 * @date 2024-03-03 23:17
 * <p>
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 */
public class A0056MergeIntervals {
    public int[][] merge(int[][] intervals) {
        HashMap<Integer, Integer> cache = new HashMap<>();
        Set<Integer> startSet = new TreeSet<>();
        for (int[] interval : intervals) {
            if (cache.containsKey(interval[0])) {
                cache.put(interval[0], Math.max(interval[1], cache.get(interval[0])));
            } else {
                cache.put(interval[0], interval[1]);
            }
            startSet.add(interval[0]);
        }
        List<int[]> resultList = new ArrayList<>();
        int[] pre = new int[0];
        for (Integer start : startSet) {
            if (resultList.isEmpty()) {
                pre = new int[]{start, cache.get(start)};
                resultList.add(pre);
            } else {
                Integer curEnd = cache.get(start);
                if (start <= pre[1]) {
                    pre[1] = Math.max(pre[1], curEnd);
                } else {
                    pre = new int[]{start, cache.get(start)};
                    resultList.add(pre);
                }
            }

        }
        return resultList.toArray(new int[0][0]);
    }


    /**
     * 不用hash set使用 Comparator 实现
     *
     */

    public int[][] merge2(int[][] intervals) {
        // 先按照区间起始位置排序
        Arrays.sort(intervals, Comparator.comparingInt(v -> v[0]));
        // 遍历区间
        int[][] res = new int[intervals.length][2];
        int idx = -1;
        for (int[] interval : intervals) {
            // 如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，
            // 则不合并，直接将当前区间加入结果数组。
            if (idx == -1 || interval[0] > res[idx][1]) {
                res[++idx] = interval;
            } else {
                // 反之将当前区间合并至结果数组的最后区间
                res[idx][1] = Math.max(res[idx][1], interval[1]);
            }
        }
        return Arrays.copyOf(res, idx + 1);
    }

}
