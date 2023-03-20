package src.leetcode.easy;


/**
 * https://leetcode.cn/problems/evaluate-boolean-binary-tree/submissions/
 * 计算布尔二叉树的值
 */


public class EvaluateTree {


    public static void main(String[] args) {
//        boolean b = evaluateTree();
//        System.out.println(b);
    }



    public static boolean evaluateTree(TreeNode root) {
        if (root.val == 2) {
            return evaluateTree(root.left) || evaluateTree(root.right);
        } else if (root.val == 3) {
            return evaluateTree(root.left) && evaluateTree(root.right);
        } else {
            return root.val == 0 ? false : true;
        }
    }

}