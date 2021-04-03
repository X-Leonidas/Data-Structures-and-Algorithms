package cn.xy.leetcode.easy.tree;

/**
 * @author XiangYu
 * @create2021-04-02-17:41 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 * <p>
 * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * Tree 1                     Tree 2
 * 1                         2
 * / \                       / \
 * 3   2                     1   3
 * /                           \   \
 * 5                             4   7
 * 输出:
 * 合并后的树:
 * 3
 * / \
 * 4   5
 * / \   \
 * 5   4   7
 * 注意: 合并必须从两个树的根节点开始。
 */
public class A0617MergeTwoBinaryTrees {

    /**执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了91.72%的用户
     * @param root1
     * @param root2
     * @return
     */
    private static TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 != null) {
            root1.val = root1.val + root2.val;
            process(root1, root2);
        }
        return  root1;
    }

    private static void process(TreeNode source, TreeNode target) {
        //用target树不全source树
        if(source.right == null && target.right != null){
            source.right = target.right;
            target.right = null;
        }

        if(source.left == null && target.left != null){
            source.left = target.left;
            target.left = null;
        }

        //到达叶节点
        if(source.left == null && source.right == null){
            return;
        }

        if(source.right != null && target.right != null){
            source.right.val = source.right.val + target.right.val;
            process(source.right,target.right);
        }
        if(source.left != null && target.left != null){
            source.left.val = source.left.val + target.left.val;
            process(source.left,target.left);
        }
    }

    public static void main(String[] args) {
        TreeNode head1 = new TreeNode(9);
        TreeNode head11 = new TreeNode(-1);
        head1.left = head11;

        TreeNode head2 = new TreeNode(-1);
        TreeNode head21 = new TreeNode(-2);
        TreeNode head22 = new TreeNode(0);
        head2.left = head21;
        head2.right = head22;
        mergeTrees(head1, head2);

    }

    /**
     * 力扣写法
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        TreeNode merged = new TreeNode(t1.val + t2.val);
        merged.left = mergeTrees2(t1.left, t2.left);
        merged.right = mergeTrees2(t1.right, t2.right);
        return merged;
    }
}
