package cn.xy.codinginterviews;

import cn.xy.utils.TreeNode;

import java.util.HashMap;

/**
 * @author XiangYu
 * @create2021-05-05-15:31
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 *
 *  
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *  
 *
 * 限制：
 *
 * 0 <= 节点个数 <= 5000
 *
 *
 * 前序遍历性质： 节点按照 [ 根节点 | 左子树 | 右子树 ] 排序。
 * 中序遍历性质： 节点按照 [ 左子树 | 根节点 | 右子树 ] 排序。
 *
 */
public class A007RebuildTreeNode {
    /**
     *   标记中序遍历
     */

    HashMap<Integer, Integer> map = new HashMap<>();
    /**
     *  保留的先序遍历，方便递归时依据索引查看先序遍历的值
     */
    int[] preorder;

    /**
     *
     * @param preorder  前序遍历
     * @param inorder   中序遍历
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        //将中序遍历的值及索引放在map中，方便递归时获取左子树与右子树的数量及其根的索引
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        //三个索引分别为
        //当前根的的索引
        //递归树的左边界，即数组左边界
        //递归树的右边界，即数组右边界
        return recur(0,0,inorder.length-1);
    }

    TreeNode recur(int pre_root, int in_left, int in_right){
        // 相等的话就是自己
        if(in_left > in_right) {
            return null;
        }
        //获取root节点
        TreeNode root = new TreeNode(preorder[pre_root]);
        //获取在中序遍历中根节点所在索引，以方便获取左子树的数量
        int idx = map.get(preorder[pre_root]);

        //左子树的根的索引为先序中的根节点+1
        //递归左子树的左边界为原来的中序in_left
        //递归右子树的右边界为中序中的根节点索引-1
        root.left = recur(pre_root+1, in_left, idx-1);

        //右子树的根的索引为先序中的 当前根位置 + 左子树的数量 + 1
        //递归右子树的左边界为中序中当前根节点+1
        //递归右子树的右边界为中序中原来右子树的边界
        root.right = recur(pre_root + (idx - in_left) + 1, idx+1, in_right);
        return root;

    }
}
