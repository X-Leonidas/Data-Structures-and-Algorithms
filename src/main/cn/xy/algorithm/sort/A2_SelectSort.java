package cn.xy.algorithm.sort;

import cn.xy.utils.Utils;

/**
 * @author XiangYu
 * @create2020-11-04-0:52
 */
public class A2_SelectSort {


    public static void main(String[] args) {
//        Integer[] randomArray = Utils.getRandomArray(100, 1, 1000);
        Integer[] randomArray = new Integer[]{99,22,11,66,77};
        selectSort(randomArray);

        Utils.printArray(randomArray);
        boolean sorted = Utils.isSorted(randomArray);
        if (sorted) {
            System.out.println("Success");
        } else {
            System.out.println("fuck");
        }
    }

    /**
     * 选择排序
     *
     * @param array
     * @return
     */
    public static void selectSort(Integer[] array) {
        for (int n = 0; n < array.length; n++) {
            for (int m = n + 1; m < array.length; m++) {
                if (array[m] < array[n]) {
                    int min  = array[n];
                    array[n] = array[m];
                    array[m] = min;
                }
            }
        }
    }
}