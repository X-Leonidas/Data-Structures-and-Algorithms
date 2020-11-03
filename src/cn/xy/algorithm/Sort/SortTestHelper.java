package cn.xy.algorithm.Sort;

import java.lang.reflect.Method;

public class SortTestHelper {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            // int[] array = getRandomArray(1000, 1, 1000);
            // Arrays.sort(array);
            //System.out.println(array.length);
            // for(int n:array){
            //    System.out.print("____"+n);
            // }
            // System.out.println();
        }


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
    public static void printArray(Object arr[]) {

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            System.out.print(' ');
        }
        System.out.println();

        return;
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
            arr[i] = new Integer(i);
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
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].compareTo(arr[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    //
    public static Integer[] copyArray(Integer[] array) {
        int n = array.length;
        Integer[] array1 = new Integer[n];
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
}
