package cn.xy.leetcode.easy;

/**
 * @author XiangYu
 * @create2022-01-06-22:50 给你一个字符串 columnTitle ，表示 Excel 表格中的列名称。返回该列名称对应的列序号。
 * <p>
 *  
 * <p>
 * 例如，
 * <p>
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: columnTitle = "A"
 * 输出: 1
 * 示例 2:
 * <p>
 * 输入: columnTitle = "AB"
 * 输出: 28
 * 示例 3:
 * <p>
 * 输入: columnTitle = "ZY"
 * 输出: 701
 * 示例 4:
 * <p>
 * 输入: columnTitle = "FXSHRXW"
 * 输出: 2147483647
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= columnTitle.length <= 7
 * columnTitle 仅由大写英文组成
 * columnTitle 在范围 ["A", "FXSHRXW"] 内
 */
public class A0171ExcelSheetColumnNumber {

    public int titleToNumber(String columnTitle) {


        int sum = 0;
        for (int i = 0; i < columnTitle.length(); i++) {
            if (sum != 0) {
                sum = sum * 26;
            }
            char temp = columnTitle.charAt(i);
            sum = sum + temp - 64;
        }
        return sum;
    }
}
