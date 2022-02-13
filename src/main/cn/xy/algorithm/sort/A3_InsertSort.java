package xy.algorithm.sort;

import cn.xy.utils.Utils;

/**
 * @author XiangYu
 * @create2020-11-04-0:52
 *
 *
 * 插入排序
 *
 * 时间复杂度：  O（N^2）
 *
 */
public class A3_InsertSort {
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
