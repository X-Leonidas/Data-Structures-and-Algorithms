package cn.xy.algorithm.Sort;

import cn.xy.algorithm.Utils;

/**
 * @author XiangYu
 * @create2020-11-04-0:52
 */
public class SelectSort {
    /**
     * 选择排序
     *
     * @param array
     * @return
     */
    public static void selectSort(int[] array) {
        for (int n = 0; n < array.length; n++) {
            int max = array[n];
            for (int m = n + 1; m < array.length; m++) {
                if (array[m] > max) {
                    array[n] = array[m];
                    array[m] = max;
                }
            }
        }
    }

    /**
     * 泛型选择排序的实现
     */
    public static void selectSort(Comparable[] array) {
        for (int n = 0; n < array.length; n++) {
            int maxIndex = n;
            for (int m = n + 1; m < array.length; m++) {
                if (array[m].compareTo(array[maxIndex]) < 0) {
                    maxIndex = m;
                    Utils.swap(array, maxIndex, m);
                }
            }
        }
    }
}
