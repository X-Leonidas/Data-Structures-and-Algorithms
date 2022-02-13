package xy.codinginterviews;

/**
 * @author XiangYu
 * @create2020-11-03-14:23
 * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。
 * 比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
 *
 *  1 <= k < s.length <= 10000
 */



public class A058ⅡLeftHandString {

    public static void main(String[] args) {
        String s = "abcdefg";
        int n = 2;
        System.out.println(reverseLeftWords3(s, n));
    }

    //暴力破解
    public static String reverseLeftWords(String s, int n) {
        char[] chars = s.toCharArray();
        char[] chars1 = new char[chars.length];
        int m = chars.length - n;
        for (int i = 0; i < chars.length; i++) {
            if(i < n){
                chars1[i+m] = chars[i];
            }else{
                chars1[i-n] = chars[i];
            }
        }
        return String.valueOf(chars1);




    }

    //孙笑川式写法
    public static String reverseLeftWords2(String s, int n) {
        return  s.substring(n) + s.substring(0,n);
    }
    //使用工具类
    public static String reverseLeftWords3(String s, int n) {
//        StringBuilder res = new StringBuilder();
//        for(int i = n; i < s.length(); i++){
//            res.append(s.charAt(i));
//        }
//        for(int i = 0; i < n; i++){
//            res.append(s.charAt(i));
//        }
//        return res.toString();


        //简便写法
        StringBuilder res = new StringBuilder();
        for(int i = n; i < n + s.length(); i++){
            res.append(s.charAt(i % s.length()));
        }
        return res.toString();
    }
}
