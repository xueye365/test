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
        ListNode sentinal = new ListNode(0, head);
        ListNode first = head;
        ListNode second = sentinal;
        for (int i = 0; i < n; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return sentinal.next;

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
