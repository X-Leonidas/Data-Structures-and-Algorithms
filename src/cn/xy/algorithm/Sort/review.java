package cn.xy.algorithm.Sort;

import java.util.Arrays;
import java.util.List;

/**
 * @authorXYPC
 * @create2019-04-04-23:05
 */
public class review {
    public static void main(String[] args) {
        Integer[] array = {1, 65, 7, 8, 934, 2, 3, 4, 0};
        //随机范围
        int N = 1000;
        //数量
        int num = 1000;
        Integer[] randomArray1 = SortTestHelper.generateNearlyOrderedArray(10000, 100);
        Integer[] randowArray2 = SortTestHelper.copyArray(randomArray1);
        Integer[] array1 = SortTestHelper.getRandomArray(num, 0, N);
        Integer[] array2 = SortTestHelper.getRandomArray(num, 0, N);
        int flag = 0;
        if (flag == 0) {
            mergeSort(array1);
            SortTestHelper.printArray(array1);
        } else if (flag == 1) {
            long startTime = System.currentTimeMillis();
            bubbleSort(randomArray1);
            long endTime = System.currentTimeMillis();
            System.out.println("第一个排序" + " " + (endTime - startTime));
            long startTime1 = System.currentTimeMillis();
            bubbleSort2(randowArray2);
            long endTime1 = System.currentTimeMillis();
            System.out.println("第二个排序" + " " + (endTime1 - startTime1));
        }
    }


    public static double sumlist(List<? extends Number> list) {
        return list.stream().mapToDouble(Number::doubleValue).sum();
    }

    public static void inserSort(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    MySort.swap(arr, j, j - 1);
                } else {
                    break;
                }

            }
        }
    }


    public static void insertSort2(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int j = i;
            int temp = arr[j];
            for (; j > 0 && arr[j - 1] > temp; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = temp;
        }
    }


    public static void bubbleSort(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j + 1] < arr[j]) {
                    MySort.swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void bubbleSort2(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean flag = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j + 1] < arr[j]) {
                    MySort.swap(arr, j, j + 1);
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

    public static void sellSort(Integer[] arr) {
        int n = arr.length;
        int h = 1;
        while (h < n / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            for (int i = h; i < arr.length; i++) {
                int j = i;
                int temp = arr[i];
                for (; j >= h && arr[j - 1] > temp; j -= h) {
                    arr[j] = arr[j - 1];
                }
                arr[j] = temp;
            }
            h /= 3;
        }

    }

    public static void sellSort2(Integer[] arr) {
        int n = arr.length;
        int h = 1;
        while (h < n / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                int j = i;
                int temp = arr[j];
                for (; j >= h && arr[j - 1] > temp; j -= h) {
                    arr[j] = arr[j - 1];

                }
                arr[j] = temp;
            }
            h /= 3;
        }

    }


    public static void mergeSort(Integer[] arr) {
        int n = arr.length;
        __mergeSort(arr, 0, n - 1);
    }

    public static void __mergeSort(Integer[] arr, int l, int r) {
        if (r - l <= 15) {
            MySort.insertSort2(arr, l, r);
            return;
        }

        int mid = (l + r) / 2;
        __mergeSort(arr, l, mid);
        __mergeSort(arr, mid + 1, r);
        if (arr[mid] > arr[mid + 1]) {
            __merg(arr, l, mid, r);
        }
    }

    public static void __merg(Integer[] arr, int l, int mid, int r) {
        Integer[] temp = Arrays.copyOfRange(arr, l, r + 1);
        int i = l;
        int j = mid + 1;
        for (int k = l; k <= r; k++) {
            if (i > mid) {
                arr[k] = temp[j - l];
                j++;
            } else if (j > r) {
                arr[k] = temp[i - l];
                i++;
            } else if (temp[j - l] > temp[i - l]) {
                arr[k] = temp[i - l];
                i++;
            } else {
                arr[k] = temp[j - l];
                j++;
            }
        }
    }
}




