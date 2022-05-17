package cn.xy.algorithm.dp;

/**
 * @author XiangYu
 * @create2021-03-14-15:26
 *   一个数组arr和一个整数aim.
 *   如果可以任意选择arr中的数字，问能不能累加得到aim,返回true或者false
 *
 *
 */
public class Code_06_Money_Problem {
    public static boolean money1(int[] arr, int aim) {
        return process1(arr, 0, 0, aim);
    }

    public static boolean process1(int[] arr, int i, int sum, int aim) {
        if (sum == aim) {
            return true;
        }
        // sum != aim
        if (i == arr.length) {
            return false;
        }
        return process1(arr, i + 1, sum, aim) || process1(arr, i + 1, sum + arr[i], aim);
    }

    /**
     * 寻找base  建立一维或多维数组，该题的数组  y轴为数组的个数，  x轴为数组的元素总和
     *  找出依赖关系，  普遍项N 的结果 依赖于(N+1 ,sum) 或 (N+1, sum+arr[i])
     *
     * @param arr
     * @param aim
     * @return
     */
    public static boolean money2(int[] arr, int aim) {
        boolean[][] dp = new boolean[arr.length + 1][aim + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][aim] = true;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = aim - 1; j >= 0; j--) {
                dp[i][j] = dp[i + 1][j];
                if (j + arr[i] <= aim) {
                    dp[i][j] = dp[i][j] || dp[i + 1][j + arr[i]];
                }
            }
        }
        return dp[0][0];
    }



    public static void main(String[] args) {
        int[] arr = { 1, 4, 8 };
        int aim = 12;
        System.out.println(money1(arr, aim));
        System.out.println(money2(arr, aim));
    }
}
