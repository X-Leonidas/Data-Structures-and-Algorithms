package cn.xy.leetcode.easy.array;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.util.*;

/**
 * @author XiangYu
 * @create2021-02-22-10:39
 *
 * 给定一个非空且只包含非负数的整数数组 nums，数组的度的定义是指数组里任一元素出现频数的最大值。
 *
 * 你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[1, 2, 2, 3, 1]
 * 输出：2
 * 解释：
 * 输入数组的度是2，因为元素1和2的出现频数最大，均为2.
 * 连续子数组里面拥有相同度的有如下所示:
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * 最短连续子数组[2, 2]的长度为2，所以返回2.
 * 示例 2：
 *
 * 输入：[1,2,2,3,1,4,2]
 * 输出：6
 *  
 *
 * 提示：
 *
 * nums.length 在1到 50,000 区间范围内。
 * nums[i] 是一个在 0 到 49,999 范围内的整数。
 *
 */
public class A0697DegreeOfAnArray {


    public static void main(String[] args) {


        int[] a ={1,3,2,2,3,1};
        findShortestSubArray(a);
    }

    public static int findShortestSubArray(int[] nums) {

        //满足度的定义的最大值
        int[] ints = Arrays.copyOf(nums, nums.length);
        Arrays.sort(ints);
        int max = ints[0];
        int sum = 1;


        for (int i = 0; i < ints.length-1; i++) {
           int tempSum = 1;
           while (i < ints.length-1 && ints[i] == ints[i+1]){
                i++;
                tempSum++;
            }
           if(tempSum >= sum ) {
               sum = tempSum;
               max = Math.max(max, ints[i]);
           }
        }

        boolean flag = false;
        int length = 0;

        for (int i = 0; i < nums.length; i++) {


            if(flag){
                length++;
                if(nums[i] == max){
                    sum--;
                }
            } else if(!flag && nums[i] == max){
                //第一次遇到
                flag = true;
                length = 1;
                sum--;
            }

            if(sum==0){
                return length;
            }


        }

        return 1;

    }

    public int findShortestSubArray2(int[] nums) {
        int n = nums.length;
        if(n == 1){
            return 1;
        }

        Map<Integer,List<Integer>> map = new HashMap<>();
        for(int i=0;i<n;i++){
            List<Integer> indexList = map.getOrDefault(nums[i],new ArrayList<>());
            indexList.add(i);
            map.put(nums[i],indexList);
        }

        Set<Map.Entry<Integer, List<Integer>>> entrySet = map.entrySet();
        List<Map.Entry<Integer,List<Integer>>> list = new ArrayList<>(entrySet);
        Collections.sort(list, new Comparator<Map.Entry<Integer,List<Integer>>>(){
            @Override
            public int compare(Map.Entry<Integer,List<Integer>> o1, Map.Entry<Integer,List<Integer>> o2) {
                // 先按度升序排列
                int size1 = o1.getValue().size(), size2 = o2.getValue().size();
                if(size1 != size2){
                    return size2-size1;
                }

                // 再按子串长度从小到大排
                int len1 = o1.getValue().get(size1-1)-o1.getValue().get(0)+1;
                int len2 = o2.getValue().get(size2-1)-o2.getValue().get(0)+1;

                return len1 - len2;
            }

        });

        // list第一个元素就是答案
        int tempSize = list.get(0).getValue().size();
        return list.get(0).getValue().get(tempSize-1) - list.get(0).getValue().get(0)+1;
    }

}
