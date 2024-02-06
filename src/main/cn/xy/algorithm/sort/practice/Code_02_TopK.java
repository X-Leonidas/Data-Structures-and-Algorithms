package cn.xy.algorithm.sort.practice;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * @author XiangYu
 * @create2021-04-27-21:01
 * 给定一个数组，找出其中最小的K个数。例如数组元素是4,5,1,6,2,7,3,8这8个数字，
 * 则最小的4个数字是1,2,3,4。如果K>数组的长度，那么返回一个空的数组
 * 示例1
 * 输入
 * 复制
 * [4,5,1,6,2,7,3,8],4
 * 返回值
 * 复制
 * [1,2,3,4]
 */
public class Code_02_TopK {
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        int length = input.length;
        if(k > input.length){
            return new ArrayList<>();
        }
        ArrayList<Integer> list = new ArrayList<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();

        for (int i = 0; i < length; i++) {
            minHeap.add(input[i]);
        }

        for (int i = 0; i < k; i++) {
            list.add(minHeap.poll());
        }

        return  list;
    }
}
