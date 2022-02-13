package xy.algorithm.windows;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author XiangYu
 * @create2021-03-22-22:21
 *
 * 给定数组 arr 和整数 num，共返回有多少个子数组满足如下情况：
 * max(arr[i…j]) - min(arr[i…j]) <= num
 * max(arr[i…j])表示子数组arr[i…j]中的最大值，min[arr[i…j])表示子数组arr[i…j]中的最小值。
 *
 *
 *
 *  思路：
 *       结论：如果一个子数组从L到R达标，则子数组内的任一个子数组都达标，如果一个子数组从L到R已经不达标，该子数组往外扩容一定不达标
 *       1. 扩张窗口，当窗口在扩容一个就不达标的情况下停止，获得从L到X的所有达标子串 x+1-L 个
 *       2. 缩小窗口L左移，更新窗口的最大，最小值，并尝试R是否可以往右走，直到达到不达标的情况下停止或到达数组尽头
 *       3. 终止情况：
 *
 */
public class Code_02_AllLessNumSubArray {

    /**
     *
     * @param arr
     * @param num 目标值
     * @return
     */
    public static int getNum(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        //最大最小更新结构
        LinkedList<Integer> qmax = new LinkedList<>();
        LinkedList<Integer> qmin = new LinkedList<>();


        //start
        int L = 0;
        //end
        int R = 0;
        int res = 0;

        while (L < arr.length) {
            while (R < arr.length) {
                // 生成最大值更新结构
                while (!qmax.isEmpty() && arr[R] >= arr[qmax.peekLast()]) {
                    qmax.pollLast();
                }
                qmax.offerLast(R);
                // 生成最小值更新结构
                while (!qmin.isEmpty() && arr[R] <= arr[qmin.peekLast()]) {
                    qmin.pollLast();
                }
                qmin.offerLast(R);

                // 不达标的情况
                if (arr[qmax.peekFirst()] - arr[qmin.peekFirst()] > num) {
                    break;
                }
                R++;
            }
            // L所指向的元素将过期，需从更新结构中弹出
            if (qmax.peekFirst() == L) {
                qmax.pollFirst();
            }
            if (qmin.peekFirst() == L) {
                qmin.pollFirst();
            }
            // 统计子数组数量
            res += R - L;
            L++;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int num = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(getNum(arr, num));
    }
}
