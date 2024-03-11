package cn.xy.leetcode.easy.tree;

import cn.xy.utils.TreeNode;

import java.util.*;

/**
 * @author XiangYu
 * @create2021-04-11-20:44 给定一个二叉树，检查它是否是镜像对称的。
 * <p>
 * 例如，二叉树[1,2,2,3,4,4,3] 是对称的。
 * 1
 * / \
 * 2   2
 * / \ / \
 * 3  4 4  3
 * <p>
 * 但是下面这个[1,2,2,null,3,null,3] 则不是镜像对称的:
 * 1
 * / \
 * 2   2
 * \   \
 * 3    3
 * <p>
 * 进阶：
 * 你可以运用递归和迭代两种方法解决这个问题吗？
 */
public class A0101SymmetricTree {

    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了6.21%的用户
     * 内存消耗：37.6 MB, 在所有 Java 提交中击败了19.98%的用户
     *
     * @param root
     * @return
     */
    public boolean isSymmetric2(TreeNode root) {

        if (root == null) {
            return true;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            LinkedList<TreeNode> tempQueue = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.poll();
                tempQueue.offer(temp);
                if (temp != null) {
                    queue.offer(temp.left);
                }
                if (temp != null) {
                    queue.offer(temp.right);
                }
            }
            if (!isBalance(tempQueue)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 是否平衡
     *
     * @param tempQueue
     * @return
     */
    private boolean isBalance(LinkedList<TreeNode> tempQueue) {
        int i = tempQueue.size() / 2;
        for (int j = 0; j < i; j++) {
            TreeNode left = tempQueue.poll();
            TreeNode right = tempQueue.pollLast();


            if (left != null && right != null) {
                if (left.val != right.val) {
                    return false;
                }
            } else {
                if (left != right) {
                    return false;
                }
            }

        }

        return true;
    }


    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.4 MB, 在所有 Java 提交中击败了69.27%的用户
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root != null) {
            return check(root.left, root.right);
        } else {
            return false;
        }

    }

    public boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
    }


    public boolean isSymmetric3(TreeNode root) {
        return check3(root, root);
    }

    public boolean check3(TreeNode u, TreeNode v) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(u);
        q.offer(v);
        while (!q.isEmpty()) {
            u = q.poll();
            v = q.poll();
            if (u == null && v == null) {
                continue;
            }
            if ((u == null || v == null) || (u.val != v.val)) {
                return false;
            }

            q.offer(u.left);
            q.offer(v.right);

            q.offer(u.right);
            q.offer(v.left);
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode root1 = new TreeNode(2);
        TreeNode root2 = new TreeNode(2);
        TreeNode root3 = new TreeNode(3);
        TreeNode root4 = new TreeNode(4);
        TreeNode root5 = new TreeNode(3);
        TreeNode root6 = new TreeNode(4);
        root.left = root2;
        root.right = root1;
        root1.left = root3;
        root1.right = root4;
        root2.left = root6;
        root2.right = root5;
        A0101SymmetricTree a0101SymmetricTree = new A0101SymmetricTree();
        System.out.println(a0101SymmetricTree.isSymmetric3(root));
    }

    public boolean isSymmetric4(TreeNode root) {
        // 有问题，因为 deque 不接受 null 值
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.push(root);
        deque.push(root);
        while (!deque.isEmpty()) {
            TreeNode treeNode1 = deque.pollFirst();
            TreeNode treeNode2 = deque.pollFirst();
            if (treeNode1 == null && treeNode2 == null) {
                continue;
            } else if (treeNode1 == null || treeNode2 == null) {
                return false;
            } else if (treeNode1.val != treeNode2.val) {
                return false;
            }
            deque.addLast(treeNode1.left);
            deque.addLast(treeNode2.right);

            deque.addLast(treeNode1.right);
            deque.addLast(treeNode2.left);
        }
        return true;
    }

    public boolean isSymmetric5(TreeNode root) {
        return dfs(root.left,root.right);
    }
    public boolean dfs(TreeNode left,TreeNode right) {
        if (left == null && right ==null) {
            return true;
        }else if (left == null || right == null) {
            return false;
        }else if (left.val != right.val) {
            return false;
        }
        return dfs(left.left,right.right) && dfs(left.right,right.left);
    }


}
