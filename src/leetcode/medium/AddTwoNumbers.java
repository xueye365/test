package leetcode.medium;


import leetcode.other.ListNode;

/**
 * 链表两个数相加
 *
 * https://leetcode-cn.com/problems/add-two-numbers/
 *
 */
public class AddTwoNumbers {

    public static void main(String[] args) {
//        ListNode node1 = new ListNode(3);
//        ListNode node2 = new ListNode(4, node1);
//        ListNode node3 = new ListNode(2, node2);
//        ListNode node4 = new ListNode(4);
//        ListNode node5 = new ListNode(6, node4);
//        ListNode node6 = new ListNode(5, node5);
//        ListNode result = addTwoNumbers(node3, node6);


//        ListNode node1 = new ListNode(0);
//        ListNode node4 = new ListNode(0);
//        ListNode result = addTwoNumbers(node1, node4);
//        System.out.println(result);


        ListNode node1 = new ListNode(9);
        ListNode node2 = new ListNode(9, node1);
        ListNode node3 = new ListNode(9, node2);
        ListNode node4 = new ListNode(9, node3);
        ListNode node5 = new ListNode(9, node4);
        ListNode node6 = new ListNode(9, node5);
        ListNode node7 = new ListNode(9, node6);
        ListNode xnode1 = new ListNode(9);
        ListNode xnode2 = new ListNode(9, xnode1);
        ListNode xnode3 = new ListNode(9, xnode2);
        ListNode xnode4 = new ListNode(9, xnode3);
        ListNode result = addTwoNumbers(node7, xnode4);
        System.out.println(result);

    }


    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l11 = l1;
        while (l11.next != null) {
            l11 = l11.next;
            if (l11 == null && l11.val == 0) {
                return l2;
            }
        }
        ListNode l22 = l2;
        while (l22.next != null) {
            l22 = l22.next;
            if (l22 == null && l22.val == 0) {
                return l1;
            }
        }
        boolean moreThanTen = false;
        ListNode node = new ListNode();
        ListNode result = node;
        while (l1 != null && l2 != null) {
            int sum;
            if (moreThanTen) {
                sum = l1.val + l2.val + 1;
            } else {
                sum = l1.val + l2.val;
            }
            if (sum >= 10) {
                moreThanTen = true;
                sum = sum - 10;
            } else {
                moreThanTen = false;
            }
            result.next = new ListNode(sum);
            l1 = l1.next;
            l2 = l2.next;
            result = result.next;
        }
        while (l1 != null) {
            int sum;
            if (moreThanTen) {
                sum = l1.val + 1;
            } else {
                sum = l1.val;
            }
            if (sum >= 10) {
                moreThanTen = true;
                sum = sum - 10;
            } else {
                moreThanTen = false;
            }
            result.next = new ListNode(sum);
            l1 = l1.next;
            result = result.next;
        }
        while (l2 != null) {
            int sum;
            if (moreThanTen) {
                sum = l2.val + 1;
            } else {
                sum = l2.val;
            }
            if (sum >= 10) {
                moreThanTen = true;
                sum = sum - 10;
            } else {
                moreThanTen = false;
            }
            result.next = new ListNode(sum);
            l2 = l2.next;
            result = result.next;
        }
        if (moreThanTen) {
            result.next = new ListNode(1);
        }
        return node.next;
    }


}