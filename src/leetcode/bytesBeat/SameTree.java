package src.leetcode.bytesBeat;


/**
 * 相同的树
 * https://leetcode.cn/problems/same-tree/
 *
 */


public class SameTree {

    public static void main(String[] args) {



    }


    /**
     * 我自己写的
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == q || (p != null && q != null && p.val == q.val)) {
            if (p != null && q != null && !isSameTree(p.left, q.left)) {
                return false;
            };
            if (p != null && q != null && !isSameTree(p.right, q.right)) {
                return false;
            }

        } else {
            return false;
        }
        return true;
    }





}

class TreeNode {
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

