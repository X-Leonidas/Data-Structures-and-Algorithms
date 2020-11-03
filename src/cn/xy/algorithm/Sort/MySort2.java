package cn.xy.algorithm.Sort;

import java.util.Arrays;

;

/**
 * @authorXYPC
 * @create2019-03-27-22:18
 */
public class MySort2 {
    /**
     * 算法类不允许产生实例
     */
    private MySort2() {
    }

    /**
     * 归并排序
     * <p>
     * 是利用归并的思想实现的排序方法，该算法采用经典的分治（divide-and-conquer）策略
     * （分治法将问题分(divide)成一些小的问题然后递归求解，而治(conquer)的阶段则将分的阶段得到的各答案"修补"在一起，即分而治之)。
     *
     * @param array
     */
    public static void mergeSort(Integer[] array) {
        int n = array.length;
        __mergeSort(array, 0, n - 1);
    }

    /**
     * 数组，知道数组的元素数为1
     */

    public static void __mergeSort(Integer[] array, Integer l, Integer r) {
        //优化，当数组内的元素足够小时，使用插入排序
        if (r - l <= 15) {
            MySort.insertSort2(array, l, r);
            return;
        }

        //获取中间数
        int mid = (l + r) / 2;
        __mergeSort(array, l, mid);
        __mergeSort(array, mid + 1, r);
        //对归并排序的改进
        if (array[mid] > array[mid + 1]) {
            __merge(array, l, mid, r);
        }

    }

    /**
     * 自底向上的归并排序
     *
     * @param array
     */

    public static void mergeSortBU(Integer[] array) {
        for (int sz = 1; sz < array.length; sz += sz) {
            for (int i = 0; i + sz < array.length; i += sz + sz) {
                __merge(array, i, i + sz - 1, Math.min(i + sz + sz - 1, array.length - 1));
            }
        }
    }


    /**
     * 合并数组
     *
     * @param array
     * @param l     左边界
     * @param mid   中间值
     * @param r     右边界
     */
    public static void __merge(Integer[] array, int l, int mid, int r) {
        //copyOfRange(arr,a,b)从数组arr下标a复制到b，但是不包括下标b的元素
        Integer[] temp = Arrays.copyOfRange(array, l, r + 1);
        int j = mid + 1;
        int i = l;
        for (int k = l; k <= r; k++) {
            if (i > mid) {
                array[k] = temp[j - l];
                j++;
            } else if (j > r) {
                array[k] = temp[i - l];
                i++;
            } else if (temp[i - l] > temp[j - l]) {
                array[k] = temp[j - l];
                j++;
            } else {
                array[k] = temp[i - l];
                i++;
            }
        }
    }


    /**
     * 快速排序
     * Quick Sort也是一个O(nlogn)复杂度的算法
     * 可以在1秒之内轻松处理100万数量级的数据
     */
    public static void quickSort(Integer[] array) {
        int n = array.length;
        __quickSort(array, 0, n - 1);
    }

    /**
     * 对arr[l...r]进行快速排序
     *
     * @param array
     * @param l
     * @param r
     */
    public static void __quickSort(Integer[] array, int l, int r) {
        if (l >= r) {
            return;
        }
        Integer p = __partition(array, l, r);
        __quickSort(array, l, p - 1);
        __quickSort(array, p + 1, r);
    }

    /**
     * 对[l...r]部分进行partition操作
     * 返回p,使得arr[l...p-1] < arr[p] < arr[p+1,l]
     *
     * @param array
     * @param l
     * @param r
     * @return
     */
    public static Integer __partition(Integer[] array, int l, int r) {
        Integer v = array[l];
        Integer j = l;
        //arr[l+1...j]<v,arr[j+1...i)>v  此处为何为前闭后开，是因为i是当前要考察的元素
        //此处是保证arr[l+1...j],arr[j+1,i)初始值均为空
        for (int i = l + 1; i <= r; i++) {
            if (array[i] < v) {
                swap(array, j + 1, i);
                j++;
            }
        }
        //第一次写的时候忘记考虑排完后，将比较元素与节点标志元素交换
        swap(array, l, j);
        return j;
    }

    public static void main(String[] args) {
        //随机范围
        int N = 500000;
        //数量
        int num = 50000;
        Integer[] randomArray1 = SortTestHelper.generateNearlyOrderedArray(num, 1000);
        Integer[] randowArray2 = SortTestHelper.copyArray(randomArray1);
        Integer[] array1 = SortTestHelper.getRandomArray(num, 0, N);
        Integer[] array2 = SortTestHelper.getRandomArray(num, 0, N);
        int flag = 0;
        if (flag == 0) {
            mergeSort(randomArray1);
            SortTestHelper.printArray(randomArray1);
        } else if (flag == 1) {
            long startTime = System.currentTimeMillis();
            mergeSort(randomArray1);
            long endTime = System.currentTimeMillis();
            System.out.println("第一个排序" + " " + (endTime - startTime));
            long startTime1 = System.currentTimeMillis();
            MySort.insertSort2(randowArray2);
            long endTime1 = System.currentTimeMillis();
            System.out.println("第二个排序" + " " + (endTime1 - startTime1));
        }
    }


    public static void swap(Object[] array, int i, int j) {
        Object t = array[i];
        array[i] = array[j];
        array[j] = t;
    }
}
