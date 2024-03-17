package cn.xy.leetcode.middle.backtracking;

/**
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * 示例 1：
 * <p>
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * 输出：true
 * 示例 3：
 * <p>
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * 输出：false
 *  
 * 提示：
 * <p>
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board 和 word 仅由大小写英文字母组成
 *
 * @author XiangYu
 * @create2021-08-14-23:51
 */
public class A0079WordSearch {
    public boolean[][] flag;
    public boolean result = false;


    public static void main(String[] args) {
        char[][] ints =  {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String s = "SEE";
        System.out.println(new A0079WordSearch().exist(ints, s));

    }

    /**
     * 执行用时：52 ms, 在所有 Java 提交中击败了97.35%的用户
     * 内存消耗：36.1 MB, 在所有 Java 提交中击败了90.18%的用户
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0){
            return false;
        }

        char[] chars = word.toCharArray();

        flag = new boolean[board.length][board[0].length];


        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (board[y][x] == chars[0]) {
                    flag[y][x] = true;
                    process(board, chars, flag, x, y, 0);
                    flag[y][x] = false;
                    if(result){
                        return true;
                    }
                }
            }
        }

        return result;
    }

    private void process(char[][] board, char[] chars, boolean[][] flag, int x, int y, int index) {
        if (index == chars.length-1) {
            result = true;
            return;
        }

        int temp = index + 1;
        if (y > 0 && !flag[y - 1][x]) {
            if (board[y - 1][x] == chars[temp] ) {
                flag[y - 1][x] = true;
                process(board, chars, flag, x, y - 1, temp);
                // 剪枝
                if (result) {
                    return;
                }
                flag[y - 1][x] = false;
            }
        }

        if (y < board.length - 1  && !flag[y + 1][x]) {
            if (board[y + 1][x] == chars[temp]) {
                flag[y + 1][x] = true;
                process(board, chars, flag, x, y + 1, temp);
                if (result) {
                    return;
                }
                flag[y + 1][x] = false;
            }
        }

        if (x > 0 && !flag[y][x - 1]) {
            if (board[y][x - 1] == chars[temp]) {
                flag[y][x - 1] = true;
                process(board, chars, flag, x - 1, y, temp);
                if (result) {
                    return;
                }
                flag[y][x - 1] = false;
            }
        }

        if (x < board[0].length - 1 && !flag[y][x + 1]) {
            if (board[y][x + 1] == chars[temp]) {
                flag[y][x + 1] = true;
                process(board, chars, flag, x + 1, y, temp);
                if (result) {
                    return;
                }
                flag[y][x + 1] = false;
            }
        }


    }
}

