package src.leetcode.medium;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 *
 * 剑指 Offer 07. 重建二叉树
 * https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/
 *
 *
 */

public class BuildTree {



    public static void main(String[] args) {

        buildTree(new int[]{-1}, new int[]{-1});

    }

    private static Map<Integer, Integer> map = new HashMap<>();
    private static int index = 0;

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        TreeNode result = build(preorder, 0, inorder.length - 1);
        return result;
    }

    public static TreeNode build(int[] preorder, int start, int end) {
        TreeNode treeNode = new TreeNode(preorder[index]);
        Integer inOrderIdex = map.get(preorder[index]);
        if (inOrderIdex <= start || inOrderIdex >= end) {
            return treeNode;
        }
        if (index + 1 < preorder.length) {
            if (map.get(preorder[index + 1]) < inOrderIdex) {
                ++index;
                treeNode.left = build(preorder, start, index - 2);
            }
            if (map.get(preorder[index + 1]) > inOrderIdex) {
                ++index;
                treeNode.right = build(preorder, index, end);
            }
        }
        return treeNode;
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


}
