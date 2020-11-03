package cn.xy.algorithm.Sort;


/**
 * @author xiangyu
 * 线性数据结构：排序
 */
public class MySort {
    /**
     * 算法类不允许产生实例
     */
    private MySort() {
    }

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
                    swap(array, maxIndex, m);
                }
            }
        }
    }

    /**
     * 插入排序
     *
     * @param array
     */
    public static void insertSort(Integer[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                if (array[j] < array[j - 1]) {
                    swap(array, j, j - 1);
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

    /**
     * 希尔排序
     * 缩小增量排序
     * ( 1）希尔排序（shell sort）这个排序方法又称为缩小增量排序，是1959年D·L·Shell提出来的。
     * 该方法的基本思想是：设待排序元素序列有n个元素，首先取一个整数increment（小于n）作为间隔将全部元素分为increment个子序列，
     * 所有距离为increment的元素放在同一个子序列中，在每一个子序列中分别实行直接插入排序。然后缩小间隔increment，重复上述子序列划分和排序工作。
     * 直到最后取increment=1，将所有元素放在同一个子序列中排序为止。
     * （2）由于开始时，increment的取值较大，每个子序列中的元素较少，排序速度较快，到排序后期increment取值逐渐变小，子序列中元素个数逐渐增多，
     * 但由于前面工作的基础，大多数元素已经基本有序，所以排序速度仍然很快。
     * 第一趟取increment的方法是：n/3向下取整+1=3（关于increment的取法之后会有介绍）
     * 希尔排序比插入排序快的原因是它可以将逆序数量减少大于1
     */
    public static void shellSort(Integer[] array) {
        //计算increment:1, 4, 13, 40, 121, 364, 1093...
        int n = array.length;
        int h = 1;
        while (h < n / 3) {
            h = 3 * h + 1;
        }
        //int increment = h;
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                int temp = array[i];
                int j = i;
                for (; j >= h && temp < array[j - h]; j -= h) {
                    array[j] = array[j - h];
                }
                array[j] = temp;
            }
            h /= 3;
        }
    }

    /**
     * 冒泡排序
     *
     * @param array
     */
    public static void bubbleSort(Comparable[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    swap(array, j, j + 1);
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
                    swap(array, j, j + 1);
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
                    swap(array, i, i + 1);
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
                    swap(array, i, i + 1);
                    newn = i + 1;
                }
            }
            n = newn;
        } while (newn > 0);
    }

    public static void swap(Object[] array, int i, int j) {
        Object t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    public static void main(String[] args) {
        Integer[] array = {1, 65, 7, 8, 934, 2, 3, 4, 0};
        //随机范围
        int N = 500000;
        //数量
        int num = 10000;
        Integer[] randomArray1 = SortTestHelper.generateNearlyOrderedArray(num, 10000);
        Integer[] randowArray2 = SortTestHelper.copyArray(randomArray1);
        Integer[] array1 = SortTestHelper.getRandomArray(num, 0, N);
        Integer[] array2 = SortTestHelper.getRandomArray(num, 0, N);
        int flag = 1;
        if (flag == 0) {
            shellSort(array1);
            SortTestHelper.printArray(array1);
        } else if (flag == 1) {
            long startTime = System.currentTimeMillis();
            shellSort(randomArray1);
            long endTime = System.currentTimeMillis();
            System.out.println("第一个排序" + " " + (endTime - startTime));
            long startTime1 = System.currentTimeMillis();
            insertSort2(randowArray2);
            long endTime1 = System.currentTimeMillis();
            System.out.println("第二个排序" + " " + (endTime1 - startTime1));
        }
    }
}
