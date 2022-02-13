package xy.algorithm.sort;

/**
 * @author XiangYu
 * @create2020-11-04-0:54
 *
 *  插入排序的改良算法
 *
 *
 */
public class B4_ShellSort {
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
}
