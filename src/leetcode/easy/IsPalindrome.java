package src.leetcode.easy;


import src.leetcode.other.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 回文链表
 *
 * https://leetcode-cn.com/problems/palindrome-linked-list/
 *
 *
 */

public class IsPalindrome {



    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2, node1);
        ListNode node3 = new ListNode(2, node2);
        ListNode node4 = new ListNode(1, node3);
        System.out.println(isPalindrome(node4));
    }


    /**
     * 快慢指针，找到后半部分，反转，然后对比
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head) {
        ListNode half = endOfFirstHalf(head);
        ListNode next = reverseList(half);
        boolean result = true;
        while (result && next != null) {
            if (head.val == next.val) {
                head = head.next;
                next = next.next;
            } else {
                return false;
            }
        }
        return result;
    }

    private static ListNode reverseList(ListNode head) {
        ListNode node = new ListNode();
        ListNode temp = head;
        while (temp != null) {
            ListNode tempNext = temp.next;
            temp.next = node.next;
            node.next = temp;
            temp = tempNext;
        }
        return node.next;
    }

    public static ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }


    /**
     * 使用list装数据，然后再对比
     * @param head
     * @return
     */
    public static boolean isPalindrome2(ListNode head) {
        List<ListNode> nodes = new ArrayList<>();
        ListNode list = head;
        while (list != null) {
            nodes.add(list);
            list = list.next;
        }
        for (int i = nodes.size() - 1; i >= 0; i--) {
            if (head.val == nodes.get(i).val) {
                head = head.next;
                continue;
            } else {
                return false;
            }
        }
        return true;
    }


    /**
     * 深度优先遍历
     * @param head
     * @return
     */

    private static ListNode temp2;

    public static boolean isPalindrome3(ListNode head) {
        ListNode temp = head;
        temp2 = head;
        return check(temp);
    }

    public static boolean check(ListNode temp) {
        boolean result = true;
        if (temp != null && temp.next != null) {
            result = check(temp.next);
        }
        if (result && temp.val == temp2.val) {
            temp2 = temp2.next;
            return true;
        } else {
            return false;
        }
    }




}
