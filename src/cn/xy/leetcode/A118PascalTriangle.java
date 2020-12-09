package cn.xy.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author XiangYu
 * @create2020-12-06-17:15
 * 1.给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * 2.给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 *      进阶：你可以优化你的算法到 O(k) 空间复杂度吗？
 */
public class A118PascalTriangle {
    public static void main(String[] args) {
        List<List<Integer>> generate = generate(6);
        for (int i = 0; i < generate.size(); i++) {
            List<Integer> integers = generate.get(i);
            for (int j = 0; j < integers.size(); j++) {
                System.out.print(integers.get(j)+ " ");
            }

            System.out.println();
        }
    }

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了58.77%的用户
     * 内存消耗：36.2 MB, 在所有 Java 提交中击败了80.57的用户
     * @param numRows
     * @return
     */
    public static List<List<Integer>> generate(int numRows) {
        //生成杨辉三角的行数
        List<List<Integer>> lists = new ArrayList<>(numRows);

        for (int i = 0; i < numRows; i++) {
            //每一列有多少个,设置固定数组
            List<Integer> list = Arrays.asList(new Integer[i+1]);
            int size = i+1;

            for (int j = 0; j < size; j++) {
                if(j == 0 || j == size -1){
                    list.set(j,1);
                }else{
                    int length = (size - 1) /2;
                    for (int k = 1; k <= length; k++) {
                        list.set(k,lists.get(i -1).get(k) + lists.get(i-1).get(k-1));
                        list.set(size - 1 - k,lists.get(i -1).get(k) + lists.get(i-1).get(k-1));
                    }
                    //循环完成过后直接赋值到末尾阶段
                    j = size -2;
                }
            }
            lists.add(list);
        }

        return  lists;
    }


    /**
     * 官方题解相似的解法
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.5 MB, 在所有 Java 提交中击败了29.54%的用户
     * @param numRows
     * @return
     */
    public static List<List<Integer>> generate2(int numRows) {
        //创建一个Lists来存储list
        List<List<Integer>> lists = new ArrayList<>();

        for(int i = 0; i < numRows; i++) {
            //每次创建一个list来保存
            List<Integer> list = new ArrayList<>();

            for (int j = 0; j <= i; j++) {
                //判断是不是第一个和最后一个
                if (j == 0 || j== i) {
                    //如果是直接添加1
                    list.add(1);
                }else {
                    //获取上一行的list,然后获取它的下表
                    //这里j是不会等于0的,因为上面已经判断了,因此如果这个成立
                    //比如是第三行i为2,j为1的时候
                    //i-1也就是2-1,就获取了第二行的,然后获取它的值j-1也就是1-1
                    //也就获取了第二行第一个,得到1,然后加同样的第二行的第二个
                    //i-1也就是2-1,并且j为1也就是第二行的第二个
                    //不需要当心越界的情况,因为如果是最后一个,同样不会执行这个语句
                    list.add(lists.get(i-1).get(j-1)+lists.get(i-1).get(j));
                }
            }
            //把list的内容添加到lists中
            lists.add(list);
        }
        return lists;
    }


    /**
     * 2
     * @param rowIndex
     * @return
     * 执行用时：1 ms, 在所有 Java 提交中击败了78.99%的用户
     * 内存消耗：36.4 MB, 在所有 Java 提交中击败了40.06%的用户
     * DP
     */

    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>(rowIndex+ 1);
        for (int i = 0; i <= rowIndex; i++) {
            res.add(1);
            for (int j = i - 1; j > 0; j--) {
                res.set(j, res.get(j) + res.get(j - 1));
            }
        }
        return res;

    }

    /**
     *执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36 MB, 在所有 Java 提交中击败了89.93%的用户
     */

    public List<Integer> getRow3(int rowIndex) {
        List<Integer> ans = new ArrayList<>();
        int N = rowIndex;
        long pre = 1;
        ans.add(1);
        for (int k = 1; k <= N; k++) {
            if(k <= (N+1)/2){
                long cur = pre * (N - k + 1) / k;
                ans.add((int) cur);
                pre = cur;
            }else{
                ans.add(ans.get(N-k));
            }

        }
        return ans;
    }
}
