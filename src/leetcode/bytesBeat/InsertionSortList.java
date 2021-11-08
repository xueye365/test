package src.leetcode.bytesBeat;



/**
 *
 * 对链表进行插入排序
 *
 * https://leetcode-cn.com/problems/insertion-sort-list/
 *
 */
public class InsertionSortList {


    public static void main(String[] args) {
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(4, node1);
        ListNode node3 = new ListNode(2, node2);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(6, node4);
        ListNode node6 = new ListNode(5, node5);
        System.out.println(node3);
        ListNode result = insertionSortList(node3);
        System.out.println(result);
    }

    public static ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode lastSorted = head, curr = head.next;
        while (curr != null) {
            if (lastSorted.val <= curr.val) {
                lastSorted = lastSorted.next;
            } else {
                ListNode prev = dummyHead;
                while (prev.next.val <= curr.val) {
                    prev = prev.next;
                }
                lastSorted.next = curr.next;
                curr.next = prev.next;
                prev.next = curr;
            }
            curr = lastSorted.next;
        }
        return dummyHead.next;
    }


    /**
     * 数组
     * @param list
     */
    public static void insertionSortList(int[] list) {
        for (int i = 1; i < list.length; i++) {
            int value = list[i];
            int i1 = i - 1;
            for (; i1 >= 0; i1--) {
                if (list[i1] > value) {
                    list[i1 + 1] = list[i1];
                } else {
                    break;
                }
            }
            list[i1 + 1] = value;
        }
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