package cn.xy.leetcode.middle.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XiangYu
 * @create2021-04-13-23:43
 *
 *  给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 示例 2：
 *
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 *
 */
public class A0078Subsets {

    private  List<List<Integer>> lists = new ArrayList<List<Integer>>();
    ArrayList<Integer> list = new ArrayList<>();

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%
     * 的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了34.55%的用户
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        process(nums,0);
        return lists;
    }

    private void process(int[] nums,int cur){
      if(nums.length  == cur){
          lists.add(new ArrayList<>(list));
          return;
      }
      list.add(nums[cur]);
      process(nums,cur+1);
      list.remove(list.size() -1);
      process(nums,cur+1);
    }


    public List<List<Integer>> subsets2(int[] nums) {


        int n = nums.length;
        //1 << n 每一位都有2种可能，所以有多少位就加多少个2，就是1左移多少位
        for (int mask = 0; mask < (1 << n); ++mask) {
            list.clear();
            for (int i = 0; i < n; ++i) {
                if ((mask & (1 << i)) != 0) {
                    list.add(nums[i]);
                }
            }
            lists.add(new ArrayList<Integer>(list));
        }
        return lists;
    }



}
