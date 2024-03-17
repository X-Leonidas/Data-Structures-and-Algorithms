package cn.xy.leetcode.middle.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiangyu
 * @date 2024-03-15 0:47
 * <p>
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * 你可以按 任何顺序 返回答案。
 * <p>
 * 示例 1：
 * 输入：n = 4, k = 2
 * 输出：
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 * <p>
 * 示例 2：
 * 输入：n = 1, k = 1
 * 输出：[[1]]
 * <p>
 * 提示：
 * 1 <= n <= 20
 * 1 <= k <= n
 */
public class A0077Combinations {

    public static void main(String[] args) {
        A0077Combinations a0077Combinations = new A0077Combinations();
        a0077Combinations.combine(4, 2);
    }


    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        if (k > n) {
            return ans;
        }
        List<Integer> outPut = new ArrayList<>();
        backtrack(n, k, ans, outPut, 1);
        return ans;
    }

    private void backtrack(int n, int k, List<List<Integer>> ans, List<Integer> outPut, int index) {
        if (k == 0) {
            ans.add(new ArrayList<>(outPut));
            return;
        }
        // 可以剪枝 , 已经填充不满 outPut的情况，但是k就不能变了 要使用 k == outPut.size() 来作为判断终止的条件
        //  i <= n - (k - outPut.size()) + 1
        for (int i = index; i <= n; i++) {
            outPut.add(i);
            backtrack(n, k - 1, ans, outPut, i + 1);
            outPut.remove(outPut.size() - 1);
        }
    }

}
