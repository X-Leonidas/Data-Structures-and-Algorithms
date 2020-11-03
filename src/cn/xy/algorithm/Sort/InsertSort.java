package cn.xy.algorithm.Sort;

import cn.xy.algorithm.Utils;

/**
 * @author XiangYu
 * @create2020-11-04-0:52
 */
public class InsertSort {
    /**
     * 插入排序
     *
     * @param array
     */
    public static void insertSort(Integer[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                if (array[j] < array[j - 1]) {
                    Utils.swap(array, j, j - 1);
                } else {
                    break;
                }
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
