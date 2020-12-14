package cn.xy.leetcode.easy.string;

/**
 * @author XiangYu
 * @create2020-11-05-16:02
 *
 * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
 *
 * 示例 1:
 * 输入: "abab"
 * 输出: True
 *
 * 解释: 可由子字符串 "ab" 重复两次构成。
 * 示例 2:
 * 输入: "aba"
 * 输出: False
 * 示例 3:
 *
 * 输入: "abcabcabcabc"
 * 输出: True
 * 解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
 */
public class A0459RepeatedSubstringPattern {
    public static void main(String[] args) {
        String s = "babbbbaabb";
        System.out.println(repeatedSubstringPattern(s));

    }

    /**
     * 自己写的和官方写的第一种写法一致
     * @param s
     * @return
     */
    public static boolean repeatedSubstringPattern(String s) {
        boolean flag = false;
        //找到字符串的因数
        for (int i = 2; s.length()/i >= 1 ; i++) {
            if(s.length()%i != 0){
                continue;
            }
            //偏移量
            int offSet = s.length()/i;

            //获取基本块
            for (int j = 0; j < offSet; j++) {
                char temp = s.charAt(j);
                //判断当前偏移量下所有字符串是否相同
                for (int k = j+offSet; k < s.length(); k=k+offSet) {
                    if(temp != s.charAt(k)){
                        flag = false;
                        break;
                    }
                    flag = true;
                }

                if(!flag){
                    break;
                }
            }
            if (flag){
                return  true;
            }
        }

        return  false;
    }




    /**
     *
     * 叼炸天的第二种写法
     * @param s
     * @return
     */
    public static boolean repeatedSubstringPattern2(String s) {
        return (s + s).indexOf(s, 1) != s.length();
    }

}
