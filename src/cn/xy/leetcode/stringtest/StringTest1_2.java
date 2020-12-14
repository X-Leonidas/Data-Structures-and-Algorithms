package cn.xy.leetcode.stringtest;

import java.util.*;

/**
 * @author XiangYu
 * @create2020-11-27-10:23
 * 给出一个区间的集合，请合并所有重叠的区间。
 * <p>
 * 示例 1:
 * <p>
 * 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 * <p>
 * 输入: intervals = [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * 注意：输入类型已于2019年4月15日更改。 请重置默认代码定义以获取新方法签名。
 * <p>
 *  
 * 提示：
 * <p>
 * intervals[i][0] <= intervals[i][1]
 */
public class StringTest1_2 {
    public static void main(String[] args) {

        int[][] ints = {{1,2},{3,9},{8,12}};

        int[][] merge = merge(ints);
        for (int[] ints1 : merge) {
            System.out.println(ints1[0] + "   " + ints1[1]);
        }

    }

    public static int[][] merge(int[][] intervals) {

        if(intervals.length < 1){
            return  intervals;
        }

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        ArrayList<int[]> array = new ArrayList<>();
        //设置起点，判断终点
        int i = 1;
        int start = intervals[0][0];
        int end = intervals[0][1];
        while(i < intervals.length){
            if(end >= intervals[i][0]){
                if(end < intervals[i][1]){
                    end = intervals[i][1];
                }
                i++;
            }else {
                int[] ints = {start,end};
                array.add(ints);
                start = intervals[i][0];
                end = intervals[i][1];
                i++;
            }
        }
        //最后在加一组
        int[] ints = {start,end};
        array.add(ints);


        return array.toArray(new int[0][]);
    }





    public static int[][] merge2(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        if (intervals.length == 0 || intervals == null) {
            return res.toArray(new int[0][]);
        }
        // 对起点终点进行排序
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int i = 0;
        while (i < intervals.length) {
            int left = intervals[i][0];
            int right = intervals[i][1];
            // 如果有重叠，循环判断哪个起点满足条件
            while (i < intervals.length - 1 && intervals[i + 1][0] <= right) {
                i++;
                right = Math.max(right, intervals[i][1]);
            }
            // 将现在的区间放进res里面
            res.add(new int[]{left, right});
            // 接着判断下一个区间
            i++;
        }
        return res.toArray(new int[0][]);
    }
}
