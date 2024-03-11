package cn.xy.leetcode.easy.tree;

import cn.xy.utils.TreeNode;

/**
 * @author XiangYu
 * @create2021-04-07-10:21 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 * 示例 :
 * 给定二叉树
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * 返回3, 它的长度是路径 [4,2,1,3] 或者[5,2,1,3]。
 * <p>
 * <p>
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 */
public class A0543DiameterOfBinaryTree {

    private class ReturnType {
        int max = 0;
        int h = 0;

        public ReturnType(int len, int max) {
            this.h = len;
            this.max = max;
        }
    }

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了17.65%的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了65.50%的用户
     *
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return 0;
        }

        ReturnType left = process(root.left);
        ReturnType right = process(root.right);
        return Math.max(Math.max(left.h + right.h + 1, left.max), right.max) - 1;
    }

    private ReturnType process(TreeNode head) {
        if (head == null) {
            return new ReturnType(0, 0);
        }
        ReturnType left = process(head.left);
        ReturnType right = process(head.right);

        int max = Math.max(Math.max(left.h + right.h + 1, left.max), right.max);
        return new ReturnType(Math.max(right.h, left.h) + 1, max);
    }

    //官网写法
    int ans;

    public int diameterOfBinaryTree2(TreeNode root) {
        ans = 1;
        depth(root);
        return ans - 1;
    }

    public int depth(TreeNode node) {
        if (node == null) {
            // 访问到空节点了，返回0
            return 0;
        }
        // 左儿子为根的子树的深度
        int L = depth(node.left);
        // 右儿子为根的子树的深度
        int R = depth(node.right);
        // 计算d_node即L+R+1 并更新ans
        ans = Math.max(ans, L + R + 1);
        // 返回该节点为根的子树的深度
        return Math.max(L, R) + 1;
    }


    private int ans2;

    public int diameterOfBinaryTree3(TreeNode root) {
        ans2 = 0;
        depth2(root);
        return ans2;
    }

    private int depth2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int lDepth = depth2(root.right);
        int rDepth = depth2(root.left);
        ans2 = Math.max(ans2, lDepth + rDepth);
        return Math.max(lDepth, rDepth) + 1;
    }

}
