package cn.xy.leetcode.middle.array;

/**
 * @author xiangyu
 * @date 2024-02-04 22:55
 * <p>
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 * <p>
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * <p>
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * 输出：true
 * <p>
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * 输出：false
 * <p>
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -109 <= matrix[i][j] <= 109
 * 每行的所有元素从左到右升序排列
 * 每列的所有元素从上到下升序排列
 * -109 <= target <= 109
 */
public class A0240Search2DMatrix2 {
    public boolean searchMatrix(int[][] matrix, int target) {
        boolean result = false;
        int xLen = matrix[0].length;
        int yLen = matrix.length;

        for (int x = 0; x < xLen; x++) {
            if (matrix[0][x] < target) {
                if (matrix[yLen - 1][x] >= target) {
                    for (int y = 1; y < yLen; y++) {
                        if (matrix[y][x] == target) {
                            return true;
                        } else if (matrix[y][x] > target) {
                            break;
                        }
                    }
                }
            } else if (matrix[0][x] == target) {
                return true;
            } else {
                break;
            }
        }
        return result;
    }

    //   二分加速
    public boolean searchMatrix2(int[][] matrix, int target) {
        boolean result = false;
        int xLen = matrix[0].length;
        int yLen = matrix.length;

        for (int x = 0; x < xLen; x++) {
            if (matrix[0][x] < target) {
                if (matrix[yLen - 1][x] >= target) {
                    int l = 0;
                    int r = yLen;
                    while (l <= r) {
                        int mid = l + (r - l) / 2;
                        if (matrix[mid][x] < target) {
                            l = mid+1;
                        } else if (matrix[mid][x] > target) {
                            r = mid-1;
                        } else {
                            return true;
                        }
                    }
                }
            } else if (matrix[0][x] == target) {
                return true;
            } else {
                break;
            }
        }
        return result;
    }

}
