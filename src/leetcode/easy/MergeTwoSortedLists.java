package src.leetcode.easy;

import src.leetcode.other.ListNode;

/**
 * 合并两个有序链表
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 */
public class MergeTwoSortedLists {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(9);
        ListNode node2 = new ListNode(1, node1);
        ListNode node3 = new ListNode(0, node2);
        System.out.println(node3);
        ListNode node4 = new ListNode(6);
        ListNode node5 = new ListNode(5, node4);
        ListNode node6 = new ListNode(4, node5);
        System.out.println(node6);
        ListNode result = mergeTwoLists(node3, node6);
        System.out.println(result);
    }


    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(-1);
        ListNode prehead = result;
        while (l1 != null && l2 != null) {
            if (l1.val >= l2.val) {
                prehead.next = l2;
                l2 = l2.next;
            } else {
                prehead.next = l1;
                l1 = l1.next;
            }
            prehead = prehead.next;
        }
        prehead.next = l1 != null ? l1 : l2;
        return result.next;
    }

}
