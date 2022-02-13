package xy.algorithm.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author XiangYu
 * @create2021-02-27-22:41 堆排序
 * <p>
 * 时间复杂度  O(N * logN)  空间复杂度 O(1)
 *
 *
 * 堆  优先级队列
 * <p>
 * <p>
 * 完全二叉树的父节点函数为      (n-1)/2    节点高度为Log N
 * 左子节点：  2n+1
 * 右子节点：  2n+2
 * <p>
 * <p>
 * <p>
 * 堆就是一个完全二叉树
 * 大根堆：在完全二叉树中任何一个子树的最大值都是这个子树的头部
 * 小根堆：在完全二叉树中任何一个子树的最小值都是这个子树的头部
 *
 *
 * 建立一个大根堆的时间复杂度为  O(N)
 *
 *      大根堆收集较小的  2/N个
 *      小根堆收集较大的  2/N个
 *
 *   堆排序中：
 *      将大根堆中的最大值与该树末尾（数组末尾）交换，获得当前的最大值
 *
 *
 *   思路：建立大根堆，将堆顶元素也就是最大元素与二叉树最后一个值进行交换，将最大值脱离数组
 *         调整脱离最大元素后的大小为N-1大根堆，使其成为大根堆
 *         依次进行上述过程，直到大根堆大小为0
 *
 */
public class B3_HeapSort {
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            //从0-i 生成大根堆
            heapInsert(arr, i);
        }

        int size = arr.length;
        //最后一个数和头节点交换  大根堆size-1
        swap(arr, 0, --size);
        while (size > 0) {
            heapify(arr, 0, size);
            swap(arr, 0, --size);
        }
    }


    /**
     * 将一个数组变成大根堆
     * @param arr
     * @param index
     */

    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    /**
     * 大根堆中某一个数变小后，重新调整为大根堆的操作
     * @param arr
     * @param index
     * @param size   大根堆的大小
     */
    public static void heapify(int[] arr, int index, int size) {
        int left = index * 2 + 1;
        while (left < size) {
            int largest = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    //PriorityQueue  默认是小根堆
    //大根堆实现传入一个比较器
    public void bigHeapWithProorityQueue(){
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(11, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }

        });
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
            heapSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        heapSort(arr);
        printArray(arr);
    }
}
