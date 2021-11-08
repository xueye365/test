package src.leetcode.bytesBeat;



/**
 *
 * 字节跳动
 * 排序链表
 *
 * https://leetcode-cn.com/problems/sort-list/
 * https://leetcode-cn.com/explore/interview/card/bytedance/244/linked-list-and-tree/1040/
 *
 */
public class SortList {


    public static void main(String[] args) {
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(4, node1);
        ListNode node3 = new ListNode(2, node2);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(6, node4);
        ListNode node6 = new ListNode(5, node5);
        System.out.println(node3);
        ListNode result = sortList(node3);
        System.out.println(result);


//        ListNode node1 = new ListNode(9);
//        ListNode node2 = new ListNode(9, node1);
//        ListNode node3 = new ListNode(9, node2);
//        ListNode node4 = new ListNode(9, node3);
//        ListNode node5 = new ListNode(9, node4);
//        ListNode node6 = new ListNode(9, node5);
//        ListNode node7 = new ListNode(9, node6);
//        ListNode xnode1 = new ListNode(9);
//        ListNode xnode2 = new ListNode(9, xnode1);
//        ListNode xnode3 = new ListNode(9, xnode2);
//        ListNode xnode4 = new ListNode(9, xnode3);
//        ListNode result = sortList(node7);
//        System.out.println(result);

    }



    public static ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    public static ListNode sortList(ListNode head, ListNode tail) {
        if(head.next == tail) {
            return head;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        tail = fast;
        ListNode listNode1 = sortList(head, mid);
        ListNode listNode2 = sortList(mid, tail);
        return merge(listNode1, listNode2);
    }

    public static ListNode merge(ListNode head1, ListNode head2) {
        ListNode result = new ListNode();
        ListNode result2 = result;
        while (head1 != null && head2 != null) {
            while (head2 != null && head1.val >= head2.val) {
                result.next = head2;
                head2 = head2.next;
                result = result.next;
            }
            while (head1 != null && head2 != null && head2.val >= head1.val) {
                result.next = head1;
                head1 = head1.next;
                result = result.next;
            }
        }
        if (head1 != null) {
            result.next = head1;
        }
        if (head2 != null) {
            result.next = head2;
        }
        return result2.next;
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