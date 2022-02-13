package xy.algorithm.array;

/**
 * @author XiangYu
 * @create2021-04-07-21:32
 *   难题
 *
 *   给定一个数组arr， 值可正， 可负， 可0； 一个整数aim， 求累加和小于等于aim的， 最长子数组， 要求时间复杂度O(N)
 *
 *   思路：
 *      min_sum[i]:原数组以i位置开头的子数组能得到的最小累加和
 *      min_sum_index[i]:当以i开头的子数组，拿到最小累加的位置
 *
 *
 *      从右向左遍历一边可生成两个数组
 *      1.先得到0开头的数组小于等于aim的最长子数组
 *      2.把0排除掉，此时0~T变为1~T,校验T+1开的最小累加和是否可以加入当前数组，用滑窗滑过整个数组，结果就出来了
 *
 *
 *
 *
 */
public class Code_06_LongestSubarrayLessSumAwesomeSolution {

    /**
     * @param arr
     * @param aim
     * @return
     */
    public static int maxLengthAwesome(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        //最小累加和数组
        int[] min_sum = new int[arr.length];
        int[] min_sum_index = new int[arr.length];
        min_sum_index[arr.length - 1] = arr.length - 1;
        min_sum[arr.length - 1] = arr[arr.length - 1];

        //填充整个数组sum数组
        for (int i = arr.length - 2; i >= 0; i--) {
            if (min_sum[i + 1] < 0) {
                min_sum[i] = arr[i] + min_sum[i + 1];
                min_sum_index[i] = min_sum_index[i + 1];
            } else {
                min_sum[i] = arr[i];
                min_sum_index[i] = i;
            }
        }


        int r = 0;
        //从start到r的累计和
        int sum = 0;
        int len = 0;
        for (int start = 0; start < arr.length; start++) {
            while (r < arr.length && sum + min_sum[r] <= aim) {
                sum += min_sum[r];
                r = min_sum_index[r] + 1;
            }
            sum -= r > start ? arr[start] : 0;
            len = Math.max(len, r - start);
            r = Math.max(r, start + 1);
        }
        return len;
    }


    //-----------------------------------------二分加速--------------------------------------------------

    /**
     * @param arr
     * @param k
     * @return
     */
    public static int maxLength(int[] arr, int k) {
        int[] h = new int[arr.length + 1];
        int sum = 0;
        h[0] = sum;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            h[i + 1] = Math.max(sum, h[i]);
        }
        sum = 0;
        int res = 0;
        int pre = 0;
        int len = 0;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            pre = getLessIndex(h, sum - k);
            len = pre == -1 ? 0 : i - pre + 1;
            res = Math.max(res, len);
        }
        return res;
    }

    public static int getLessIndex(int[] arr, int num) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int res = -1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (arr[mid] >= num) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int len, int maxValue) {
        int[] res = new int[len];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue) - (maxValue / 3);
        }
        return res;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000000; i++) {
            int[] arr = generateRandomArray(10, 20);
            int k = (int) (Math.random() * 20) - 5;
            if (maxLengthAwesome(arr, k) != maxLength(arr, k)) {
                System.out.println("oops!");
            }
        }

    }

}
