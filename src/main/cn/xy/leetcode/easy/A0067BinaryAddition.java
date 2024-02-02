package cn.xy.leetcode.easy;

/**
 * @author xiangyu
 * @date 2024-01-29 23:59
 * 二进制加法
 * 给定两个 01 字符串 a 和 b ，请计算它们的和，并以二进制字符串的形式输出。
 * <p>
 * 输入为 非空 字符串且只包含数字 1 和 0。
 * 示例 1:
 * <p>
 * 输入: a = "11", b = "10"
 * 输出: "101"
 * 示例 2:
 * <p>
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 每个字符串仅由字符 '0' 或 '1' 组成。
 * 1 <= a.length, b.length <= 10^4
 * 字符串如果不是 "0" ，就都不含前导零
 */
public class A0067BinaryAddition {
    public static String addBinary(String a, String b) {
        char[] arrayA = a.toCharArray();
        char[] arrayB = b.toCharArray();
        char[] result = new char[Math.max(a.length() + 1, b.length() + 1)];
        int indexA = a.length() - 1;
        int indexB = b.length() - 1;
        boolean cnt = false;
        int curIndex = result.length - 1;
        while (indexA >= 0 && indexB >= 0) {
            int temp =  arrayA[indexA--] + arrayB[indexB--] - 96;
            if (cnt) {
                temp++;
                cnt = false;
            }
            if (temp >= 2) {
                temp = temp % 2;
                cnt = true;
            }
            result[curIndex--] = (char) (temp + 48);
        }

        while(indexA >= 0){
            int temp = arrayA[indexA--] - 48;
            if(cnt){
                temp++;
                cnt = false;
            }
            if (temp >= 2) {
                temp = temp % 2;
                cnt = true;
            }
            result[curIndex--] = (char) (temp + 48);
        }

        while(indexB >= 0){
            int temp = arrayA[indexB--] - 48;
            if(cnt){
                temp++;
                cnt = false;
            }
            if (temp >= 2) {
                temp = temp % 2;
                cnt = true;
            }
            result[curIndex--] = (char) (temp + 48);
        }
        String str = new String(result);
        if(cnt){
            result[curIndex] = '1';
        }


        if(result[0] != 1 && str.length() > 1){
            str = str.substring(1);
        }

        return str;
    }

    public static void main(String[] args) {
        System.out.println(addBinary("1", "111"));
    }
}
