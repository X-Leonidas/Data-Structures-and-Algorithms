package cn.xy.codinginterviews;

/**
 * @author XiangYu
 * @create2021-04-20-10:39
 *
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。
 *
 *
 *   思路： 将n次方转为2进制表达，例如10^75 =  10^1001011  = 10^64 * 10^8 * 10^2 * 10^1
 */
public class A016Pow {
    public static double myPow(double x, int n) {
        if(n == 0){
            return  1;
        }

        String binaryString = Integer.toBinaryString(Math.abs(n));

        double[] powRes = new double[binaryString.length()];


        double temp = x;
        powRes[powRes.length -1] = temp;
        for (int i = powRes.length -2 ; i >= 0; i--) {
            powRes[i] = temp * temp;
            temp = powRes[i];
        }

        char[] chars = binaryString.toCharArray();


        double res = 1;
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == '1'){
                res = res * powRes[i];
            }
        }
        if(n > 0){
            return res;
        }else{
            return  1/res;
        }


    }

    /**
     * 官方写法
     * @param x
     * @param n
     * @return
     */
    public double myPow2(double x, int n) {
        long b = n;
        if(b < 0){
            x = 1 / x;
            b = - b;
        }
        double res = 1;
        while(b != 0){
            if(b % 2 != 0){
                res *= x;
            }
            b >>= 1;
            x *= x;
        }
        return res;
    }





    public static void main(String[] args) {
        myPow(2.0,-2);
    }
}
