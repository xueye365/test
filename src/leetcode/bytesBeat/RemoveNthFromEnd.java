package src.leetcode.bytesBeat;



/**
 *
 * 删除链表的倒数第 N 个结点
 *
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 *
 */

public class RemoveNthFromEnd {



    public static void main(String[] args) {
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(4, node1);
        ListNode node3 = new ListNode(2, node2);
        System.out.println(node3);
        ListNode result = removeNthFromEnd(node3, 3);
        System.out.println(result);
    }

    /**
     * 未通过
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = new ListNode(0, head);
        ListNode temp = slow;

        while (fast.next != null) {
            fast = fast.next;
            n--;
            if (n <= 0) {
                slow = slow.next;
            }
        }
        if (slow.next.next == null) {
            slow.next = null;
        } else {
            if (slow.next.next.next != null) {
                slow.next.next = slow.next.next.next;
            } else {
                slow.next.next = null;
            }
        }
        return temp.next;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

}
