package cn.xy.algorithm.dp;

/**
 * @author XiangYu
 * @create2020-12-21-14:10 动态规划
 * <p>
 * 1. 建立状态转移方程
 * 2. 缓存并复用以往结果
 * 3. 按顺序从小往大算
 */
public class DP {

    //实例一
    // 三步问题
    /*
     * 三步问题。有个小孩正在上楼梯，楼梯有n阶台阶，小孩一次可以上1阶、2阶或3阶。实现一种方法，计算小孩有多少种上楼梯的方式。
     * 结果可能很大，你需要对结果模1000000007。
     *
     * 示例1:
     *
     *  输入：n = 3
     *  输出：4
     *  说明: 有四种走法
     * 示例2:
     *
     *  输入：n = 5
     *  输出：13
     * 提示:
     *
     * n范围在[1, 1000000]之间
     *
     */

    public int waysToStep(int n) {
        if (n <= 2) {
            return n;
        }

        int[] ints = new int[n + 1];


        ints[1] = 1;
        ints[2] = 2;
        ints[3] = 4;

        for (int i = 4; i < ints.length; i++) {
            //取模，对两个较大的数之和取模再对整体取模，防止越界（这里也是有讲究的）
            //假如对三个dp[i-n]都 % 1000000007，那么也是会出现越界情况（导致溢出变为负数的问题）
            //因为如果本来三个dp[i-n]都接近 1000000007 那么取模后仍然不变，但三个相加则溢出
            //但对两个较大的dp[i-n]:dp[i-2],dp[i-3]之和mod 1000000007，那么这两个较大的数相加大于 1000000007但又不溢出
            //取模后变成一个很小的数，与dp[i-1]相加也不溢出
            //所以取模操作也需要仔细分析
            ints[i] = (ints[i - 1] + (ints[i - 2] + ints[i - 3]) % 1000000007) % 1000000007;
        }
        return ints[n];
    }


    //实例二
    //最小路径和    题号 64
    /*
     *  给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
        说明：每次只能向下或者向右移动一步。

        示例 1：
        输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
        输出：7
        解释：因为路径 1→3→1→1→1 的总和最小。

        示例 2：
        输入：grid = [[1,2,3],[4,5,6]]
        输出：12
        提示：
        m == grid.length
        n == grid[i].length
        1 <= m, n <= 200
        0 <= grid[i][j] <= 100
     */

    public int minPathSum(int[][] grid) {
        int rows = grid.length;
        int columns = grid[0].length;

        int[][] dp = new int[rows][columns];
        dp[0][0] = grid[0][0];

        for (int i = 1; i < columns; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }

        for (int i = 1; i < rows; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }


        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }


        return dp[rows - 1][columns - 1];
    }

    /**
     * 节省空间的做法,使用原数组保存dp最优解
     *
     * @param grid
     * @return
     */
    public int minPathSum2(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 && j == 0) {
                    continue;
                } else if (i == 0) {
                    grid[i][j] = grid[i][j - 1] + grid[i][j];
                } else if (j == 0) {
                    grid[i][j] = grid[i - 1][j] + grid[i][j];
                } else {
                    grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
                }
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }


    //实例三： 53 最大子序和
    /**
     *  最大子序和
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     * 示例:
     *
     * 输入: [-2,1,-3,4,-1,2,1,-5,4]
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     */



    public static  int maxSubArray(int[] nums) {
        int length = nums.length;

        if(nums.length == 1){
            return nums[0];
        }

        int[] dp = new int[length];
        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-1]+nums[i],nums[i]);
            max = Math.max(max,dp[i]);
        }
        return max;
    }



    //实例4：152 乘积最大子数组

    /**
     * 乘积最大子数组
     * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
     * <p>
     * <p>
     * <p>
     * 示例 1:
     * <p>
     * 输入: [2,3,-2,4]
     * 输出: 6
     * 解释: 子数组 [2,3] 有最大乘积 6。
     * 示例 2:
     * <p>
     * 输入: [-2,0,-1]
     * 输出: 0
     * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
     */

    public static void main(String[] args) {
        int[] ints = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(ints));
    }


    public static int maxProduct(int[] nums) {
        int length = nums.length;
        int[] maxF = new int[length];
        int[] minF = new int[length];
//        System.arraycopy(nums, 0, maxF, 0, length);
//        System.arraycopy(nums, 0, minF, 0, length);
        maxF[0] = nums[0];
        minF[0] = nums[0];
        for (int i = 1; i < length; ++i) {
            maxF[i] = Math.max(maxF[i - 1] * nums[i], Math.max(nums[i], minF[i - 1] * nums[i]));
            minF[i] = Math.min(minF[i - 1] * nums[i], Math.min(nums[i], maxF[i - 1] * nums[i]));
        }
        int ans = maxF[0];
        for (int i = 1; i < length; ++i) {
            ans = Math.max(ans, maxF[i]);
        }
        return ans;
    }
}
