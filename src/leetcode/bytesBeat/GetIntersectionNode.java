package src.leetcode.bytesBeat;


import src.leetcode.other.ListNode;


/**
 *
 * 相交链表
 *
 * 指针 pA 指向 A 链表，指针 pB 指向 B 链表，依次往后遍历
 * 如果 pA 到了末尾，则 pA = headB 继续遍历
 * 如果 pB 到了末尾，则 pB = headA 继续遍历
 * 比较长的链表指针指向较短链表head时，长度差就消除了
 * 如此，只需要将最短链表遍历两次即可找到位置
 *
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 *
 */
public class GetIntersectionNode {


    public static void main(String[] args) {
        ListNode head4 = new ListNode(4);
        ListNode head6 = new ListNode(6, head4);
        ListNode head2 = new ListNode(2, head6);
        ListNode head5 = new ListNode(5);
        ListNode head1 = new ListNode(1, head5);
        ListNode intersectionNode = getIntersectionNode(head2, head1);
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

}
