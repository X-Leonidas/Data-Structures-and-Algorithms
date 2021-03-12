package cn.xy.codinginterviews;

/**
 * @author XiangYu
 * @create2021-02-25-21:48
 *
 *
 * 顺时针打印矩阵
 *
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 示例 2：
 *
 * 输入：matrix = [[1,2,3,4],
 *                 [5,6,7,8],
 *                [9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *  
 *
 * 限制：
 *
 * 0 <= matrix.length <= 100
 * 0 <= matrix[i].length <= 100
 *
 */
public class A029PrintMatrixByClockwise {

    /**
     * 可以模拟打印矩阵的路径。初始位置是矩阵的左上角，初始方向是向右，当路径超出界限或者进入之前访问过的位置时，则顺时针旋转，进入下一个方向。
     * 判断路径是否进入之前访问过的位置需要使用一个与输入矩阵大小相同的辅助矩阵visited，其中的每个元素表示该位置是否被访问过。
     * 当一个元素被访问时，将 visited中的对应位置的元素设为已访问。
     *
     * 如何判断路径是否结束？由于矩阵中的每个元素都被访问一次，因此路径的长度即为矩阵中的元素数量，当路径的长度达到矩阵中的元素数量时即为完整路径，将该路径返回。
     *
     * @param matrix
     * @return
     */
    public int[] spiralOrder1(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }

        int rows = matrix.length, columns = matrix[0].length;

        //构建标记数组
        boolean[][] visited = new boolean[rows][columns];

        //获取元素总数
        int total = rows * columns;
        int[] order = new int[total];
        int row = 0, column = 0;
        //移动方向 右  下  左 上
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directionIndex = 0;

        for (int i = 0; i < total; i++) {
            order[i] = matrix[row][column];
            visited[row][column] = true;

            int nextRow = row + directions[directionIndex][0];
            int nextColumn = column + directions[directionIndex][1];
            //前几个都是最外圈的情况， 当最外圈遍历完毕后靠标记数组来标识是否已经遍历
            if (nextRow < 0 || nextRow >= rows || nextColumn < 0 || nextColumn >= columns || visited[nextRow][nextColumn]) {
                //到达尽头转弯
                directionIndex = (directionIndex + 1) % 4;
            }
            //到达下一个起点
            row += directions[directionIndex][0];
            column += directions[directionIndex][1];
        }
        return order;
    }


    /**
     * 按层次模拟
     * @param matrix
     * @return
     */
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }

        //获取行和列的总数
        int rows = matrix.length, columns = matrix[0].length;
        //初始化输出数组
        int[] order = new int[rows * columns];
        int index = 0;
        //固定边界
        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;

        while (left <= right && top <= bottom) {
            //从左向右，到顶点
            for (int column = left; column <= right; column++) {
                order[index++] = matrix[top][column];
            }
            //从上到下
            for (int row = top + 1; row <= bottom; row++) {
                order[index++] = matrix[row][right];
            }

            //再次判断是否遍历完成，避免一行再返回的情况
            if (left < right && top < bottom) {
                //从右往左
                for (int column = right - 1; column > left; column--) {
                    order[index++] = matrix[bottom][column];
                }
                //从上到下
                for (int row = bottom; row > top; row--) {
                    order[index++] = matrix[row][left];
                }
            }
            //缩小层次
            left++;
            right--;
            top++;
            bottom--;
        }
        return order;
    }


    /**
     * 左神的写法
     * @param matrix
     * 用左顶点和右低点，两个点定义一个矩形，一次打印该矩形的元素
     *
     *
     */

    public static void spiralOrderPrint(int[][] matrix) {
        int tR = 0;
        int tC = 0;
        int dR = matrix.length - 1;
        int dC = matrix[0].length - 1;
        while (tR <= dR && tC <= dC) {
            printEdge(matrix, tR++, tC++, dR--, dC--);
        }
    }

    public static void printEdge(int[][] m, int tR, int tC, int dR, int dC) {
        //一横行
        if (tR == dR) {
            for (int i = tC; i <= dC; i++) {
                System.out.print(m[tR][i] + " ");
            }
        } else if (tC == dC) {
            //一竖行
            for (int i = tR; i <= dR; i++) {
                System.out.print(m[i][tC] + " ");
            }
        } else {
            int curC = tC;
            int curR = tR;
            while (curC != dC) {
                System.out.print(m[tR][curC] + " ");
                curC++;
            }
            while (curR != dR) {
                System.out.print(m[curR][dC] + " ");
                curR++;
            }
            while (curC != tC) {
                System.out.print(m[dR][curC] + " ");
                curC--;
            }
            while (curR != tR) {
                System.out.print(m[curR][tC] + " ");
                curR--;
            }
        }
    }

}
