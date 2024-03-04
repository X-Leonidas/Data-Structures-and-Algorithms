package cn.xy.leetcode.middle.matrix;

/**
 * @author xiangyu
 * @date 2024-03-05 0:08
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * <p>
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 * <p>
 * 提示：
 * <p>
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 20
 * -1000 <= matrix[i][j] <= 1000
 */
public class A0048RotateImage {

    /**
     * 具体规律
     * 「第 i 行」元素旋转到「第 n−1−i 列」元素；
     * 「第 j 列」元素旋转到「第 j 行」元素；
     *
     * @param matrix
     */

    public void rotate(int[][] matrix) {
        if (matrix.length == 0) {
            return;
        }
        int temp = 0;
        for (int i = 0; i < matrix.length / 2; i++, temp++) {
            for (int j = temp; j < matrix[i].length - temp - 1; j++) {
                recursion(matrix, i, j, i, j);
            }
        }
    }

    public void recursion(int[][] matrix, int row, int column, int starRow, int starColumn) {
        int tagRow = column;
        int tagColumn = matrix.length - 1 - row;
        int temp = matrix[row][column];
        if (tagColumn == starColumn && tagRow == starRow) {
            matrix[tagRow][tagColumn] = matrix[row][column];
        } else {
            recursion(matrix, tagRow, tagColumn, starRow, starColumn);
            matrix[tagRow][tagColumn] = temp;
        }
    }


    /**
     *   * 具体规律
     *      * 「第 i 行」元素旋转到「第 n−1−i 列」元素；
     *      * 「第 j 列」元素旋转到「第 j 行」元素；
     * @param matrix
     */
    public void rotate2(int[][] matrix) {
        // 设矩阵行列数为 n
        int n = matrix.length;
        // 起始点范围为 0 <= i < n / 2 , 0 <= j < (n + 1) / 2
        // 其中 '/' 为整数除法
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < (n + 1) / 2; j++) {
                // 暂存 A 至 tmp
                int tmp = matrix[i][j];
                // 元素旋转操作 A <- D <- C <- B <- tmp
                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = tmp;
            }
        }
    }
}
