package src.leetcode.easy;


/**
 * 字节跳动
 *
 * 反转链表
 * https://leetcode-cn.com/problems/reverse-linked-list/
 * https://leetcode-cn.com/explore/interview/card/bytedance/244/linked-list-and-tree/1038/
 *
 */
public class ReverseLinkedList {

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

    public static void main(String[] args) {
        ListNode node1 = new ListNode(9);
        ListNode node2 = new ListNode(1, node1);
        ListNode node3 = new ListNode(0, node2);
        System.out.println(node3.toString());
        ListNode node4 = new ListNode(6);
        ListNode node5 = new ListNode(5, node4);
        ListNode node6 = new ListNode(4, node5);
        System.out.println(node6.toString());
        System.out.println(reverseList(node3));
    }


    public static ListNode reverseList(ListNode head) {
        ListNode listNode = new ListNode();
        while (head != null) {
            ListNode headTemp = head.next;
            ListNode listNodeTemp = listNode.next;
            listNode.next = head;
            listNode.next.next = listNodeTemp;
            head = headTemp;
        }
        return listNode.next;
    }






}
