package cn.xy.algorithm.windows;

import java.util.LinkedList;

/**
 * @author XiangYu
 * @create2021-03-21-22:48 有一个整型数组arr和一个大小为w的窗口从数组的最左边滑到最右边，窗口每次向右边滑一个位置。
 * 例如，数组为【4,3,5,4,3,3,6,7】，窗口大小为3时：
 * <p>
 * 窗口数组 　　                   最大值
 * <p>
 * [4 3 5] 4 3 3 6 7                5
 * 4 [3 5 4] 3 3 6 7                5
 * 4 3 [5 4 3] 3 6 7                5
 * 4 3 5 [4 3 3] 6 7                4
 * 4 3 5 4 [3 3 6] 7                6
 * 4 3 5 4 3 [3 6 7]                7
 * <p>
 * 如果数组长度为n，窗口大小为w，则一共产生n-w+1个窗口的最大值。
 * 请功能实现一个函数，
 * 输入：整型数组arr，窗口大小为w；
 * 输出：一个长度为n-w+1的数组res，res【i】表示每一种窗口状态下的最大值。
 * <p>
 * 以本题为例，结果应该返回【5,5,5,4,6,7】
 * <p>
 * <p>
 * 思路：
 * + 需要一个双端队列存储值和索引下标，队列的值从大到小排列
 *  + 窗口中加数的逻辑
 *      + 加的数从**队尾**插入双端队列，并且要保证是从大到小的顺序，如果不符合先弹出双端队列中的值在塞入当前数及其索引
 *      + 例如： 窗口中的数为 5 4 1 2 5 6    则此时双端队列中只有6
 *  + 窗口中减数的逻辑
 *      +  查看当前双端队列的头节点所存储的索引是否为当前要弹出的数的索引，如果是，则从双端队列弹出头节点
 */
public class Windows {
    /**
     *
     * @param arr
     * @param w  窗口大小
     * @return
     */
    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || arr.length < w || w < 1) {
            return null;
        }

        //用来记录最大值的更新
        LinkedList<Integer> qmax = new LinkedList<Integer>();

        //用来记录结果
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            //1到4步骤的过程
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]) {
                qmax.pollLast();
            }
            qmax.addLast(i);

            //第5步:过期计算
            if (qmax.peekFirst() == i - w) {
                qmax.pollFirst();
            }
            //第6步:记录res值
            if (i - w >= -1) {
                res[index++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 3, 5, 4, 3, 3, 6, 7};
        int[] res = getMaxWindow(arr, 3);
        for (int r : res) {
            System.out.println(r);
        }
    }
}
