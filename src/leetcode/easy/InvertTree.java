package src.leetcode.easy;



/**
 *
 * 翻转二叉树
 *
 * https://leetcode-cn.com/problems/invert-binary-tree/
 *
 *
 */

public class InvertTree {



    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(9);
        TreeNode node5 = new TreeNode(2, node1, node2);
        TreeNode node6 = new TreeNode(7, node3, node4);
        TreeNode node7 = new TreeNode(4, node5, node6);
        System.out.println(node7);
        System.out.println(invertTree(node7));
    }

    public static TreeNode invertTree(TreeNode root) {
        invertTreeReverse(root);
        return root;
    }

    public static void invertTreeReverse(TreeNode root) {
        if (root != null && (root.left != null || root.right != null)) {
            invertTreeReverse(root.left);
            invertTreeReverse(root.right);
            TreeNode right = root.right;
            TreeNode left = root.left;
            root.left = right;
            root.right = left;
        }
    }





    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }


}
