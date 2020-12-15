package cn.xy.leetcode.practice.stringtest;

/**
 * @author XiangYu
 * @create2020-12-04-10:36
 *
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 *
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 *
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 * 示例 2：
 *
 * 输入：["H","a","n","n","a","h"]
 * 输出：["h","a","n","n","a","H"]
 *
 *
 */
public class StringTest4_1 {

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.99%的用户
     * 内存消耗：44.9 MB, 在所有 Java 提交中击败了94.21%的用户
     * @param s
     */
    public void reverseString(char[] s) {
        int length = s.length;
        for (int i = 0; i < length/2; i++) {
                char temp = s[i];
                s[i] = s[length - i -1];
                s[length - i -1] = temp;
        }
    }

}
