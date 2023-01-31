package src.leetcode.medium;

/**
 * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/?favorite=2cktkvj
 *
 * 删除链表的倒数第 N 个结点
 *
 */
public class removeNthFromEnd {


    public static void main(String[] args) {
        ListNode node1 = new ListNode(5);
        ListNode node2 = new ListNode(4, node1);
        ListNode node3 = new ListNode(3, node2);
        ListNode node4 = new ListNode(2, node3);
        ListNode node5 = new ListNode(1, node4);

//        ListNode node1 = new ListNode(2);
//        ListNode node2 = new ListNode(1, node1);
        ListNode listNode = removeNthFromEnd(node2, 2);
        System.out.println(listNode);
    }



    public static ListNode removeNthFromEnd(ListNode head, int n) {
        int start = 0;
        ListNode temp = new ListNode(0, head);
        ListNode temp1= temp;
        ListNode temp2 = temp;

        while (temp1.next != null && start < n) {
            temp1 = temp1.next;
            start++;
        }
        while (temp1.next != null) {
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        temp2.next = temp2.next.next;
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
