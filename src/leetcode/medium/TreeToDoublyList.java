package src.leetcode.medium;


import java.util.Stack;

/**
 *
 * 剑指offer
 * 二叉搜索树与双向链表
 *
 * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/
 *
 */


public class TreeToDoublyList {

    /**
     * 堆方法，优先队列
     * @param args
     */
    public static void main(String[] args) {
        Node node4 = new Node(1);
        Node node5 = new Node(3);
        Node node2 = new Node(2, node4, node5);
//        Node node3 = new Node(5);
//        Node node1 = new Node(4, node2, node3);
        System.out.println(treeToDoublyList(node2));

    }
    public static Node head = new Node();
    public static Node pre = head;
    public static Node back = head;

    public static Node treeToDoublyList(Node root) {
        trice(root);
        head.right.left = back;
        pre.right = head.right;
        return head.right;
    }


    public static void trice(Node root) {
        if (root == null) return ;
        trice(root.left);
        pre.right = root;
        pre = pre.right;
        pre.left = back;
        back = back.right;
        trice(root.right);
    }


    /**
     * 杨传的方法
     * @param root
     * @return
     */
    public Node treeToDoublyList2(Node root) {
        if (root == null) {
            return null;
        }
        Stack<Node> stack = new Stack<>();
        Node cur = root,pre=null,head=null;
        while(cur != null || !stack.isEmpty()){
            while(cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            if (!stack.isEmpty()) {
                cur = stack.pop();
                if (pre != null) {
                    pre.right = cur;
                    cur.left = pre;
                    pre = cur;
                } else {
                    head = cur;
                    pre = cur;
                }
                cur = cur.right;
            }
        }
        head.left = pre;
        pre.right = head;
        return head;
    }

}

class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }

//    @Override
//    public String toString() {
//        return "Node{" +
//                "val=" + val +
//                ", left=" + left +
//                ", right=" + right +
//                '}';
//    }
}



