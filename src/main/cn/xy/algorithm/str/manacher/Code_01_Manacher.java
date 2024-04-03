package cn.xy.algorithm.str.manacher;

/**
 * @author XiangYu
 * @date 2021-03-21-18:45
 * 可以在时间**O(N)**的情况下求解一个字符串的最长回文子串长度的问题。
 * 在每个字符串之间加入一个标志位，例如 11311 -> #1#1#3#1#1# , 确保回文串是奇数，对称位置只有虚轴没有实轴
 * 对应关系:
 * + 真实长度 = 回文半径 -1
 * + 真实回文串终止位置 = 扩展回文串结尾下标/2
 * <p>
 * 回文半径数组p[i] 代表当前位置的最大半径
 * 回文覆盖右边界r: 回文到不了的边界
 * 会问中心c : 此时取得当前 r 的 最早的c位置
 * <p>
 * 四种情况：
 * a，i没有被r包住，那么以i为中心直接扩展 i >= r
 * b，i被r包住，对称点 2*c-i 的回文半径，在大回文区域以内，直接确定p[i] = p[2*c-i]
 * <blockquote> 因为回文子串都处于整个大回文中，所以 i 和 以c对称的i` 必定相等
 * c，i被r包住，对称点 2*c-i 的回文半径，在大回文区域以外，直接确定p[i] = r - i
 * <blockquote>
 * d，i被r包住，对称点 2*c-i 的回文半径，撞线大回文区域的边界，从r之外的位置进行扩展
 * <blockquote>
 */
public class Code_01_Manacher {

    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        //预处理操作，将偶数变为奇数
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        //插入的标志位
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    public static int maxLcpsLength(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        //构建算法的前置数据结构
        char[] charArr = manacherString(str);
        //回文半径数组
        int[] pArr = new int[charArr.length];
        // 此时取得当前 r 的 最早的位置
        int c = 0;
        //回文右边界
        int pR = 0;
        // 当前的半径长度
        int len;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < charArr.length; i++) {
            // i 在 回文右边界里面，先给一个至少的回文区域  没有的从本身1开始
            len = pR > i ? Math.min(pArr[2 * c - i], pR - i) : 1;

            // 左右扩的行为
            while (i + len < charArr.length && i - len >= 0 && charArr[i + len] == charArr[i - len]) {
                len++;
            }
            // 更新pR
            // 以及当前最早到达pR的位置
            if (i + len > pR) {
                pR = i + len;
                c = i;
            }
            max = Math.max(max, len);
            pArr[i] = len;
        }

        return max - 1;
    }

    public static void main(String[] args) {
        String str1 = "abc1234321ab";
        System.out.println(maxLcpsLength(str1));
    }


    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }

        if (s.length() == 1) {
            return s;
        }
        //滑块

        //数组的长度，回文数的长度
        int length = s.length();


        for (int j = length; j > 0; j--) {
            int i = 0;
            while (i + length - 1 < s.length()) {
                //开始
                int star = i;
                //结束
                int end = i + length - 1;
                //需要校验长度
                int tempLength = (end - star + 1) / 2;
                boolean flag = true;

                for (int k = 0; k < tempLength; k++) {
                    if (s.charAt(star) == s.charAt(end)) {
                        star++;
                        end--;
                    } else {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    return s.substring(i, i + length);
                } else {
                    i++;
                }
            }
            //未匹配到，长度减1
            length--;
        }


        return s.substring(0, 1);
    }
}
