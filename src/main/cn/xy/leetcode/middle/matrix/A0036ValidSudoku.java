package cn.xy.leetcode.middle.matrix;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 请你判断一个 9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 *
 * 注意：
 * 一个有效的数独（部分已被填充）不一定是可解的。
 * 只需要根据以上规则，验证已经填入的数字是否有效即可。
 * 空白格用 '.' 表示。
 *
 *
 * 示例 1：
 * 输入：board =
 * [["5","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * 输出：true
 *
 * 示例 2：
 * 输入：board =
 * [["8","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * 输出：false
 * 解释：除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。 但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。
 *
 * 提示：
 * board.length == 9
 * board[i].length == 9
 * board[i][j} 是一位数字（1-9）或者 '.'
 *
 *
 * @author xiangyu
 * @date 2025-02-15 20:01
 */
public class A0036ValidSudoku {
    public static void main(String[] args) {
        char[][] board =
                {{'.','.','4','.','.','.','6','3','.'},{'.','.','.','.','.','.','.','.','.'},{'5','.','.','.','.','.','.','9','.'},{'.','.','.','5','6','.','.','.','.'},{'4','.','3','.','.','.','.','.','1'},{'.','.','.','7','.','.','.','.','.'},{'.','.','.','5','.','.','.','.','.'},{'.','.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.','.'}};

        boolean validSudoku = new A0036ValidSudoku().isValidSudoku(board);
    }


    private Map<Integer, HashSet<Character>> rowCache = new HashMap<>();

    private Map<Integer, HashSet<Character>> columnCache = new HashMap<>();

    private Map<String, HashSet<Character>>  nineSqureCache = new HashMap<>();

    /**
     * 可以将MAP 转成数组来减少性能耗费
     *  九宫格可以设置成 [3][3][9]
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                if(board[x][y] == '.'){
                    continue;
                }

                HashSet<Character> rowHashSet = rowCache.getOrDefault(x, new HashSet<>());
                if(rowHashSet.contains(board[x][y])){
                    return false;
                }else{
                    rowHashSet.add(board[x][y]);
                    rowCache.put(x,rowHashSet);
                }

                HashSet<Character> colunmHashSet = columnCache.getOrDefault(y, new HashSet<>());
                if(colunmHashSet.contains(board[x][y])){
                    return false;
                }else{
                    colunmHashSet.add(board[x][y]);
                    rowCache.put(y,colunmHashSet);
                }

               String   nineSqureIndex = x/3  + "" + y/3;
                HashSet<Character> nineHashSet = nineSqureCache.getOrDefault(nineSqureIndex, new HashSet<>());
                if(nineHashSet.contains(board[x][y])){
                    return false;
                }else{
                    nineHashSet.add(board[x][y]);
                    nineSqureCache.put(nineSqureIndex,nineHashSet);
                }
            }
        }

        return true;
    }

}
