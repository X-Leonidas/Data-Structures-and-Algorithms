package cn.xy.algorithm.sort;

import cn.xy.utils.Utils;

/**
 * @author XiangYu
 * @create2020-11-04-0:52 插入排序
 * <p>
 * 时间复杂度：  O（N^2）
 */
public class A3_InsertSort {


    public static void main(String[] args) {
        Integer[] randomArray = Utils.getRandomArray(10, 1, 1000);
        Utils.printArray(randomArray);
        insertSort(randomArray);
        Utils.printArray(randomArray);
        boolean sorted = Utils.isSorted(randomArray);
        if (sorted) {
            System.out.println("Success");
        } else {
            System.out.println("fuck");
        }
    }

    /**
     * 插入排序
     *
     * @param arr
     */
    public static void insertSort(Integer[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                Utils.swap(arr, j, j + 1);
            }
        }
    }

    public static void sort(int[] arr) throws Exception {
        for (int i = 1; i < arr.length; i++) {
            // 记录要插入的数据
            int tmp = arr[i];
            // 从已经排序的序列最右边的开始比较，找到比其小的数
            int j = i;
            while (j > 0 && tmp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            // 存在比其小的数，插入
            if (j != i) {
                arr[j] = tmp;
            }
        }
    }


//    public static void insertSort2(int[] arr){
//        if(arr == null  || arr.length < 2){
//            return;
//        }
//        for (int i = 0; i < arr.length; i++) {
//            for(int j = i + 1)
//        }
//    }

    /**
     * 插入排序的改写，为了在归并排序中使用
     */

    public static void insertSort2(Integer[] array, int l, int r) {

        for (int i = l + 1; i < r; i++) {
            Integer temp = array[i];
            int j;
            for (j = i; j > 0 && array[j - 1] > temp; j--) {
                array[j] = array[j - 1];
            }
            array[j] = temp;
        }
    }

    /**
     * 插入排序优化赋值过程
     *
     * @param array
     */
    public static void insertSort2(Integer[] array) {
        for (int i = 1; i < array.length; i++) {
            Integer temp = array[i];
            int j;
            for (j = i; j > 0 && array[j - 1] > temp; j--) {
                array[j] = array[j - 1];
            }
            array[j] = temp;
        }
    }
}