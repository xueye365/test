package src.leetcode.easy;


import src.leetcode.other.ListNode;

/**
 *
 * 环形链表
 *
 * https://leetcode-cn.com/problems/linked-list-cycle/submissions/
 *
 */
public class HasCycle {


    public static void main(String[] args) {

    }

    public static boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode quick = head.next;
        ListNode slow = head;
        while (quick != null && slow != null && quick.next != null) {
            if (quick == slow) {
                return true;
            }
            quick = quick.next.next;
            slow = slow.next;
        }
        return false;
    }

}
