package src.leetcode.easy;


import java.util.*;

/**
 *
 * 剑指offer
 * 从上到下打印二叉树 II
 *
 * https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof/
 *
 */


public class LevelOrder {

    /**
     * 堆方法，优先队列
     * @param args
     */
    public static void main(String[] args) {
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2);
        node2.left = node4;
        node2.right = node5;
//        Node node3 = new Node(5);
//        Node node1 = new Node(4, node2, node3);
        System.out.println(levelOrder(node2));

    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            for (int i = queue.size(); i > 0; i--) {
                TreeNode tree = queue.poll();
                if (tree.left != null) {
                    queue.add(tree.left);
                }
                if (tree.right != null) {
                    queue.add(tree.right);
                }
                level.add(tree.val);
            }
            result.add(level);
        }
        return result;
    }


}

 class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }


