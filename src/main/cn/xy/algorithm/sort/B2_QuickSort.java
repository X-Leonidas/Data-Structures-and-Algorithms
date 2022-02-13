package xy.algorithm.sort;

import java.util.Arrays;

/**
 * @author XiangYu
 * @create2021-02-27-21:45
 *
 *
 *
 *
 *
 * 基础  ：  荷兰国旗问题
 *  经典快排的问题：当数据出现例如 1，2，3，4，5，6，7的情况时，因为每次都只能排序一个元素
 *  复杂度会上升到 O(N^2)
 *
 *  优化版：使用随机快排  O(N  * logN)
 *
 *    思路：随机选择一个数M,小于M的放在左边，大于M的放在右边，等于M的放在中间
 *              1 具体过程，先随机选择一个数N，与数组末尾的数进行交换
 *              2 设计一个小于等于区间，从左到右依次遍历数组，index小于等于N，则将小于等于区间的下一个数
 *                 与index交换并且小于等于区间向右扩张一位，直到遍历完除N外的所有数
 *              3 将N与小于等于区间的下一位进行交换
 *              4 这就是一次完整的partition的过程
 *         接下来对左右两个部分递归调用上面的排序过程
 *
 * 快速排序
 */
public class B2_QuickSort {
    /**
     * 快排
     */
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    /**
     *
     * @param arr
     * @param l  左区间
     * @param r  右区间
     */
    public static void quickSort(int[] arr, int l, int r) {
        if (l < r) {
            //随机将一个数换到末尾
            swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
            int[] p = partition(arr, l, r);

            //从等于num的数组下标左侧开始
            quickSort(arr, l, p[0] - 1);

            //从等于num的数组下标右侧开始
            quickSort(arr, p[1] + 1, r);
        }
    }

    /**
     * 分组过程，将最后一个作为num，小于num的在数组左边，等于num的在中间，大于num的在右边
     * @param arr
     * @param l
     * @param r
     * @return
     */
    public static int[] partition(int[] arr, int l, int r) {
        //左指针
        int less = l - 1;
        //右指针
        int more = r;
        while (l < more) {
            if (arr[l] < arr[r]) {
                //l与less的下一个交换，l进1
                swap(arr, ++less, l++);
            } else if (arr[l] > arr[r]) {
                //l与more的下一个交换，l不变
                swap(arr, --more, l);
            } else {
                //与r相等的直接++
                l++;
            }
        }
        //将末尾的num移动到中间
        swap(arr, more, r);
        //返回等于num的数组下标
        return new int[] { less + 1, more };
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
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

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            quickSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        quickSort(arr);
        printArray(arr);

    }

}
