package cn.xy.leetcode.easy.array;

import java.util.*;

/**
 * @author XiangYu
 * @create2020-11-02-10:13
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 *
 *
 */
public class A0349IntersectionOfTwoArrays {
    public static void main(String[] args) {
        int[] nums1 = {4,9,5};
        int[] nums2 = {9,4,9,8,4};
        for (int i : intersection2(nums1, nums2)) {
            System.out.println(i);
        }
    }

    /**
     * 暴力破解
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> unionSet = new HashSet<>();

        for (int num1 : nums1) {
            for (int num2 : nums2) {
                if (num1 == num2) {
                    unionSet.add(num1);
                    break;
                }
            }
        }

        int[] ints = new int[unionSet.size()];
        int i = 0;
        for (Integer integer : unionSet) {
            ints[i] = integer;
            i++;
        }

        return  ints;
    }

    /**
     * list与set的交集判断耗费了时间
     * 以及stream流的操作耗费了时间
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersection2(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();

        List<Integer> list = new ArrayList<>();
        for(int i:nums1){
            set1.add(i);
        }
        for(int i:nums2){
            list.add(i);
        }
        set1.retainAll(list);
        return set1.stream().mapToInt(i->i).toArray();
    }


    /**
     * 使用两个set
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersection3(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();

        Set<Integer> set2 = new HashSet<>();
        for(int i:nums1){
            set1.add(i);
        }
        for(int i:nums2){
            set2.add(i);
        }
        set1.retainAll(set2);

        int[] result = new int[set1.size()];
        int start = 0;
        for (int item : set1) {
            result[start] = item;
            start++;
        }
        return result;
    }

    /**
     * 使用一个set
     */
    public static int[] intersection4(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        for(int i:nums1){
            set1.add(i);
        }
        for(int i:nums2){
            if(set1.contains(i)){
               set2.add(i);
            }
        }

        int[] result = new int[set2.size()];
        int start = 0;
        for (int item : set2) {
            result[start] = item;
            start++;
        }
        return result;
    }



    /**
     * 使用一个map
     */
    public static int[] intersection5(int[] nums1, int[] nums2) {
        Map<Integer,Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            map.put(nums1[i],1);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (map.getOrDefault(nums2[i],0)==1){
                set.add(nums2[i]);
            }

        }
        int[] result = new int[set.size()];
        int i=0;
        for (Integer integer : set) {
            result[i++]=integer;
        }
        return result;
    }





}


