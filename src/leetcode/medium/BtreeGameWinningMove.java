package src.leetcode.medium;


import java.util.Arrays;

/**
 *
 * 二叉树着色游戏
 *
 * https://leetcode.cn/problems/binary-tree-coloring-game/
 */
public class BtreeGameWinningMove {


    public static void main(String[] args) {
        TreeNode treeNode8 = new TreeNode(8);
        TreeNode treeNode9 = new TreeNode(9);
        TreeNode treeNode10 = new TreeNode(10);
        TreeNode treeNode11 = new TreeNode(11);
        TreeNode treeNode4 = new TreeNode(4, treeNode8, treeNode9);
        TreeNode treeNode5 = new TreeNode(5, treeNode10, treeNode11);
        TreeNode treeNode2 = new TreeNode(2, treeNode4, treeNode5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode3 = new TreeNode(3, treeNode6, treeNode7);
        TreeNode treeNode1 = new TreeNode(1, treeNode2, treeNode3);
        System.out.println(btreeGameWinningMove(treeNode1, 11, 3));



//        TreeNode treeNode2 = new TreeNode(2);
//        TreeNode treeNode3 = new TreeNode(3);
//        TreeNode treeNode1 = new TreeNode(1, treeNode2, treeNode3);
//        System.out.println(btreeGameWinningMove(treeNode1, 3, 2));



//        TreeNode treeNode4 = new TreeNode(4);
//        TreeNode treeNode5 = new TreeNode(5);
//        TreeNode treeNode2 = new TreeNode(2, treeNode4, treeNode5);
//        TreeNode treeNode3 = new TreeNode(3);
//        TreeNode treeNode1 = new TreeNode(1, treeNode2, treeNode3);
//        System.out.println(btreeGameWinningMove(treeNode1, 5, 2));

    }

    static int[] countArray;
    static TreeNode left;
    static TreeNode right;

    public static boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        countArray = new int[n + 1];
        left = null;
        right = null;
        Arrays.fill(countArray, 0);
        getCount(root, x);
        int leftcount = left == null ? 0 : countArray[left.val];
        int rightcount = right == null ? 0 : countArray[right.val];
        int rootcount = countArray[root.val] - countArray[x];
        if (leftcount + rightcount + 1 < rootcount ||
                rootcount + rightcount + 1 < leftcount ||
                leftcount + rootcount + 1 < rightcount
        ) {
            return true;
        }
        return false;
    }


    public static int getCount(TreeNode root, int x) {
        if (root != null) {
            if (left == null && root.left != null && root.val == x) {
                left = root.left;
            }
            if (right == null && root.right != null && root.val == x) {
                right = root.right;
            }
            int sum = 1;
            sum += getCount(root.left, x);
            sum += getCount(root.right, x);
            countArray[root.val] = sum;
            return sum;
        } else {
            return 0;
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
     }






}