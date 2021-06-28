package src.leetcode.medium;



/**
 *
 * 字节跳动
 * 链表两个数相加
 *
 * https://leetcode-cn.com/problems/add-two-numbers/
 * https://leetcode-cn.com/explore/interview/card/bytedance/244/linked-list-and-tree/1022/
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
//        ListNode result = addTwoNumbers2(node3, node6);
//                System.out.println(result);



//        ListNode node1 = new ListNode(0);
//        ListNode node4 = new ListNode(0);
//        ListNode result = addTwoNumbers2(node1, node4);
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
        ListNode result = addTwoNumbers2(node7, xnode4);
        System.out.println(result);

    }

    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode result2 = result;
        int flag = 0;
        while (l1 != null || l2 != null) {
            int l1v = l1 == null ? 0 : l1.val;
            int l2v = l2 == null ? 0 : l2.val;
            int i = l1v + l2v + flag;
            flag = 0;
            if (i >= 10) {
                i = i - 10;
                flag = 1;
            }
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
            result.next = new ListNode(i);
            result = result.next;
        }
        if (flag == 1) {
            result.next = new ListNode(flag);
        }
        return result2.next;
    }



  public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }






}