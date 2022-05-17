package cn.xy.algorithm.BinaryTree.traverse;

import cn.xy.utils.TreeNode;

import java.util.ArrayDeque;

/**
 * @author XiangYu
 * @create2021-03-29-18:33
 * 广度优先遍历
 */
public class BFS {
    void bfs(TreeNode root) {
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.getLeft() != null) {
                queue.offer(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.offer(node.getRight());
            }
        }
    }


}
