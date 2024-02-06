package cn.xy.leetcode.middle.binarytree;

import cn.xy.utils.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author XiangYu
 * @create2021-04-15-21:07 给定一个不含重复元素的整数数组 nums 。一个以此数组直接递归构建的 最大二叉树 定义如下：
 * <p>
 * 二叉树的根是数组 nums 中的最大元素。
 * 左子树是通过数组中 最大值左边部分 递归构造出的最大二叉树。
 * 右子树是通过数组中 最大值右边部分 递归构造出的最大二叉树。
 * 返回有给定数组 nums 构建的 最大二叉树 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：nums = [3,2,1,6,0,5]
 * 输出：[6,3,5,null,2,0,null,null,1]
 * 解释：递归调用如下所示：
 * - [3,2,1,6,0,5] 中的最大值是 6 ，左边部分是 [3,2,1] ，右边部分是 [0,5] 。
 * - [3,2,1] 中的最大值是 3 ，左边部分是 [] ，右边部分是 [2,1] 。
 * - 空数组，无子节点。
 * - [2,1] 中的最大值是 2 ，左边部分是 [] ，右边部分是 [1] 。
 * - 空数组，无子节点。
 * - 只有一个元素，所以子节点是一个值为 1 的节点。
 * - [0,5] 中的最大值是 5 ，左边部分是 [0] ，右边部分是 [] 。
 * - 只有一个元素，所以子节点是一个值为 0 的节点。
 * - 空数组，无子节点。
 * 示例 2：
 * <p>
 * <p>
 * 输入：nums = [3,2,1]
 * 输出：[3,null,2,null,1]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 * nums 中的所有整数 互不相同
 * <p>
 * <p>
 * 思路：建立两个表，分别是index位置上左边第一个比它大的数和右边第一个比它大的数 （可以用栈来减少操作）
 * 比较两个表，其中较小的数为index的根节点
 */
public class A0654MaximumBinaryTree {


    public int[] buildMaxTree(int[] arr, int n) {
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
                int index = stack.pop();
                if (stack.isEmpty()) {
                    res[index] = i;
                } else {
                    res[index] = arr[stack.peek()] < arr[i] ? stack.peek() : i;
                }
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int index = stack.pop();
            if (stack.isEmpty()) {
                res[index] = -1;
            } else {
                res[index] = stack.peek();
            }
        }

        return res;
    }


    /**
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {

        if (nums.length == 0) {
            return null;
        }

        //用来存储节点
        HashMap<Integer, TreeNode> mapRes = new HashMap<>();


        //用来左边比它大的数
        //key是当前值
        HashMap<Integer, Integer> leftMax = new HashMap<>();
        HashMap<Integer, Integer> righttMax = new HashMap<>();

        for (int num : nums) {
            mapRes.put(num, new TreeNode(num));
        }

        LinkedList<Integer> stack = new LinkedList<>();
        stack.offer(nums[0]);
        leftMax.put(nums[0], -1);
        for (int i = 1; i < nums.length; i++) {

            while (!stack.isEmpty() && stack.peekLast() < nums[i]) {
                stack.pollLast();
            }

            if (stack.isEmpty()) {
                leftMax.put(nums[i], -1);

            } else {
                leftMax.put(nums[i], stack.peekLast());
            }
            stack.offer(nums[i]);

        }
        stack.clear();
        int length = nums.length;
        stack.offer(nums[length - 1]);
        righttMax.put(nums[length - 1], -1);

        for (int i = length - 2; i >= 0; i--) {

            while (!stack.isEmpty() && stack.peekLast() < nums[i]) {
                stack.pollLast();
            }

            if (stack.isEmpty()) {
                righttMax.put(nums[i], -1);
            } else {
                righttMax.put(nums[i], stack.peekLast());
            }
            stack.offer(nums[i]);
        }

        //定义头节点
        TreeNode root = null;
        for (int num : nums) {
            TreeNode temp = null;
            if (righttMax.get(num) > 0 && leftMax.get(num) > 0) {
                temp = mapRes.get(Math.min(righttMax.get(num), leftMax.get(num)));
            } else if (righttMax.get(num) < 0 && leftMax.get(num) > 0) {
                temp = mapRes.get(leftMax.get(num));
            } else if (righttMax.get(num) > 0 && leftMax.get(num) < 0) {
                temp = mapRes.get(righttMax.get(num));
            } else {
                root = mapRes.get(num);
                continue;
            }
            if (temp.left == null) {
                temp.left = mapRes.get(num);
            } else {
                temp.right = mapRes.get(num);
            }
        }

        return root;
    }


    /**
     * 官方题解
     *
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree2(int[] nums) {
        return construct(nums, 0, nums.length);
    }

    public TreeNode construct(int[] nums, int l, int r) {
        if (l == r) {
            return null;
        }
        int max_i = max(nums, l, r);
        TreeNode root = new TreeNode(nums[max_i]);
        root.left = construct(nums, l, max_i);
        root.right = construct(nums, max_i + 1, r);
        return root;
    }
    /**
     *  拿到范围内的最大值
     */

    public int max(int[] nums, int l, int r) {
        int max_i = l;
        for (int i = l; i < r; i++) {
            if (nums[max_i] < nums[i]){
                max_i = i;
            }
        }
        return max_i;
    }


    public static void main(String[] args) {
        int[] temp = {3, 2, 1, 6, 0, 5};
        new A0654MaximumBinaryTree().constructMaximumBinaryTree(temp);
    }

}
