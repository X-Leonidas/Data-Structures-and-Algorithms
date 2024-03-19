package cn.xy.leetcode.middle.binarysearch;

/**
 * @author xiangyu
 * @date 2024-03-18 22:45
 * 给你一个满足下述两条属性的 m x n 整数矩阵：
 * 每行中的整数从左到右按非严格递增顺序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * 输出：false
 * <p>
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -104 <= matrix[i][j], target <= 104
 */
public class A0074Search2DMatrix {

    public static void main(String[] args) {
        A0074Search2DMatrix a0074Search2DMatrix = new A0074Search2DMatrix();
        a0074Search2DMatrix.searchMatrix(new int[][]{{1}}, 3);
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }

        //  左下起第一个小于 target ， 右上起第一个大于 target
        int lengthY = matrix.length;
        int lengthX = matrix[0].length;

        int left = lengthY - 1;
        while (left > 0 && matrix[left][0] > target) {
            left--;
        }

        int right = 0;
        while (right < lengthY-1 && matrix[right][lengthX - 1] < target) {
            right++;
        }
        for (int i = left; i <= right; i++) {
            // 二分查找
            int start = 0;
            int end = lengthX - 1;
            while (start <= end) {
                int mid = start + (end - start) / 2;
                if (matrix[i][mid] < target) {
                    start = mid + 1;
                } else if (matrix[i][mid] > target) {
                    end = mid - 1;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 将二维数组化为一维数组使用
     * @param mat
     * @param t
     * @return
     */
    public boolean searchMatrix2(int[][] mat, int t) {
        int m = mat.length, n = mat[0].length;
        int l = 0, r = m * n - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (mat[mid / n][mid % n] <= t) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return mat[r / n][r % n] == t;
    }

}
