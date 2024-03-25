package cn.xy.leetcode.middle.greed;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiangyu
 * @date 2024-03-26 0:44
 * 给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。
 * 注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
 * 返回一个表示每个字符串片段的长度的列表。
 * <p>
 * 示例 1：
 * 输入：s = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca"、"defegde"、"hijhklij" 。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 这样的划分是错误的，因为划分的片段数较少。
 * <p>
 * 示例 2：
 * 输入：s = "eccbbbbdec"
 * 输出：[10]
 * <p>
 * 提示：
 * 1 <= s.length <= 500
 * s 仅由小写英文字母组成
 */
public class A0763PartitionLabels {

    public static void main(String[] args) {
        A0763PartitionLabels a0763PartitionLabels = new A0763PartitionLabels();
        a0763PartitionLabels.partitionLabels("ababcbacadefegdehijhklij");
    }

    public List<Integer> partitionLabels(String s) {
        List<Integer> ans = new ArrayList<>();
        int[] maxLength = new int[26];
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            maxLength[charArray[i] - 97] = i;
        }
        int start = 0;
        int end = 0;
        for (int i = 0; i < charArray.length; i++) {
            // 当前字母要达到的最小的位置
            int temp = maxLength[charArray[i] - 97];
            if (temp > end) {
                end = temp;
            }
            // 切割位置
            if (i == end) {
                ans.add(end - start + 1);
                start = i + 1;
            }
        }

        return ans;
    }
}
