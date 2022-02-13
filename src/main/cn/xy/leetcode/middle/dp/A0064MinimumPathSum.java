package cn.xy.leetcode.middle.dp;

/**
 * @author XiangYu
 * @create2021-04-27-23:27
 *
 *
 *
 *  给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 * 示例 2：
 *
 * 输入：grid = [[1,2,3],[4,5,6]]
 * 输出：12
 *  
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 100
 *
 *
 */
public class A0064MinimumPathSum {
    public int minPathSum(int[][] matrix) {
        int row = matrix.length;
        int column = matrix[0].length;


        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if(i == 0 && j == 0){
                    continue;
                }else if(i == 0){
                    matrix[i][j] = matrix[i][j-1] + matrix[i][j];
                }else if(j == 0){
                    matrix[i][j] = matrix[i-1][j] + matrix[i][j];
                }else{
                    matrix[i][j] = matrix[i][j] + Math.min(matrix[i-1][j],matrix[i][j-1]);
                }
            }
        }

        return matrix[row - 1][column-1];
    }


    public int minPathSum2(int[][] matrix) {
        // write code here
        // dp[][]记录
        if(matrix == null || matrix.length == 0) {
            return 0;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        dp[0][0] = matrix[0][0];
        // 第一行
        for(int i = 1; i < m ; i++){
            dp[0][i] = dp[0][i-1] + matrix[0][i];
        }
        // 第一列
        for(int j = 1; j < n; j++){
            dp[j][0] = dp[j-1][0] + matrix[j][0];
        }
        // 动态规划
        for(int i = 1; i < n; i++){
            for(int j = 1; j < m ;j++){
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + matrix[i][j];
            }
        }
        return dp[n-1][m-1];
    }
}
