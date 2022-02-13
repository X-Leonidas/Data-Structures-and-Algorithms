package xy.codinginterviews;

/**
 * @author XiangYu
 * @create2020-12-22-15:14
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 *
 * 示例 1:
 *
 * 输入: n = 1
 * 输出: [1,2,3,4,5,6,7,8,9]
 *
 */
public class A017OneToN {
    public static void main(String[] args) {
        printNumbers(3);
    }


    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：46.6 MB, 在所有 Java 提交中击败了67.92%的用户
     * @param n
     * @return
     */
    public static  int[] printNumbers(int n) {

//        int sum = 9;
//
//        for (int i = 1; i < n; i++) {
//            sum = sum * 10 + 9;
//        }
        int sum = (int) (Math.pow(10,n) -1);



        int[] ints = new int[sum];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = i+1;
        }

        return  ints;
    }







    //考虑大数时


    StringBuilder res;
    int count = 0, n;
    char[] num, loop = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    public String printNumbers2(int n) {
        this.n = n;
        // 数字字符串集
        res = new StringBuilder();
        // 定义长度为 n 的字符列表
        num = new char[n];
        // 开启全排列递归
        dfs(0);
        // 删除最后多余的逗号
        res.deleteCharAt(res.length() - 1);
        // 转化为字符串并返回
        return res.toString();
    }
    void dfs(int x) {
        // 终止条件：已固定完所有位
        if(x == n) {
            // 拼接 num 并添加至 res 尾部，使用逗号隔开
            res.append(String.valueOf(num) + ",");
            return;
        }
        // 遍历 ‘0‘ - ’9‘
        for(char i : loop) {
            // 固定第 x 位为 i
            num[x] = i;
            // 开启固定第 x + 1 位
            dfs(x + 1);
        }
    }


}
