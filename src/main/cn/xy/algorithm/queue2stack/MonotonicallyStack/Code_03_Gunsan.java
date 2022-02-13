package xy.algorithm.queue2stack.MonotonicallyStack;

import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

/**
 * @author XiangYu
 * @create2021-03-23-22:12 一个数组arr代表一个环形的山，每座山上都会放烽火
 * 1，相邻的每座山都可以看到对方的烽火
 * 2. 不相邻的两座山，两座山之间的山比两座山都要矮，才能看到彼此的烽火
 * <p>
 * 求能看到的山峰有多少对？
 * <p>
 * <p>
 * 当没有相同高度的两座山时， 为 2*n -3
 */
public class Code_03_Gunsan {

    public static class Pair {
        public int value;
        public int times;

        public Pair(int value) {
            this.value = value;
            this.times = 1;
        }
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNextInt()) {
            int size = in.nextInt();
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = in.nextInt();
            }
            System.out.println(communications(arr));
        }


        in.close();
    }

    /**
     * 循环数组处理，当i到达尽头时回到0
     * @param size
     * @param i
     * @return
     */
    private static int nextIndex(int size, int i) {
        return i < (size - 1) ? (i + 1) : 0;
    }

    /**
     * 组和数列公式  C(n,m)
     * @param n
     * @return
     */
    private static long getInternalSum(int n) {
        return n == 1L ? 0L : (long) n * (long) (n - 1) / 2L;
    }

    private static long communications(int[] arr) {
        if (null == arr || arr.length < 2) {
            return 0;
        }

        int size = arr.length;
        int maxIndex = 0;

        for (int i = 0; i < size; i++) {
            maxIndex = arr[maxIndex] < arr[i] ? i : maxIndex;
        }

        int value = arr[maxIndex];
        //最大值的下一个元素
        int index = nextIndex(size, maxIndex);
        long res = 0L;
        Stack<Pair> stack = new Stack<Pair>();
        stack.push(new Pair(value));
        //遍历过程中结算和压栈逻辑
        while (index != maxIndex) {
            value = arr[index];
            //当要压入的元素不符合单调栈定义，结算弹出栈的元素
            while (!stack.isEmpty() && stack.peek().value < value) {
                int times = stack.pop().times;
                //C(times,2) + 2*times
                res += getInternalSum(times) + times;
                res += stack.isEmpty() ? 0 : times;
            }

            if (!stack.isEmpty() && stack.peek().value == value) {
                stack.peek().times++;
            } else {
                stack.push(new Pair(value));
            }
            index = nextIndex(size, index);
        }


        //遍历完成后，清空栈的逻辑
        while (!stack.isEmpty()) {
            int times = stack.pop().times;
            res += getInternalSum(times);
            if (!stack.isEmpty()) {
                res += times;
                if (stack.size() > 1) {
                    res += times;
                } else {
                    res += stack.peek().times > 1 ? times : 0;
                }
            }
        }
        return res;
    }

}
