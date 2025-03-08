package cn.xy.leetcode.middle.binarytree;
/*
 * [117] 填充每个节点的下一个右侧节点指针 II
 * https://leetcode.cn/problems/populating-next-right-pointers-in-each-node-ii/description/
 *
 * 给定一个二叉树：
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL 。
 * 
 * 初始状态下，所有next 指针都被设置为 NULL 。
 *
 * 示例 1：
 * 输入：root = [1,2,3,4,5,null,7]
 * 输出：[1,#,2,3,#,4,5,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化输出按层序遍历顺序（由 next
 * 指针连接），'#' 表示每层的末尾。
 * 
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 *
 * 提示：
 * 树中的节点数在范围 [0, 6000] 内
 * -100 <= Node.val <= 100
 *
 * 进阶：
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序的隐式栈空间不计入额外空间复杂度。
 */

import cn.xy.utils.Node;

import java.util.ArrayDeque;
import java.util.Deque;

public class A0117PopulatingNextRightPointersInEachNodeII {
    public Node connect(Node root) {
        if(root == null){
            return null;
        }

        Deque<Node> queue = new ArrayDeque<>();
        Deque<Node> temp  = new ArrayDeque<>();

        queue.offer(root);
        Node preNode = null;

        while (!queue.isEmpty()) {
            Node node = queue.pollFirst();
            
             if(preNode != null){
                preNode.next = node;
             }

             preNode = node;
             if(node.left != null){
                temp.addLast(node.left);
             }

             if(node.right != null){
                temp.addLast(node.right);
             }
             
           
            if(queue.isEmpty()){
                preNode = null;
                queue = temp;
                temp = new ArrayDeque<>();
            }
        }

        return root;
    }

    /**
     * 使用size记录当前层级元素，减少空间占用
     * @param root
     * @return
     */
    public Node connect2(Node root) {
        if(root == null){
            return null;
        }
        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node preNode = null;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.pollFirst();
                if(preNode != null){
                    preNode.next = node;
                }
                preNode = node;
                if(node.left != null){
                    queue.addLast(node.left);
                }

                if(node.right != null){
                    queue.addLast(node.right);
                }
            }
        }

        return root;
    }
}