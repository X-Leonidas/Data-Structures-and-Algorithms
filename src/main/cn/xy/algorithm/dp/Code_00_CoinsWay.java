package xy.algorithm.dp;

/**
 * @author XiangYu
 * @create2021-04-05-20:02 换钱的方法数
 * 【 题目】
 * 给定数组arr， arr中所有的值都为正数且不重复。 每个值代表
 * 一种面值的货币， 每种面值的货币可以使用任意张， 再给定一
 * 个整数aim代表要找的钱数， 求换钱有多少种方法。
 * 【 举例】
 * arr=[5,10,25,1]， aim=0。
 * 组成0元的方法有1种， 就是所有面值的货币都不用。 所以返回1。
 * arr=[5,10,25,1]， aim=15。
 * 组成15元的方法有6种， 分别为3张5元、 1张10元+1张5元、 1张
 * 10元+5张1元、 10张1元+1张5元、 2张5元+5张1元和15张1元。 所
 * 以返回6。
 * arr=[3,5]， aim=2。
 * 任何方法都无法组成2元。 所以返回0
 * <p>
 * 有后效性：前面所作的抉择影响后续的抉择，例如n皇后问题
 * 无后效性：前面所作的抉择无法影响后续的抉择
 * 与code _01 配套练习
 * <p>
 * 思路：
 * 1.先写出暴力递归的方式
 * 2。 接下来分析哪些参数一旦固定，哪些值就可以固定，（哪些结果可以缓存来提高性能）
 * 2.1 根据参数做出二维数组的表，横纵坐标一般是递归函数的初始参数
 * 3. 根据递归过程，分析表中的哪些数据可以被确定
 * 3. 找出不被确定的数据项的依赖关系
 */
public class Code_00_CoinsWay {


    /**
     * 暴力递归方法
     *
     * @param arr
     * @param aim
     * @return
     */
    public static int coins1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process1(arr, 0, aim);
    }

    /**
     * @param arr
     * @param index 可以任意自由使用index及其之后所有的钱
     * @param aim   目标钱数
     * @return 方法数
     */
    public static int process1(int[] arr, int index, int aim) {
        int res = 0;
        if (index == arr.length) {
            res = aim == 0 ? 1 : 0;
        } else {
            for (int i = 0; arr[index] * i <= aim; i++) {
                res += process1(arr, index + 1, aim - arr[index] * i);
            }
        }
        return res;
    }


    /**
     * 用map[index][aim]保存已经计算过的递归过程的结果
     * @param arr
     * @param aim
     * @return
     */
    public static int coins2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[][] map = new int[arr.length + 1][aim + 1];
        return process2(arr, 0, aim, map);
    }

    /**
     * @param arr
     * @param index
     * @param aim
     * @param map
     * @return
     */
    public static int process2(int[] arr, int index, int aim, int[][] map) {
        int res = 0;
        if (index == arr.length) {
            res = aim == 0 ? 1 : 0;
        } else {
            int mapValue = 0;
            for (int i = 0; arr[index] * i <= aim; i++) {

                mapValue = map[index + 1][aim - arr[index] * i];
                if (mapValue != 0) {
                    res += mapValue == -1 ? 0 : mapValue;
                } else {
                    res += process2(arr, index + 1, aim - arr[index] * i, map);
                }
            }
        }
        map[index][aim] = res == 0 ? -1 : res;
        return res;
    }


    public static int coins3(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[][] dp = new int[arr.length][aim + 1];
        //第1列都为0.dp[i][0] 都为1，表示组成钱数0的方法只有1种
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }
        //第1行中的某些项.dp[0][j]，只使用arr[0]货币，可以组成哪些数
        for (int j = 1; arr[0] * j <= aim; j++) {
            dp[0][arr[0] * j] = 1;
        }
        int num = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                num = 0;
                for (int k = 0; j - arr[i] * k >= 0; k++) {
                    num += dp[i - 1][j - arr[i] * k];
                }
                dp[i][j] = num;
            }
        }
        return dp[arr.length - 1][aim];
    }

    /**
     * dp[i][j] = dp[i-1][j] + dp[i][j-arr[i]]
     * @param arr
     * @param aim
     * @return
     */
    public static int coins4(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[][] dp = new int[arr.length][aim + 1];
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; arr[0] * j <= aim; j++) {
            dp[0][arr[0] * j] = 1;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                dp[i][j] = dp[i - 1][j];
                dp[i][j] += j - arr[i] >= 0 ? dp[i][j - arr[i]] : 0;
            }
        }
        return dp[arr.length - 1][aim];
    }

    /**
     * 空间压缩
     * @param arr
     * @param aim
     * @return
     */
    public static int coins5(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[] dp = new int[aim + 1];
        for (int j = 0; arr[0] * j <= aim; j++) {
            dp[arr[0] * j] = 1;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                dp[j] += j - arr[i] >= 0 ? dp[j - arr[i]] : 0;
            }
        }
        return dp[aim];
    }

    public static void main(String[] args) {
        int[] coins = {10, 5, 1, 25};
        int aim = 2000;

        long start = 0;
        long end = 0;
        start = System.currentTimeMillis();
        System.out.println(coins1(coins, aim));
        end = System.currentTimeMillis();
        System.out.println("cost time : " + (end - start) + "(ms)");

        aim = 20000;

        start = System.currentTimeMillis();
        System.out.println(coins2(coins, aim));
        end = System.currentTimeMillis();
        System.out.println("cost time : " + (end - start) + "(ms)");

        start = System.currentTimeMillis();
        System.out.println(coins3(coins, aim));
        end = System.currentTimeMillis();
        System.out.println("cost time : " + (end - start) + "(ms)");

        start = System.currentTimeMillis();
        System.out.println(coins4(coins, aim));
        end = System.currentTimeMillis();
        System.out.println("cost time : " + (end - start) + "(ms)");

        start = System.currentTimeMillis();
        System.out.println(coins5(coins, aim));
        end = System.currentTimeMillis();
        System.out.println("cost time : " + (end - start) + "(ms)");

    }

}
