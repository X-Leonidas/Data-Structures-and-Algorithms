package cn.xy.utils;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author XiangYu
 * @create2020-11-04-0:50
 */
public class Utils {

    /**
     * 传入数组 生成链表
     *
     * @param list
     * @return
     */
    public static ListNode convertNode(List<Integer> list) {
        ListNode head = new ListNode(-1);
        ListNode preNode = head;
        for (Integer i : list) {
            ListNode node = new ListNode(i);
            preNode.next = node;
            preNode = node;
        }
        return head.next;
    }

    /**
     * 打印链表
     * @param node
     */
    public static void printNode(ListNode node) {
        System.out.print("head->");
        while (node != null) {
            System.out.print(node.val + "-> ");
            node = node.next;
        }
        System.out.print("end");
    }


    /**
     * 生成随机长度，随机大小的数组数组
     *
     * @param arrayRangeL 数组的最小长度
     * @param arrayRangeR 数组的最大长度
     * @param rangeL      数值的最小值
     * @param rangeR      数值的最大值
     * @return 数组
     */
    public static Integer[] getRandomSizeArray(int arrayRangeL, int arrayRangeR, int rangeL, int rangeR) {
        int arraySize = (int) ((arrayRangeR - arrayRangeL + 1) * Math.random() + arrayRangeL);
        Integer[] array = new Integer[arraySize];
        for (int i = 0; i < arraySize; i++) {
            array[i] = (int) ((rangeR - rangeL + 1) * Math.random() + rangeL);
        }
        return array;
    }


    //交换
    public static void swap(Object[] array, int i, int j) {
        Object t = array[i];
        array[i] = array[j];
        array[j] = t;
    }


    //交换
    public static void swap(int[] array, int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    /**
     * 生成一个区间为[rangeL,rangeR]，长度为n的int数组
     *
     * @param n
     * @param rangeL
     * @param rangeR
     * @return
     */
    public static Integer[] getRandomArray(int n, int rangeL, int rangeR) {

        Integer[] array = new Integer[n];
        for (int i = 0; i < n; i++) {
            array[i] = (int) ((rangeR - rangeL + 1) * Math.random() + rangeL);
        }
        return array;
    }

    /**
     * 打印数组
     *
     * @param arr
     */
    public static void printArray(Object[] arr) {

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            System.out.print(' ');
        }
        System.out.println();
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            System.out.print(' ');
        }
        System.out.println();
    }


    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    /**
     * 生成一个近乎有序的数组
     * 首先生成一个含有[0...n-1]的完全有序数组, 之后随机交换swapTimes对数据
     * swapTimes定义了数组的无序程度:
     * swapTimes == 0 时, 数组完全有序
     * swapTimes 越大, 数组越趋向于无序
     */
    public static Integer[] generateNearlyOrderedArray(int n, int swapTimes) {
        //先生成一个有序数组
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        //随机交换
        for (int i = 0; i < swapTimes; i++) {
            int a = (int) (Math.random() * n);
            int b = (int) (Math.random() * n);
            int t = arr[a];
            arr[a] = arr[b];
            arr[b] = t;
        }
        return arr;
    }


    //判断数组是否有序
    public static boolean isSorted(Comparable[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].compareTo(arr[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    //复制数组
    public static int[] copyArray(int[] array) {
        int n = array.length;
        int[] array1 = new int[n];
        for (int i = 0; i < n; i++) {
            array1[i] = array[i];
        }

        return array1;
    }


    public static void testSort(String sortClassName, Comparable[] arr, String sortname) {
        try {
            //通过sortClassName获得排序函数的Class对象
            Class sortClass = Class.forName(sortClassName);
            // 通过排序函数的Class对象获得排序方法
            Method sort = sortClass.getMethod(sortname, new Class[]{Comparable[].class});
            // 排序参数只有一个，是可比较数组arr
            Object[] params = new Object[]{arr};

            long startTime = System.currentTimeMillis();

            // 调用排序函数
            sort.invoke(null, params);

            long endTime = System.currentTimeMillis();

            assert isSorted(arr);

            System.out.println(sortname + ":" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }
}