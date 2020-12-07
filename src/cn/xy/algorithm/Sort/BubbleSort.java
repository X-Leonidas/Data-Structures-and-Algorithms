package cn.xy.algorithm.Sort;

import cn.xy.utils.Utils;

/**
 * @author XiangYu
 * @create2020-11-04-0:49
 * 冒泡排序
 */
public class BubbleSort {
    /**
     * 冒泡排序
     *
     * @param array
     */
    public static void bubbleSort(Comparable[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    Utils.swap(array, j, j + 1);
                }
            }
        }
    }

    /**
     * 冒泡排序改进,加入一个flag标志
     *
     * @param array
     */
    public static void bubbleSort2(Comparable[] array) {
        boolean flag = false;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    Utils.swap(array, j, j + 1);
                    flag = true;
                }
            }
            if (flag == false) {
                break;
            } else {
                flag = false;
            }
        }
    }

    /**
     * 冒泡排序
     * github上的写法
     *
     * @param array
     */
    public static void bubbleSort3(Comparable[] array) {
        boolean flag;
        int n = array.length;
        do {
            //每次循环都要记得留出口
            flag = false;
            for (int i = 0; i < n - 1; i++) {
                if (array[i].compareTo(array[i + 1]) > 0) {
                    Utils.swap(array, i, i + 1);
                    flag = true;
                }
            }
            n--;
        } while (flag);

    }

    public static void bubbleSort4(Comparable[] array) {

        int n = array.length;
        int newn;
        do {
            newn = 0;
            for (int i = 0; i < n - 1; i++) {
                if (array[i].compareTo(array[i + 1]) > 0) {
                    Utils.swap(array, i, i + 1);
                    newn = i + 1;
                }
            }
            n = newn;
        } while (newn > 0);
    }
}
